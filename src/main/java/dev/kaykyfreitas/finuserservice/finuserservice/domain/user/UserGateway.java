package dev.kaykyfreitas.finuserservice.finuserservice.domain.user;

import dev.kaykyfreitas.finuserservice.finuserservice.domain.pagination.Pagination;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.pagination.SearchQuery;

import java.util.Optional;

public interface UserGateway {
    User create(User user);
    Optional<User> getById(UserId id);
    Pagination<User> getAll(SearchQuery query);
    User update(User user);
    void delete(UserId id);
}
