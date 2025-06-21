package dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.model.response;

import java.time.Instant;

public record GetUserByIdResponse(
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
}
