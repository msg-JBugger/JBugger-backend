package com.example.demo.entity;

import com.example.demo.enums.PermissionEnum;
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
@Table(name="permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long permissionId;

    private PermissionEnum type;

    private String description;

    @ManyToMany(
            fetch = FetchType.EAGER,
            mappedBy = "permissions"
    )
    private Set<Role> roles = new HashSet<>();
}
