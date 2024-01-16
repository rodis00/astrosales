package com.github.rodis00.astrosales.auth;

import com.github.rodis00.astrosales.exception.InvalidPasswordException;
import com.github.rodis00.astrosales.exception.UserExistsException;
import com.github.rodis00.astrosales.model.Role;
import com.github.rodis00.astrosales.model.User;
import com.github.rodis00.astrosales.model.UserProfile;
import com.github.rodis00.astrosales.config.JwtService;
import com.github.rodis00.astrosales.repository.UserRepository;
import com.github.rodis00.astrosales.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    public AuthenticationResponse register(RegisterRequest request) {
        if (!userRepository.existsByEmail(request.getEmail())) {
            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(Role.USER);
            user.setUserProfile(new UserProfile());
            userRepository.save(user);

            String jwtToken = jwtService.generateToken(user);
            return new AuthenticationResponse(jwtToken);
        }
        else
            throw new UserExistsException("User with this email already exists.");
    }

    public AuthenticationResponse registerAdminUser(RegisterRequest request) {
        if (!userRepository.existsByEmail(request.getEmail())) {
            User admin = new User();
            admin.setEmail(request.getEmail());
            admin.setPassword(passwordEncoder.encode(request.getPassword()));
            admin.setRole(Role.ADMIN);
            admin.setUserProfile(new UserProfile());
            userRepository.save(admin);

            String jwtToken = jwtService.generateToken(admin);
            return new AuthenticationResponse(jwtToken);
        }
        else
            throw new UserExistsException("User with this email already exists.");
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = userService.findByEmail(request.getEmail());
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            String jwtToken = jwtService.generateToken(user);
            return new AuthenticationResponse(jwtToken);
        }
        else
            throw new InvalidPasswordException("Invalid password.");

    }
}
