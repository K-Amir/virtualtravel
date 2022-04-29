package com.virtualtravel.empresa.Auth.Application;


import com.virtualtravel.empresa.Auth.Application.Dto.AuthOutputDto;
import com.virtualtravel.empresa.Auth.Domain.AdminUsersEntity;
import com.virtualtravel.empresa.Auth.Domain.AuthService;
import com.virtualtravel.empresa.Auth.Infrastructure.JwtUtil;
import com.virtualtravel.empresa.ErrorHandling.SuccessDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("empresa/v0/auth")
public record AuthController(AuthService authService, JwtUtil jwtUtil) {


    @GetMapping("token")
    public ResponseEntity<?> getJwtToken(@RequestHeader("user") String user, @RequestHeader("password") String password) {
        AdminUsersEntity adminUsersEntity = authService.getByEmail(user);

        authService.isPasswordMatch(password, adminUsersEntity.getPassword());

        AuthOutputDto output = new AuthOutputDto(jwtUtil.generateToken(user));

        return ResponseEntity.ok(output);

    }

    @PostMapping
    public ResponseEntity<SuccessDto> registerNewAdminUser(@RequestBody AdminUsersEntity adminUsersEntity) {
        authService.save(adminUsersEntity);
        return SuccessDto.send("User created successfully");
    }


    @GetMapping("token/{token}")
    public ResponseEntity<?> checkToken(@PathVariable String token) {
        jwtUtil.getSubject(token);

        return ResponseEntity.ok().body(true);
    }
}
