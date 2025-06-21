package dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.update;

import dev.kaykyfreitas.finuserservice.finuserservice.domain.exception.NotFoundException;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.exception.NotificationException;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.User;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.UserGateway;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.UserId;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.validation.handler.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultUpdateUserUseCase extends UpdateUserUseCase {

    private final UserGateway userGateway;

    @Override
    public UpdateUserOutput execute(final UpdateUserCommand command) {
        final var id = UserId.from(command.id());
        final var name = command.name();
        final var document = command.document();
        final var email = command.email();
        final var phone = command.phone();
        final var birthDate = command.birthDate();

        final var userOptional = userGateway.getById(id);

        if (userOptional.isEmpty())
            throw NotFoundException.with(User.class, id);

        final var user = userOptional.get();

        final var notification = Notification.create();
        notification.validate(() -> user.update(
                name,
                document,
                email,
                phone,
                birthDate
        ));

        if (notification.hasError()) notify(notification);

        return UpdateUserOutput.from(userGateway.update(user));
    }

    private void notify(final Notification notification) {
        throw new NotificationException("could not update user", notification);
    }

}
