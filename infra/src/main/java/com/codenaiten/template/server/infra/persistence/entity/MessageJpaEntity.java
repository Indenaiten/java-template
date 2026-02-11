package com.codenaiten.template.server.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table( name = "messages" )
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageJpaEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", nullable = false )
    private Long id;

    @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.DETACH )
    @JoinColumn( name = "owner", nullable = false )
    private UserJpaEntity owner;

    @Column( name = "text", nullable = false )
    private String text;

    @CreationTimestamp
    @Column( name = "created_at", nullable = false, updatable = false )
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column( name = "updated_at", nullable = false )
    private LocalDateTime updatedAt;
}
