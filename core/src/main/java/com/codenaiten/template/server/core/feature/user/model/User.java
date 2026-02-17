package com.codenaiten.template.server.core.feature.user.model;

import com.codenaiten.template.server.core.feature.user.model.vo.UserName;
import com.codenaiten.template.server.core.feature.user.model.vo.UserSurname;
import com.codenaiten.template.server.core.feature.user.model.vo.UserUsername;
import com.codenaiten.template.server.core.shared.model.entity.Entity;
import com.codenaiten.template.server.core.shared.model.vo.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class User implements Entity<UUID>{

    private UUID id;
    private String email;
    private String username;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

//--------------------------------------------------------------------------------------------------------------------\\
//---| COPY |---------------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    @Override
    public User copy() {
        return User.builder().id( this.id ).email( this.email ).username( this.username ).name( this.name )
                .surname( this.surname ).password( this.password ).birthdate( this.birthdate )
                .createdAt( this.createdAt ).updatedAt( this.updatedAt ).build();
    }

//--------------------------------------------------------------------------------------------------------------------\\
//---| GETTERS |------------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public Optional<String> getSurname(){
        return Optional.ofNullable( this.surname );
    }

    public Integer getAge(){
        return this.birthdate.until( LocalDate.now() ).getYears();
    }

//--------------------------------------------------------------------------------------------------------------------\\
//---| UPDATERS |-----------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public boolean updateEmail( final Email email ){
        if( Objects.isNull( email )) throw new IllegalArgumentException( "Email cannot be null" );
        if( Objects.equals( this.email, email.value() )) return false;
        this.email = email.value();
        this.updatedAt = LocalDateTime.now();
        return true;
    }

    public boolean updateUsername( final UserUsername username ){
        if( Objects.isNull( username )) throw new IllegalArgumentException( "Username cannot be null" );
        if( Objects.equals( this.username, username.value() )) return false;
        this.username = username.value();
        this.updatedAt = LocalDateTime.now();
        return true;
    }

    public boolean updateName( final UserName name ){
        if( Objects.isNull( name )) throw new IllegalArgumentException( "Name cannot be null" );
        if( Objects.equals( this.name, name.value() )) return false;
        this.name = name.value();
        this.updatedAt = LocalDateTime.now();
        return true;
    }

    public boolean updateSurname( final UserSurname surname ){
        this.surname = Optional.ofNullable( surname ).map( UserSurname::value ).orElse( null );
        if( Objects.equals( this.surname, surname.value() )) return false;
        this.updatedAt = LocalDateTime.now();
        return true;
    }

    public boolean updateBirthdate( final LocalDate birthdate ){
        if( Objects.isNull( birthdate )) throw new IllegalArgumentException( "Birthdate cannot be null" );
        if( Objects.equals( this.birthdate, birthdate )) return false;
        this.birthdate = birthdate;
        this.updatedAt = LocalDateTime.now();
        return true;
    }

    public boolean updatePassword( final String password ){
        if( Objects.isNull( password )) throw new IllegalArgumentException( "Password cannot be null" );
        if( Objects.equals( this.password, password )) return false;
        this.password = password;
        this.updatedAt = LocalDateTime.now();
        return true;
    }

//--------------------------------------------------------------------------------------------------------------------\\
//---| FACTORY |------------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public static User create( final Email email, final UserUsername username, final UserName name,
                               final UserSurname surname, final LocalDate birthdate, final String password ){
        if( Objects.isNull( email )) throw new IllegalArgumentException( "Email is required" );
        if( Objects.isNull( username )) throw new IllegalArgumentException( "Username is required" );
        if( Objects.isNull( name )) throw new IllegalArgumentException( "Name is required" );
        if( Objects.isNull( birthdate )) throw new IllegalArgumentException( "Birthdate is required" );
        if( Objects.isNull( password )) throw new IllegalArgumentException( "Password is required" );

        final UUID id = UUID.randomUUID();
        final LocalDateTime createdAt = LocalDateTime.now();
        final UserBuilder builder = User.builder().id( id ).email( email.value() ).username( username.value() )
                .name( name.value() ).birthdate( birthdate ).password( password ).createdAt( createdAt )
                .updatedAt( createdAt );
        Optional.ofNullable( surname ).ifPresent( v -> builder.surname( v.value() ));
        return builder.build();
    }

//--------------------------------------------------------------------------------------------------------------------\\

}

