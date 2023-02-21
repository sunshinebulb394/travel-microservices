package com.travel.app.bus.auth;

import com.travel.app.bus.config.LogoutService;
import com.travel.app.bus.dto.AuthenticationRequest;
import com.travel.app.bus.dto.AuthenticationResponse;
import com.travel.app.bus.dto.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.registerAdmin(request));
    }
    @PostMapping("/register/user")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.registerUser(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }



//    @GetMapping("/all")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<List<User>>getAllAccount() {
//        return ResponseEntity.ok(accountService.getAccounts());
//    }
//
//    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public void deleteAccount(@PathVariable("id") Long id) {
//        accountService.deleteAccount(id);
//    }
//
//    @PutMapping("/update/{id}")
//    @PreAuthorize("hasRole('ADMIN', 'USER')")
//    public ResponseEntity<User> updateAccount(@PathVariable("id") Long id, @RequestBody User user) throws AccountNotFoundException {
//        return ResponseEntity.ok(accountService.updateAccount(id, user));
//    }

}
