package org.springclass.springclassproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mas_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private  RoleType role;

    public enum RoleType {
        USER, ADMIN
    }

    public Role() {}

    public Role(String name, RoleType role) {
        this.name = name;
        this.role = role;
    }
}
