package dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.retrieve.list;

import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.User;

public record ListUserOutput(
        String id,
        String name,
        String document,
        String email,
        String phone,
        String birthDate,
        boolean active
) {
    public static ListUserOutput from(final User user) {
        return new ListUserOutput(
                user.getId().getValue(),
                user.getName(),
                user.getDocument(),
                user.getEmail(),
                user.getPhone(),
                user.getBirthDate() != null ? user.getBirthDate().toString() : null,
                user.isActive()
        );
    }
}
