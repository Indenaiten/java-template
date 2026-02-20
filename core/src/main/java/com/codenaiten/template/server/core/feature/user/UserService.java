package com.codenaiten.template.server.core.feature.user;

import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.core.feature.user.dto.command.RegisterUserCommand;
import com.codenaiten.template.server.core.feature.user.dto.command.UpdateUserCommand;
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
import com.codenaiten.template.server.core.feature.user.model.vo.UserName;
import com.codenaiten.template.server.core.feature.user.model.vo.UserPassword;
import com.codenaiten.template.server.core.feature.user.model.vo.UserSurname;
import com.codenaiten.template.server.core.feature.user.model.vo.UserUsername;
import com.codenaiten.template.server.core.shared.model.vo.Email;
import com.codenaiten.template.server.core.shared.spi.PasswordCrypterPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserPropertiesPort userPropertiesPort;
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordCrypterPort passwordCrypter;

    public User create( final RegisterUserCommand command ){
        final UUID id = UUID.randomUUID();
        final Email email = new Email( command.email() );
        final UserUsername username = new UserUsername( command.username() );
        final UserName name = new UserName( command.name() );
        final UserSurname surname = command.surname().map( UserSurname::new ).orElse( null );
        final LocalDate birthdate = command.birthdate();
        final UserPassword password = new UserPassword( command.password() );
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

        if( command.email().isPresent() ) {
            final Email email = command.email().get().map( Email::new )
                    .orElseThrow( () -> new IllegalArgumentException( "User Email to update is required" ));
            if( update.updateEmail( email )) {
                final UserEmailUniqueSpec userEmailUniqueSpec = new UserEmailUniqueSpec( this.userRepositoryPort );
                if( userEmailUniqueSpec.not().test( update )) throw new UserEmailUniqueException( email.value() );
                log.debug( "User {}: Updated email: {}", user.getId(), email );
            }
        }

        if( command.username().isPresent() ) {
            final UserUsername username = command.username().get().map( UserUsername::new )
                    .orElseThrow( () -> new IllegalArgumentException( "User Username to update is required" ));
            if( update.updateUsername( username )) {
                final UserUsernameUniqueSpec userUsernameUniqueSpec = new UserUsernameUniqueSpec( this.userRepositoryPort );
                if( userUsernameUniqueSpec.not().test( update )) throw new UserUsernameUniqueException( username.value() );
                log.debug( "User {}: Updated username: {}", user.getId(), username );
            }
        }

        if( command.name().isPresent() ) {
            final UserName name = command.name().get().map( UserName::new )
                    .orElseThrow( () -> new IllegalArgumentException( "User Name to update is required" ));
            if( update.updateName( name )){
                log.debug( "User {}: Updated name: {}", user.getId(), name );
            }
        }

        if( command.surname().isPresent() ) {
            final UserSurname surname = command.surname().get().map( UserSurname::new ).orElse( null );
            if( update.updateSurname( surname )){
                log.debug( "User {}: Updated surname: {}", user.getId(), surname );
            }
        }

        if( command.birthdate().isPresent() ) {
            final LocalDate birthdate = command.birthdate().get()
                    .orElseThrow( () -> new IllegalArgumentException( "User Birthdate to update is required" ));
            if( update.updateBirthdate( birthdate )) {
                final UserMinimumAgeSpec userMinimumAgeSpec = new UserMinimumAgeSpec( this.userPropertiesPort );
                if( userMinimumAgeSpec.not().test( update )) throw new UserMinimumAgeException( birthdate );
                log.debug( "User {}: Updated birthdate: {}", user.getId(), birthdate );
            }
        }

        if( command.password().isPresent() ) {
            final UserPassword password = command.password().get().map( UserPassword::new )
                    .orElseThrow( () -> new IllegalArgumentException( "User Password to update is required" ));
            if( update.updatePassword( this.passwordCrypter.hash( password.value() ))) {
                log.debug( "User {}: Updated password", user.getId() );
            }
        }

        return update;
    }
}
