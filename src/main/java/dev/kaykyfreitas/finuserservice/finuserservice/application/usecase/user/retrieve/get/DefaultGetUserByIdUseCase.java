package dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.retrieve.get;

import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.User;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.UserGateway;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.UserId;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultGetUserByIdUseCase extends GetUserByIdUseCase {

    private final UserGateway userGateway;

    @Override
    public GetUserByIdOutput execute(final String id) {
        final var userId = UserId.from(id);

        final var user = userGateway.getById(userId)
                .orElseThrow(() -> NotFoundException.with(User.class, userId));

        return GetUserByIdOutput.from(user);
    }

}
