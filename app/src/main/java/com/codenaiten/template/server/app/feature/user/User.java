package com.codenaiten.template.server.app.feature.user;

import com.codenaiten.template.server.app.shared.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode( callSuper = true )
public class User extends BaseEntity<UUID> {

    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Override
    public User copy() {
        return User.builder()
                .id( this.id )
                .username( this.username )
                .createdAt( this.createdAt )
                .updatedAt( this.updatedAt )
                .build();
    }
}
