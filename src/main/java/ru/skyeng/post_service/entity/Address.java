package ru.skyeng.post_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street", nullable = false, length = 30)
    private String street;

    @Column(name = "city", nullable = false, length = 20)
    private String city;

    @Column(name = "state", length = 20)
    private String state;

    @Column(name = "country", nullable = false, length = 20)
    private String country;

    @OneToMany(mappedBy = "officeAddress")
    private List<PostOffice> postOffices;

    @OneToMany(mappedBy = "recipientAddress")
    private List<Parcel> parcels;
}
