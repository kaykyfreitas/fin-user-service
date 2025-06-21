package dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.create;

import java.time.LocalDate;

public record CreateUserCommand(
        String name,
        String document,
        String email,
        String phone,
        LocalDate birthDate
) {
    public static CreateUserCommand with(
            final String name,
            final String document,
            final String email,
            final String phone,
            final LocalDate birthDate
    ) {
        return new CreateUserCommand(name, document, email, phone, birthDate);
    }
}
