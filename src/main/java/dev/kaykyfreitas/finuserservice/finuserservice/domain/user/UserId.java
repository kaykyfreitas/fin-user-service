package dev.kaykyfreitas.finuserservice.finuserservice.domain.user;

import dev.kaykyfreitas.finuserservice.finuserservice.domain.Identifier;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.utils.IdUtils;

import java.util.Objects;

public class UserId extends Identifier {

    private final String id;

    private UserId(final String id) {
        this.id = id;
    }

    public static UserId from(final String id) {
        return new UserId(id);
    }

    public static UserId unique() {
        return new UserId(IdUtils.uuid());
    }

    @Override
    public String getValue() {
        return this.id;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final UserId userId = (UserId) o;
        return Objects.equals(id, userId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
