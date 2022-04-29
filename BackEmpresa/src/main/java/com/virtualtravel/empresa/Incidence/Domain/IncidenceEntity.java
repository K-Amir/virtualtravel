package com.virtualtravel.empresa.Incidence.Domain;

import com.virtualtravel.empresa.Bus.Domain.BusEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "incidence")
@Getter
@Setter
public class IncidenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private BusEntity busEntity;

    private String reason;
}
