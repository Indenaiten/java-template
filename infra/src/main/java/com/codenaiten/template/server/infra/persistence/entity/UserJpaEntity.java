package com.codenaiten.template.server.infra.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table( name = "users" )
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJpaEntity {

    @Id
    @Column( name = "id", nullable = false )
    private UUID id;

    @Column( name = "email", nullable = false, unique = true )
    private String email;

    @Column( name = "username", nullable = false, unique = true )
    private String username;

    @Column( name = "name", nullable = false )
    private String name;

    @Column( name = "surname" )
    private String surname;

    @Column( name = "birthdate", nullable = false )
    private LocalDate birthdate;

    @Column( name = "password", nullable = false )
    private String password;

    @CreationTimestamp
    @Column( name = "created_at", nullable = false, updatable = false )
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column( name = "updated_at", nullable = false )
    private LocalDateTime updatedAt;
}
