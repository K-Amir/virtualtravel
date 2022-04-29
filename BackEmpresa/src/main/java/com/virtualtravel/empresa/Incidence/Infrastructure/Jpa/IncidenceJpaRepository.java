package com.virtualtravel.empresa.Incidence.Infrastructure.Jpa;

import com.virtualtravel.empresa.Incidence.Domain.IncidenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IncidenceJpaRepository extends JpaRepository<IncidenceEntity, Integer> {
    IncidenceEntity findIncidenceByBusEntity_Id(int busId);
}
