package dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.delete;

import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.UserGateway;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultDeleteUserUseCase extends DeleteUserUseCase {

    private final UserGateway userGateway;

    @Override
    public void execute(final String id) {
        final var userId = UserId.from(id);
        userGateway.delete(userId);
    }

}
