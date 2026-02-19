package com.codenaiten.template.server.core.feature.user.service;

import com.codenaiten.template.server.core.feature.user.User;
import com.codenaiten.template.server.core.feature.user.command.RegisterUserCommand;
import com.codenaiten.template.server.core.feature.user.command.UpdateUserCommand;
import com.codenaiten.template.server.core.feature.user.exception.UserEmailUniqueException;
import com.codenaiten.template.server.core.feature.user.exception.UserIdUniqueException;
import com.codenaiten.template.server.core.feature.user.exception.UserMinimumAgeException;
import com.codenaiten.template.server.core.feature.user.exception.UserUsernameUniqueException;
import com.codenaiten.template.server.core.feature.user.spec.UserEmailUniqueSpec;
import com.codenaiten.template.server.core.feature.user.spec.UserIdUniqueSpec;
import com.codenaiten.template.server.core.feature.user.spec.UserMinimumAgeSpec;
import com.codenaiten.template.server.core.feature.user.spec.UserUsernameUniqueSpec;
import com.codenaiten.template.server.core.feature.user.spi.UserPropertiesPort;
import com.codenaiten.template.server.core.feature.user.spi.UserRepositoryPort;
import com.codenaiten.template.server.core.feature.user.vo.UserName;
import com.codenaiten.template.server.core.feature.user.vo.UserPassword;
import com.codenaiten.template.server.core.feature.user.vo.UserSurname;
import com.codenaiten.template.server.core.feature.user.vo.UserUsername;
import com.codenaiten.template.server.core.shared.vo.Email;
import com.codenaiten.template.server.core.shared.spi.PasswordCrypterPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserPropertiesPort userPropertiesPort;
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordCrypterPort passwordCrypter;

    public User create( final RegisterUserCommand command ){
        final UUID id = UUID.randomUUID();
        final Email email = new Email( command.getEmail() );
        final UserUsername username = new UserUsername( command.getUsername() );
        final UserName name = new UserName( command.getName() );
        final UserSurname surname = command.getSurname().map( UserSurname::new ).orElse( null );
        final LocalDate birthdate = command.getBirthdate();
        final UserPassword password = new UserPassword( command.getPassword() );
        final String hashedPassword = this.passwordCrypter.hash( password.value() );
        final User user = User.create( email, username, name, surname, birthdate, hashedPassword );

        final UserIdUniqueSpec userIdUniqueSpec = new UserIdUniqueSpec( this.userRepositoryPort );
        if( userIdUniqueSpec.not().test( user )) throw new UserIdUniqueException( user.getId() );

        final UserEmailUniqueSpec userEmailUniqueSpec = new UserEmailUniqueSpec( this.userRepositoryPort );
        if( userEmailUniqueSpec.not().test( user )) throw new UserEmailUniqueException( user.getEmail() );

        final UserUsernameUniqueSpec userUsernameUniqueSpec = new UserUsernameUniqueSpec( this.userRepositoryPort );
        if( userUsernameUniqueSpec.not().test( user )) throw new UserUsernameUniqueException( user.getUsername() );

        final UserMinimumAgeSpec userMinimumAgeSpec = new UserMinimumAgeSpec( this.userPropertiesPort );
        if( userMinimumAgeSpec.not().test( user )) throw new UserMinimumAgeException( birthdate );

        return user;
    }

    public User update( final User user, final UpdateUserCommand command ){
        final User update = user.copy();

        final Email email = Optional.ofNullable( command.getEmail() ).map( Email::new ).orElse( null );
        if( Objects.nonNull( email ) && update.different( email )) {
            final UserEmailUniqueSpec userEmailUniqueSpec = new UserEmailUniqueSpec( this.userRepositoryPort );
            if( userEmailUniqueSpec.not().test( update )) throw new UserEmailUniqueException( email.value() );
            log.debug( "User {}: Updated email: {}", user.getId(), email );
        }

        final UserUsername username = Optional.ofNullable( command.getUsername() ).map( UserUsername::new ).orElse( null );
        if( Objects.nonNull( username ) && update.different( username )) {
            final UserUsernameUniqueSpec userUsernameUniqueSpec = new UserUsernameUniqueSpec( this.userRepositoryPort );
            if( userUsernameUniqueSpec.not().test( update )) throw new UserUsernameUniqueException( username.value() );
            log.debug( "User {}: Updated username: {}", user.getId(), username );
        }

        final UserName name = Optional.ofNullable( command.getName() ).map( UserName::new ).orElse( null );
        if( Objects.nonNull( name ) && update.updateName( name )){
            log.debug( "User {}: Updated name: {}", user.getId(), name );
        }

        final Optional<UserSurname> surname = Optional.ofNullable( command.getSurname() )
                .map( opt -> opt.map( UserSurname::new )).orElse( null );
        if( Objects.nonNull( surname ) && update.updateSurname( surname.orElse( null ))){
            log.debug( "User {}: Updated surname: {}", user.getId(), surname );
        }

        final LocalDate birthdate = command.getBirthdate();
        if( Objects.nonNull( birthdate ) && update.updateBirthdate( birthdate )) {
            final UserMinimumAgeSpec userMinimumAgeSpec = new UserMinimumAgeSpec( this.userPropertiesPort );
            if( userMinimumAgeSpec.not().test( update )) throw new UserMinimumAgeException( birthdate );
            log.debug( "User {}: Updated birthdate: {}", user.getId(), birthdate );
        }

        final UserPassword password = Optional.ofNullable( command.getPassword() ).map( UserPassword::new ).orElse( null );
        if( Objects.nonNull( password ) && update.updatePassword( this.passwordCrypter.hash( password.value() ))) {
            log.debug( "User {}: Updated password", user.getId() );
        }

        return update;
    }
}
