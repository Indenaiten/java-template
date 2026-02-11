package com.codenaiten.template.server.app.feature.message;

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
public class Message extends BaseEntity<Long> {

    private UUID owner;
    private String text;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Override
    public Message copy() {
        return Message.builder()
                .id( this.id )
                .owner( this.owner )
                .text( this.text )
                .createdAt( this.createdAt )
                .updatedAt( this.updatedAt )
                .build();
    }
}
