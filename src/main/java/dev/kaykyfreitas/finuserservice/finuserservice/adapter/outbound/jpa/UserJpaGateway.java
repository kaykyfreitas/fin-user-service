package dev.kaykyfreitas.finuserservice.finuserservice.adapter.outbound.jpa;

import dev.kaykyfreitas.finuserservice.finuserservice.adapter.outbound.jpa.entity.UserJpaEntity;
import dev.kaykyfreitas.finuserservice.finuserservice.adapter.outbound.jpa.mapper.UserJpaMapper;
import dev.kaykyfreitas.finuserservice.finuserservice.adapter.outbound.jpa.repository.UserJpaRepository;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.pagination.Pagination;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.pagination.SearchQuery;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.User;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.UserGateway;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class UserJpaGateway implements UserGateway {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User create(final User user) {
        return save(user);
    }

    @Override
    public Optional<User> getById(final UserId id) {
        return userJpaRepository.findById(id.getValue()).map(UserJpaMapper::fromJpa);
    }

    @Override
    public Pagination<User> getAll(final SearchQuery query) {
        final var page = PageRequest.of(
                query.page(),
                query.perPage(),
                Sort.Direction.fromString(query.direction()),
                query.sort()
        );

        if (isNull(query.terms())) {
            final var pageResult = userJpaRepository.findAll(page);

            return new Pagination<>(
                    pageResult.getNumber(),
                    pageResult.getSize(),
                    pageResult.getTotalElements(),
                    pageResult.map(UserJpaMapper::fromJpa).toList()
            );
        }

        final var example = assembleExample(query.terms());

        final var pageResult = this.userJpaRepository.findAll(example, page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(UserJpaMapper::fromJpa).toList()
        );
    }

    @Override
    public User update(final User user) {
        return save(user);
    }

    @Override
    public void delete(final UserId id) {
        if (userJpaRepository.existsById(id.getValue()))
            userJpaRepository.deleteById(id.getValue());
    }

    private User save(final User user) {
        return UserJpaMapper.fromJpa(userJpaRepository.save(UserJpaMapper.toJpa(user)));
    }

    private Example<UserJpaEntity> assembleExample(final String terms) {
        final var userExample = new UserJpaEntity();
        userExample.setName(terms);
        userExample.setDocument(terms);
        userExample.setEmail(terms);
        userExample.setPhone(terms);

        final var matcher = ExampleMatcher.matchingAny()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return Example.of(userExample, matcher);
    }

}
