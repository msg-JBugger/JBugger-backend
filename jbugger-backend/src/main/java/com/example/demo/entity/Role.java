package com.example.demo.entity;

import com.example.demo.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;

    private RoleEnum type;

    @ManyToMany(
            targetEntity = User.class,
            mappedBy = "roles"
    )
    private Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "roles_permissions",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "permissionId")
    )
    private Set<Permission> permissions = new HashSet<>();
}