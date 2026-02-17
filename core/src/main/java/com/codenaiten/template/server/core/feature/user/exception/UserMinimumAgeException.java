package com.codenaiten.template.server.core.feature.user.exception;

import com.codenaiten.template.server.core.shared.exception.AppException;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserMinimumAgeException extends AppException{

    public static final String DEFAULT_MESSAGE = "User minimum age is not reached";

//--------------------------------------------------------------------------------------------------------------------\\

    private final LocalDate birthdate;

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public UserMinimumAgeException( final LocalDate birthdate ){
        super( DEFAULT_MESSAGE );
        this.birthdate = birthdate;
    }

    public UserMinimumAgeException( final LocalDate birthdate, final Throwable cause ){
        super( DEFAULT_MESSAGE, cause );
        this.birthdate = birthdate;
    }

//--------------------------------------------------------------------------------------------------------------------\\

}
