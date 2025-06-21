package dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.create;

import dev.kaykyfreitas.finuserservice.finuserservice.domain.exception.NotificationException;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.User;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.UserGateway;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.validation.handler.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultCreateUserUseCase extends CreateUserUseCase {

    private final UserGateway userGateway;

    @Override
    public CreateUserOutput execute(final CreateUserCommand command) {
        final var name = command.name();
        final var document = command.document();
        final var email = command.email();
        final var phone = command.phone();
        final var birthDate = command.birthDate();

        final var notification = Notification.create();
        final var user = notification.validate(() -> User.newUser(name, document, email, phone, birthDate));

        if (notification.hasError()) notify(notification);

        return CreateUserOutput.from(userGateway.create(user));
    }

    private void notify(final Notification notification) {
        throw new NotificationException("could not create an user", notification);
    }

}
