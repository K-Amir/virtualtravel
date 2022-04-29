package com.virtualtravel.empresa.Mail.Infrastructure.Jpa;

import com.virtualtravel.empresa.Mail.Domain.MailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailJpaRepository extends JpaRepository<MailEntity, Integer> {
}
