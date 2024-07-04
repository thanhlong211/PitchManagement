package com.swd.ccp.models.entity_models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pitch")
public class Pitch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String pitchStatus;

    @OneToMany(mappedBy = "pitch")
    @ToString.Exclude
    List<Booking> bookingList;

    private String name;

    private int capacity;

    private float price;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
//
//    @Transient
//    private boolean isAvailable;
}
