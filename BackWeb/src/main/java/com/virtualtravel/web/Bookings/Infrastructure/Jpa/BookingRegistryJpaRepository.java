package com.virtualtravel.web.Bookings.Infrastructure.Jpa;

import com.virtualtravel.web.Bookings.Domain.BookingRegistryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRegistryJpaRepository extends JpaRepository<BookingRegistryEntity, Integer> {
}
