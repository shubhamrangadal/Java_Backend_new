package com.fr.epita.javabackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Table(name = "contact_db")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    private String email;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "address_id",nullable = false)
    private Address address;

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
