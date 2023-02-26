package com.fr.epita.javabackend.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_db")
public class User {

    public User(Long id,String username, String password, int contact, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.contactId = contact;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;


    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "contact_id", nullable = false)
    int contactId;

    @Column(name = "role")
    private String role;


}
