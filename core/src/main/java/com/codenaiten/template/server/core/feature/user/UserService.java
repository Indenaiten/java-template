package com.codenaiten.template.server.core.feature.user;

import com.codenaiten.template.server.core.feature.user.dto.command.RegisterUserCommand;
import com.codenaiten.template.server.core.feature.user.dto.command.UpdateUserCommand;
import com.codenaiten.template.server.core.feature.user.exception.*;
import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.core.feature.user.model.vo.UserName;
import com.codenaiten.template.server.core.feature.user.model.vo.UserPassword;
import com.codenaiten.template.server.core.feature.user.model.vo.UserSurname;
import com.codenaiten.template.server.core.feature.user.model.vo.UserUsername;
import com.codenaiten.template.server.core.feature.user.spec.UserEmailUniqueSpec;
import com.codenaiten.template.server.core.feature.user.spec.UserIdUniqueSpec;
import com.codenaiten.template.server.core.feature.user.spec.UserMinimumAgeSpec;
import com.codenaiten.template.server.core.feature.user.spec.UserUsernameUniqueSpec;
import com.codenaiten.template.server.core.feature.user.spi.UserMinimumAgeProviderPort;
import com.codenaiten.template.server.core.feature.user.spi.UserRepositoryPort;
import com.codenaiten.template.server.core.shared.model.vo.Email;
import com.codenaiten.template.server.core.shared.spi.PasswordEncoderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserMinimumAgeProviderPort userProperties;
    private final UserRepositoryPort userRepository;
    private final PasswordEncoderPort passwordEncoder;

    public User create( final RegisterUserCommand command ){
        final Email email = new Email( command.email() );
        final UserUsername username = new UserUsername( command.username() );
        final UserName name = new UserName( command.name() );
        final UserSurname surname = command.surname().map( UserSurname::new ).orElse( null );
        final LocalDate birthdate = command.birthdate();
        final UserPassword password = new UserPassword( command.password() );
        final String hashedPassword = this.passwordEncoder.hash( password.value() );
        final User user = User.create( email, username, name, surname, birthdate, hashedPassword );

        final UserIdUniqueSpec userIdUniqueSpec = new UserIdUniqueSpec( this.userRepository );
        if( userIdUniqueSpec.not().test( user )) throw new UserIdUniqueException( user.getId() );

        final UserEmailUniqueSpec userEmailUniqueSpec = new UserEmailUniqueSpec( this.userRepository );
        if( userEmailUniqueSpec.not().test( user )) throw new UserEmailUniqueException( user.getEmail() );

        final UserUsernameUniqueSpec userUsernameUniqueSpec = new UserUsernameUniqueSpec( this.userRepository );
        if( userUsernameUniqueSpec.not().test( user )) throw new UserUsernameUniqueException( user.getUsername() );

        final UserMinimumAgeSpec userMinimumAgeSpec = new UserMinimumAgeSpec( this.userProperties );
        if( userMinimumAgeSpec.not().test( user )) throw new UserMinimumAgeException( birthdate );

        return user;
    }

    public boolean update( final User user, final UpdateUserCommand command ){
        if( !this.userRepository.existsById( user.getId() )) throw new UserNotFoundException( user.getId() );
        boolean result = false;

        final Email email = new Email( command.email() );
        if( user.updateEmail( email )) {
            final UserEmailUniqueSpec userEmailUniqueSpec = new UserEmailUniqueSpec( this.userRepository);
            if( userEmailUniqueSpec.not().test( user )) throw new UserEmailUniqueException( email.value() );
            result = true;
            log.debug( "User {}: Updated email: {}", user.getId(), email );
        }

        final UserUsername username = new UserUsername( command.username() );
        if( user.updateUsername( username )) {
            final UserUsernameUniqueSpec userUsernameUniqueSpec = new UserUsernameUniqueSpec( this.userRepository);
            if( userUsernameUniqueSpec.not().test( user )) throw new UserUsernameUniqueException( username.value() );
            result = true;
            log.debug( "User {}: Updated username: {}", user.getId(), username );
        }

        final UserName name = new UserName( command.name() );
        if( user.updateName( name )){
            result = true;
            log.debug( "User {}: Updated name: {}", user.getId(), name );
        }

        final UserSurname surname = command.surname().map( UserSurname::new ).orElse( null );
        if( user.updateSurname( surname )){
            result = true;
            log.debug( "User {}: Updated surname: {}", user.getId(), surname );
        }

        final LocalDate birthdate = command.birthdate();
        if( user.updateBirthdate( birthdate )) {
            final UserMinimumAgeSpec userMinimumAgeSpec = new UserMinimumAgeSpec( this.userProperties);
            if( userMinimumAgeSpec.not().test( user )) throw new UserMinimumAgeException( birthdate );
            result = true;
            log.debug( "User {}: Updated birthdate: {}", user.getId(), birthdate );
        }

        return result;
    }

    public boolean updatePassword( final User user, final String password, final UserPassword newPassword ){
        if( !this.userRepository.existsById( user.getId() )) throw new UserNotFoundException( user.getId() );
        boolean result = false;

        if( this.passwordEncoder.matches( password, user.getPassword() ) &&
                user.updatePassword( this.passwordEncoder.hash( newPassword.value() ))) {
            result = true;
            log.debug( "User {}: Updated password", user.getId() );
        }

        return result;
    }
}
