package dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.retrieve.list;

import dev.kaykyfreitas.finuserservice.finuserservice.application.UseCase;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.pagination.Pagination;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.pagination.SearchQuery;

public abstract class ListUserUseCase extends UseCase<SearchQuery, Pagination<ListUserOutput>> {
}
