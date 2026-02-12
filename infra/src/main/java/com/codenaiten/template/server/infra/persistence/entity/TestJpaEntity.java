package com.codenaiten.template.server.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table( name = "tests" )
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestJpaEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", nullable = false )
    private Long id;

    @Column( name = "message", nullable = false )
    private String message;

    @CreationTimestamp
    @Column( name = "created_at", nullable = false, updatable = false )
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column( name = "updated_at", nullable = false )
    private LocalDateTime updatedAt;
}
