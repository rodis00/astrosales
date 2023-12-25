package com.github.rodis00.astrosales.auth;

import com.github.rodis00.astrosales.dto.ApiResponseDto;
import com.github.rodis00.astrosales.service.UserService;
import com.github.rodis00.astrosales.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("astrosales/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (!userService.existsByEmail(request.getEmail()))
            return ResponseEntity.ok(authenticationService.register(request));
        else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponseDto(
                        UserUtils.USER_EXISTS_CODE,
                        UserUtils.USER_EXISTS_MESSAGE
                    ));
        }
    }

    @PostMapping("/register-admin-user")
    public ResponseEntity<AuthenticationResponse> registerAdminUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.registerAdminUser(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
