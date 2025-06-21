package dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.retrieve.list;

import dev.kaykyfreitas.finuserservice.finuserservice.domain.pagination.Pagination;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.pagination.SearchQuery;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultListUserUseCase extends ListUserUseCase {

    private final UserGateway userGateway;

    @Override
    public Pagination<ListUserOutput> execute(final SearchQuery query) {
        return userGateway.getAll(query)
                .map(ListUserOutput::from);
    }

}
