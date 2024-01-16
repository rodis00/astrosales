package com.github.rodis00.astrosales.controller;

import com.github.rodis00.astrosales.dto.ApiResponseDto;
import com.github.rodis00.astrosales.dto.UserDto;
import com.github.rodis00.astrosales.model.Transaction;
import com.github.rodis00.astrosales.model.User;
import com.github.rodis00.astrosales.model.UserProfile;
import com.github.rodis00.astrosales.service.TransactionService;
import com.github.rodis00.astrosales.service.UserService;
import com.github.rodis00.astrosales.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("astrosales/api/v1/users")
public class UserController {
    private final UserService userService;
    private final TransactionService transactionService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService,
                          TransactionService transactionService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        if (!userService.existsByEmail(user.getEmail())) {
            User newUser = new User();
            newUser.setEmail(user.getEmail());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setUserProfile(new UserProfile());
            userService.saveUser(newUser);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(UserDto.from(newUser));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponseDto(
                            UserUtils.USER_EXISTS_CODE,
                            UserUtils.USER_EXISTS_MESSAGE)
                    );
        }
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUsers()
                        .stream()
                        .map(UserDto::from)
                        .toList()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(UserDto.from(userService.getUserById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(UserDto.from(userService.updateUser(id, user)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUpdateUser(@PathVariable Integer id, @RequestBody User user) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(UserDto.from(userService.patchUpdateUser(id, user)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        List<Transaction> transactions = transactionService.getTransactionByUserId(id);
        if (!transactions.isEmpty()) {
            for (Transaction t : transactions)
                t.setUser(null);
            transactionService.saveAllTransactions(transactions);
        }
        userService.deleteUser(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();

    }
}
