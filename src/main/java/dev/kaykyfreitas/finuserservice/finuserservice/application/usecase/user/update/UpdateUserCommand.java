package dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.update;

import java.time.LocalDate;

public record UpdateUserCommand(
        String id,
        String name,
        String document,
        String email,
        String phone,
        LocalDate birthDate
) {
    public static UpdateUserCommand with(
            final String id,
            final String name,
            final String document,
            final String email,
            final String phone,
            final LocalDate birthDate
    ) {
        return new UpdateUserCommand(id, name, document, email, phone, birthDate);
    }
}
