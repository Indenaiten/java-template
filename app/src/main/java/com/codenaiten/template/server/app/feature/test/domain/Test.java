package com.codenaiten.template.server.app.feature.test.domain;

import com.codenaiten.template.server.app.shared.domain.entity.BaseEntity;
import com.codenaiten.template.server.app.shared.domain.entity.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode( callSuper = true )
public class Test extends BaseEntity<Long> {

    private String message;

    @Override
    public Entity<Long> copy() {
        return Test.builder().id( this.id ).message( this.message ).build();
    }
}
