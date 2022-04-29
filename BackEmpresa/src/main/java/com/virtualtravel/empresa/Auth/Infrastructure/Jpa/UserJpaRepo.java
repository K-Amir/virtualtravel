package com.virtualtravel.empresa.Auth.Infrastructure.Jpa;

import com.virtualtravel.empresa.Auth.Domain.AdminUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepo extends JpaRepository<AdminUsersEntity, String> {
}
