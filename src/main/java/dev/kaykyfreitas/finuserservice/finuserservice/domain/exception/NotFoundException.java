package dev.kaykyfreitas.finuserservice.finuserservice.domain.exception;

import dev.kaykyfreitas.finuserservice.finuserservice.domain.Entity;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.Identifier;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.validation.DomainError;

import java.util.List;

public class NotFoundException extends DomainException{

    protected NotFoundException(final String aMessage, final List<DomainError> someErrors) {
        super(aMessage, someErrors);
    }

    public static NotFoundException with(
            final Class<? extends Entity<?>> entity,
            final Identifier id
    ) {
        final var errorMessage = "%s with id %s was not found".formatted(
                entity.getSimpleName().toLowerCase(),
                id.getValue()
        );
        final var domainError = new DomainError(errorMessage);
        return new NotFoundException(errorMessage, List.of(domainError));
    }

}
