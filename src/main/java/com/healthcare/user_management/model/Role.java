package com.healthcare.user_management.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roleId;

    @Column(name = "role_name", length = 200)
    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;
}
