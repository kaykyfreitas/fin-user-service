package dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.presenter;

import dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.model.response.CreateUserResponse;
import dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.model.response.GetUserByIdResponse;
import dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.model.response.ListUserResponse;
import dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.model.response.UpdateUserResponse;
import dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.create.CreateUserOutput;
import dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.retrieve.get.GetUserByIdOutput;
import dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.retrieve.list.ListUserOutput;
import dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.update.UpdateUserOutput;

public interface UserApiPresenter {

    static CreateUserResponse present(final CreateUserOutput output) {
        return new CreateUserResponse(
                output.id(),
                output.name(),
                output.document(),
                output.email(),
                output.phone(),
                output.birthDate(),
                output.active(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static UpdateUserResponse present(final UpdateUserOutput output) {
        return new UpdateUserResponse(
                output.id(),
                output.name(),
                output.document(),
                output.email(),
                output.phone(),
                output.birthDate(),
                output.active(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static GetUserByIdResponse present(final GetUserByIdOutput output) {
        return new GetUserByIdResponse(
                output.id(),
                output.name(),
                output.document(),
                output.email(),
                output.phone(),
                output.birthDate(),
                output.active(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static ListUserResponse present(final ListUserOutput output) {
        return new ListUserResponse(
                output.id(),
                output.name(),
                output.document(),
                output.email(),
                output.phone(),
                output.birthDate(),
                output.active()
        );
    }

}
