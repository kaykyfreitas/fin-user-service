package dev.kaykyfreitas.finuserservice.finuserservice.domain.user;

import dev.kaykyfreitas.finuserservice.finuserservice.domain.AggregateRoot;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.exception.NotificationException;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.utils.InstantUtils;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.validation.ValidationHandler;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.validation.handler.Notification;

import java.time.Instant;
import java.time.LocalDate;

public class User extends AggregateRoot<UserId> {

    private String name;
    private String document;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private boolean active;

    private User(
            final UserId id,
            final String name,
            final String document,
            final String email,
            final String phone,
            final LocalDate birthDate,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        super(id, createdAt, updatedAt, deletedAt);
        this.name = name;
        this.document = document;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.active = active;
        this.selfValidation();
    }

    public static User newUser(
            final String name,
            final String document,
            final String email,
            final String phone,
            final LocalDate birthDate
    ) {
        final var id = UserId.unique();
        final var now = InstantUtils.now();
        return new User(id, name, document, email, phone, birthDate, true, now, now, null);
    }

    public static User with(
            final UserId id,
            final String name,
            final String document,
            final String email,
            final String phone,
            final LocalDate birthDate,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        return new User(id, name, document, email, phone, birthDate, active, createdAt, updatedAt, deletedAt);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new UserValidator(this, handler).validate();
    }

    private void selfValidation() {
        final var notification = Notification.create();

        this.validate(notification);

        if (notification.hasError())
            throw new NotificationException("failed to create user", notification);
    }

    public User activate() {
        this.active = true;
        this.deletedAt = null;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public User deactivate() {
        this.active = false;
        this.deletedAt = InstantUtils.now();
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public User update(
            final String name,
            final String document,
            final String email,
            final String phone,
            final LocalDate birthDate
    ) {
        this.name = name;
        this.document = document;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.updatedAt = InstantUtils.now();
        this.selfValidation();
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public boolean isActive() {
        return active;
    }

}
