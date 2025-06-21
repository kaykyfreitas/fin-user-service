package dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.model.response;

import dev.kaykyfreitas.finuserservice.finuserservice.domain.validation.DomainError;

import java.time.Instant;
import java.util.List;

public record DefaultErrorResponse(
        Instant timestamp,
        Integer status,
        List<DomainError> errors
) {
}
