package com.github.rodis00.astrosales.auth;

import com.github.rodis00.astrosales.auth.exception.InvalidPasswordException;
import com.github.rodis00.astrosales.auth.exception.UserExistsException;
import com.github.rodis00.astrosales.user.entity.Role;
import com.github.rodis00.astrosales.user.entity.User;
import com.github.rodis00.astrosales.userProfile.entity.UserProfile;
import com.github.rodis00.astrosales.config.JwtService;
import com.github.rodis00.astrosales.user.UserRepository;
import com.github.rodis00.astrosales.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<String> getAuthorities(String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractClaim(token, claims -> claims.getSubject());
        User user = userService.findByEmail(username);

        return user.getAuthorities()
                .stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .toList();
    }
}
