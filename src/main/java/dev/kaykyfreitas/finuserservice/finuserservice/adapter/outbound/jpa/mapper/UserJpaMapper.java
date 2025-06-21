package dev.kaykyfreitas.finuserservice.finuserservice.adapter.outbound.jpa.mapper;

import dev.kaykyfreitas.finuserservice.finuserservice.adapter.outbound.jpa.entity.UserJpaEntity;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.User;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.UserId;

public final class UserJpaMapper {

    private UserJpaMapper() {}

    public static User fromJpa(final UserJpaEntity userJpaEntity) {
        if (userJpaEntity == null) return null;

        return User.with(
                UserId.from(userJpaEntity.getId()),
                userJpaEntity.getName(),
                userJpaEntity.getDocument(),
                userJpaEntity.getEmail(),
                userJpaEntity.getPhone(),
                userJpaEntity.getBirthDate(),
                userJpaEntity.isActive(),
                userJpaEntity.getCreatedAt(),
                userJpaEntity.getUpdatedAt(),
                userJpaEntity.getDeletedAt()
        );
    }

    public static UserJpaEntity toJpa(final User user) {
        if (user == null) return null;

        return new UserJpaEntity(
                user.getId().getValue(),
                user.getName(),
                user.getDocument(),
                user.getEmail(),
                user.getPhone(),
                user.getBirthDate(),
                user.isActive(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt()
        );
    }

}
