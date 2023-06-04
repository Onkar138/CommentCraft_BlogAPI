package com.myblogrestapi.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "Users",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),@UniqueConstraint(columnNames = {"username"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long u_id;
    private String name;
    private String email;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "u_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "r_id"))
    private Set<Role> roles;
}
