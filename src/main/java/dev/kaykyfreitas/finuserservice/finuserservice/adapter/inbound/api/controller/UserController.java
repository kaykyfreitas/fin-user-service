package dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.controller;

import dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.UserAPI;
import dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.model.request.CreateUserRequest;
import dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.model.response.CreateUserResponse;
import dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.model.response.GetUserByIdResponse;
import dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.model.response.ListUserResponse;
import dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.model.response.UpdateUserResponse;
import dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.presenter.UserApiPresenter;
import dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.create.CreateUserCommand;
import dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.create.CreateUserUseCase;
import dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.delete.DeleteUserUseCase;
import dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.retrieve.get.GetUserByIdUseCase;
import dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.retrieve.list.ListUserUseCase;
import dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.update.UpdateUserCommand;
import dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.update.UpdateUserUseCase;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.pagination.Pagination;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.pagination.SearchQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserAPI {

    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final GetUserByIdUseCase getUserUseCase;
    private final ListUserUseCase listUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @Override
    public ResponseEntity<CreateUserResponse> create(final CreateUserRequest request) {
        final var command = CreateUserCommand.with(
                request.name(),
                request.document(),
                request.email(),
                request.phone(),
                request.birthDate()
        );

        final var output = createUserUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserApiPresenter.present(output));
    }

    @Override
    public ResponseEntity<UpdateUserResponse> update(final String id, final CreateUserRequest request) {
        final var command = UpdateUserCommand.with(
                id,
                request.name(),
                request.document(),
                request.email(),
                request.phone(),
                request.birthDate()
        );

        final var output = updateUserUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.OK).body(UserApiPresenter.present(output));
    }

    @Override
    public ResponseEntity<GetUserByIdResponse> getById(final String id) {
        final var output = getUserUseCase.execute(id);

        return ResponseEntity.status(HttpStatus.OK).body(UserApiPresenter.present(output));
    }

    @Override
    public ResponseEntity<Pagination<ListUserResponse>> list(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        final var query = new SearchQuery(page, perPage, search, sort, direction);

        final var output = listUserUseCase.execute(query);

        return ResponseEntity.status(HttpStatus.OK).body(output.map(UserApiPresenter::present));
    }

    @Override
    public ResponseEntity<Void> delete(final String id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}
