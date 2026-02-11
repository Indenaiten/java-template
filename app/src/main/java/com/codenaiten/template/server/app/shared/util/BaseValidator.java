package com.codenaiten.template.server.app.shared.util;

import com.codenaiten.template.server.app.shared.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class BaseValidator {

    protected final List<String> errors = new ArrayList<>();

    public void validate() throws ValidationException{
        if( !this.errors.isEmpty() ) throw new ValidationException( this.errors );
    }

    public void reset(){
        this.errors.clear();
    }
}
