package dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.create;

import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.User;

import java.time.Instant;

public record CreateUserOutput(
        String id,
        String name,
        String document,
        String email,
        String phone,
        String birthDate,
        boolean active,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {
    public static CreateUserOutput from(final User user) {
        return new CreateUserOutput(
                user.getId().getValue(),
                user.getName(),
                user.getDocument(),
                user.getEmail(),
                user.getPhone(),
                user.getBirthDate() != null ? user.getBirthDate().toString() : null,
                user.isActive(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt()
        );
    }
}
