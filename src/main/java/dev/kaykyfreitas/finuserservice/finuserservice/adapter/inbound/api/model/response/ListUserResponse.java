package dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.model.response;

public record ListUserResponse(
        String id,
        String name,
        String document,
        String email,
        String phone,
        String birthDate,
        boolean active
) {
}
