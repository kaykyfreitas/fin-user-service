package dev.kaykyfreitas.finuserservice.finuserservice.domain.user;

import dev.kaykyfreitas.finuserservice.finuserservice.domain.validation.DomainError;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.validation.ValidationHandler;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.validation.Validator;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class UserValidator extends Validator {

    private static final Integer NAME_MAX_LENGTH = 100;
    private static final Integer NAME_MIN_LENGTH = 3;

    private static final Integer CPF_LENGTH = 11;

    private static final Integer EMAIL_MAX_LENGTH = 100;
    private static final String EMAIL_FORMAT_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    
    private static final Integer PHONE_MIN_LENGTH = 8;
    private static final Integer PHONE_MAX_LENGTH = 11;
    private static final String PHONE_FORMAT_REGEX = "^\\d+$";
    
    private static final Integer MIN_AGE = 18;
    
    private final User user;
    
    protected UserValidator(User user, ValidationHandler handler) {
        super(handler);
        this.user = user;
    }

    @Override
    public void validate() {
        validateNameConstraints();
        validateDocumentConstraints();
        validateEmailConstraints();
        validatePhoneConstraints();
        validateBirthDateConstraints();
    }
    
    private void validateNameConstraints() {
        final var name = this.user.getName();
        if (Objects.isNull(name)) {
            this.validationHandler().append(new DomainError("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new DomainError("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            final String msg = "'name' must be between %d and %d characters";
            this.validationHandler().append(new DomainError(String.format(msg, NAME_MIN_LENGTH, NAME_MAX_LENGTH)));
        }
    }
    
    private void validateDocumentConstraints() {
        final String cpf = user.getDocument();

        if (Objects.isNull(cpf)) {
            this.validationHandler().append(new DomainError("'document' should not be null"));
            return;
        }

        if (cpf.isBlank()) {
            this.validationHandler().append(new DomainError("'document' should not be empty"));
            return;
        }

        if (!cpf.matches("^\\d+$")) {
            this.validationHandler().append(new DomainError("'document' should contain only digits"));
            return;
        }

        final int length = cpf.trim().length();
        if (length != CPF_LENGTH) {
            final String msg = "'document' must have %s digits";
            this.validationHandler().append(new DomainError(String.format(msg, CPF_LENGTH)));
        }
    }
    
    private void validateEmailConstraints() {
        final var email = this.user.getEmail();
        if (Objects.isNull(email)) {
            this.validationHandler().append(new DomainError("'email' should not be null"));
            return;
        }

        if (email.isBlank()) {
            this.validationHandler().append(new DomainError("'email' should not be empty"));
            return;
        }

        if (!email.matches(EMAIL_FORMAT_REGEX)) {
            this.validationHandler().append(new DomainError("'email' must be a valid email address"));
        }

        final int length = email.trim().length();
        if (length > EMAIL_MAX_LENGTH) {
            final String msg = "'email' size limit is %d characters";
            this.validationHandler().append(new DomainError(String.format(msg, EMAIL_MAX_LENGTH)));
        }
    }
    
    private void validatePhoneConstraints() {
        final var phone = this.user.getPhone();
        if (Objects.isNull(phone)) {
            this.validationHandler().append(new DomainError("'phone' should not be null"));
            return;
        }

        if (phone.isBlank()) {
            this.validationHandler().append(new DomainError("'phone' should not be empty"));
            return;
        }

        if (!phone.matches(PHONE_FORMAT_REGEX)) {
            this.validationHandler().append(new DomainError("'phone' should contain only digits"));
            return;
        }

        final int length = phone.trim().length();
        if (length < PHONE_MIN_LENGTH || length > PHONE_MAX_LENGTH) {
            final String msg = "'phone' must be between %d and %d digits";
            this.validationHandler().append(new DomainError(String.format(msg, PHONE_MIN_LENGTH, PHONE_MAX_LENGTH)));
        }
    }
    
    private void validateBirthDateConstraints() {
        final var birthDate = this.user.getBirthDate();
        if (Objects.isNull(birthDate)) {
            this.validationHandler().append(new DomainError("'birthDate' should not be null"));
            return;
        }

        final var now = LocalDate.now();
        
        if (birthDate.isAfter(now)) {
            this.validationHandler().append(new DomainError("'birthDate' should not be in the future"));
            return;
        }
        
        final var age = Period.between(birthDate, now).getYears();
        if (age < MIN_AGE) {
            final String msg = "User must be at least %d years old";
            this.validationHandler().append(new DomainError(String.format(msg, MIN_AGE)));
        }
    }
    
}
