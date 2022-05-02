package com.virtualtravel.empresa.Auth.Infrastructure;

import com.virtualtravel.empresa.Auth.Domain.AdminUsersEntity;
import com.virtualtravel.empresa.Auth.Domain.AuthService;
import com.virtualtravel.empresa.Auth.Infrastructure.Jpa.UserJpaRepo;
import com.virtualtravel.empresa.ErrorHandling.PasswordDoesNotMatchException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public record AuthServiceImpl(UserJpaRepo userRepo) implements AuthService {

    @Override
    public void save(AdminUsersEntity adminUsersEntity) {
        userRepo.save(adminUsersEntity);
    }

    @Override
    public void deleteByEmail(String email) {
        userRepo.deleteById(email);
    }

    @Override
    public AdminUsersEntity getByEmail(String email) {
        return userRepo.findById(email).orElseThrow(() -> new EntityNotFoundException("Not found user with the provided email"));
    }


}
