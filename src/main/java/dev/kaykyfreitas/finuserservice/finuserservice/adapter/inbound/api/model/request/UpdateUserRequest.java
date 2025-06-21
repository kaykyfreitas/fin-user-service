package dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.model.request;

import java.time.LocalDate;

public record UpdateUserRequest(
        String name,
        String document,
        String email,
        String phone,
        LocalDate birthDate
) {
}
