package com.swd.ccp.models.entity_models;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String address;

    @Column(name = "open_time")
    private String openTime;

    @Column(name = "close_time")
    private String closeTime;

    private Double rating;

    private String phone;

    private String avatar;

    private String status;



    @OneToMany(mappedBy = "shop")
    @ToString.Exclude
    private List<FollowerCustomer> followerCustomerList;



    @OneToMany(mappedBy = "shop")
    @ToString.Exclude
    private List<Manager> managerList;


    @OneToMany(mappedBy = "shop")
    @ToString.Exclude
    private List<ShopCustomerCommunication> shopCustomerCommunications;

    @OneToMany(mappedBy = "shop")
    @ToString.Exclude
    private List<Pitch> pitches;

}
