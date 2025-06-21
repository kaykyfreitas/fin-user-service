package dev.kaykyfreitas.finuserservice.finuserservice.domain.pagination;

public record SearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction
) {
}