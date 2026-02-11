package com.codenaiten.template.server.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
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

    @Column( name = "username", nullable = false, unique = true )
    private String username;

    @OneToMany( mappedBy = "owner", cascade = CascadeType.ALL )
    private List<MessageJpaEntity> messages;

    @CreationTimestamp
    @Column( name = "created_at", nullable = false, updatable = false )
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column( name = "updated_at", nullable = false )
    private LocalDateTime updatedAt;
}
