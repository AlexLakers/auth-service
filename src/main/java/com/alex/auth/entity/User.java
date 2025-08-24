package com.alex.bank.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "username")
@ToString(of = "username")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(name = "email")
    private String username;
    private String password;
    private boolean enabled;

    @Embedded
    @ElementCollection
    @CollectionTable(name = "user_roles",
                    joinColumns = @JoinColumn(name = "user_id"))
    @AttributeOverride(name = "roleName",column = @Column(name = "name"))
    @Builder.Default
    private List<Role> roles=new ArrayList<>();


}
