package com.tsb.technical.test.controllers;

import com.tsb.technical.test.entities.AccountHolder;
import com.tsb.technical.test.repositories.AccountHolderRepository;
import com.tsb.technical.test.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    AccountHolderRepository accountHolderRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, AccountHolderRepository accountHolderRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.accountHolderRepository = accountHolderRepository;
    }

    record LoginRequest(String username, String password) {}
    record LoginResponse(String token, Long accountHolderId) {}
    record ErrorResponse(String message) {}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );


            // Grab the id of the authenticated used
            // In reality we would use something unique but for simplicity I am not enforcing it here
            Long accountHolderId = accountHolderRepository.findByUsername(request.username).getId();

            final String jwt = jwtUtil.generateToken(request.username(), accountHolderId);
            // Not great practice but for simplicity it will be ok
            return ResponseEntity.ok(new LoginResponse(jwt, accountHolderId));

        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Invalid username or password"));
        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Authentication failed: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("An error occurred during authentication"));
        }
    }

    // Optional: Add an endpoint to test if the authentication is working
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Auth endpoint is accessible!");
    }
}
