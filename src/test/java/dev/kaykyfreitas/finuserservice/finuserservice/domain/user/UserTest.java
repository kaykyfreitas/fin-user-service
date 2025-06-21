package dev.kaykyfreitas.finuserservice.finuserservice.domain.user;

import dev.kaykyfreitas.finuserservice.finuserservice.domain.exception.NotificationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    
    @Test
    void givenValidParams_whenCreateNewUser_thenReturnUser() {
        final var expectedName = "John Doe";
        final var expectedDocument = "12345678901";
        final var expectedEmail = "john.doe@example.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);
        
        final var actualUser = User.newUser(
                expectedName,
                expectedDocument,
                expectedEmail,
                expectedPhone,
                expectedBirthDate
        );
        
        assertNotNull(actualUser);
        assertNotNull(actualUser.getId());
        assertEquals(expectedName, actualUser.getName());
        assertEquals(expectedDocument, actualUser.getDocument());
        assertEquals(expectedEmail, actualUser.getEmail());
        assertEquals(expectedPhone, actualUser.getPhone());
        assertEquals(expectedBirthDate, actualUser.getBirthDate());
        assertTrue(actualUser.isActive());
        assertNotNull(actualUser.getCreatedAt());
        assertNotNull(actualUser.getUpdatedAt());
        assertNull(actualUser.getDeletedAt());
    }

    @Test
    void givenInvalidNullName_whenCallNewUser_thenShouldReceiveError() {
        final String expectedName = null;
        final var expectedDocument = "12345678901";
        final var expectedEmail = "john.doe@example.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> User.newUser(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenInvalidEmptyName_whenCallNewUser_thenShouldReceiveError() {
        final String expectedName = "  ";
        final var expectedDocument = "12345678901";
        final var expectedEmail = "john.doe@example.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> User.newUser(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenInvalidNameLengthLessThan3_whenCallNewUser_thenShouldReceiveError() {
        final String expectedName = "Jo";
        final var expectedDocument = "12345678901";
        final var expectedEmail = "john.doe@example.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 100 characters";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> User.newUser(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenInvalidNameLengthMoreThan100_whenCallNewUser_thenShouldReceiveError() {
        final String expectedName = "J".repeat(101);
        final var expectedDocument = "12345678901";
        final var expectedEmail = "john.doe@example.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 100 characters";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> User.newUser(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenInvalidNullDocument_whenCallNewUser_thenShouldReceiveError() {
        final var expectedName = "John Doe";
        final String expectedDocument = null;
        final var expectedEmail = "john.doe@example.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'document' should not be null";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> User.newUser(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenInvalidEmptyDocument_whenCallNewUser_thenShouldReceiveError() {
        final var expectedName = "John Doe";
        final String expectedDocument = "  ";
        final var expectedEmail = "john.doe@example.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'document' should not be empty";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> User.newUser(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenInvalidDocumentLength_whenCallNewUser_thenShouldReceiveError() {
        final var expectedName = "John Doe";
        final String expectedDocument = "123456";
        final var expectedEmail = "john.doe@example.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'document' must have 11 digits";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> User.newUser(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenInvalidDocumentWithNonDigits_whenCallNewUser_thenShouldReceiveError() {
        final var expectedName = "John Doe";
        final String expectedDocument = "1234567890A";
        final var expectedEmail = "john.doe@example.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'document' should contain only digits";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> User.newUser(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenInvalidNullEmail_whenCallNewUser_thenShouldReceiveError() {
        final var expectedName = "John Doe";
        final var expectedDocument = "12345678901";
        final String expectedEmail = null;
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'email' should not be null";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> User.newUser(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenInvalidEmptyEmail_whenCallNewUser_thenShouldReceiveError() {
        final var expectedName = "John Doe";
        final var expectedDocument = "12345678901";
        final String expectedEmail = "  ";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'email' should not be empty";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> User.newUser(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenInvalidEmailFormat_whenCallNewUser_thenShouldReceiveError() {
        final var expectedName = "John Doe";
        final var expectedDocument = "12345678901";
        final String expectedEmail = "invalid-email";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'email' must be a valid email address";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> User.newUser(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenInvalidNullPhone_whenCallNewUser_thenShouldReceiveError() {
        final var expectedName = "John Doe";
        final var expectedDocument = "12345678901";
        final var expectedEmail = "john.doe@example.com";
        final String expectedPhone = null;
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'phone' should not be null";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> User.newUser(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenInvalidEmptyPhone_whenCallNewUser_thenShouldReceiveError() {
        final var expectedName = "John Doe";
        final var expectedDocument = "12345678901";
        final var expectedEmail = "john.doe@example.com";
        final String expectedPhone = "  ";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'phone' should not be empty";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> User.newUser(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenInvalidPhoneWithNonDigits_whenCallNewUser_thenShouldReceiveError() {
        final var expectedName = "John Doe";
        final var expectedDocument = "12345678901";
        final var expectedEmail = "john.doe@example.com";
        final String expectedPhone = "1198A654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'phone' should contain only digits";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> User.newUser(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenInvalidPhoneTooShort_whenCallNewUser_thenShouldReceiveError() {
        final var expectedName = "John Doe";
        final var expectedDocument = "12345678901";
        final var expectedEmail = "john.doe@example.com";
        final String expectedPhone = "1234567";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'phone' must be between 8 and 11 digits";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> User.newUser(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenInvalidNullBirthDate_whenCallNewUser_thenShouldReceiveError() {
        final var expectedName = "John Doe";
        final var expectedDocument = "12345678901";
        final var expectedEmail = "john.doe@example.com";
        final var expectedPhone = "11987654321";
        final LocalDate expectedBirthDate = null;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'birthDate' should not be null";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> User.newUser(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenInvalidFutureBirthDate_whenCallNewUser_thenShouldReceiveError() {
        final var expectedName = "John Doe";
        final var expectedDocument = "12345678901";
        final var expectedEmail = "john.doe@example.com";
        final var expectedPhone = "11987654321";
        final LocalDate expectedBirthDate = LocalDate.now().plusDays(1);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'birthDate' should not be in the future";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> User.newUser(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenInvalidUserTooYoung_whenCallNewUser_thenShouldReceiveError() {
        final var expectedName = "John Doe";
        final var expectedDocument = "12345678901";
        final var expectedEmail = "john.doe@example.com";
        final var expectedPhone = "11987654321";
        final LocalDate expectedBirthDate = LocalDate.now().minusYears(17);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "User must be at least 18 years old";

        final var actualException = assertThrows(
                NotificationException.class,
                () -> User.newUser(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenAInactiveUser_whenCallActivate_thenShouldActivateUser() {
        final var expectedName = "John Doe";
        final var expectedDocument = "12345678901";
        final var expectedEmail = "john.doe@example.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);
        
        final var aUser = User.newUser(
                expectedName,
                expectedDocument,
                expectedEmail,
                expectedPhone,
                expectedBirthDate
        );
        
        // First deactivate the user
        final var updatedAt = aUser.getUpdatedAt();
        aUser.deactivate();
        
        // Then activate it again
        final var actualUser = aUser.activate();
        
        assertNotNull(actualUser);
        assertTrue(actualUser.isActive());
        assertNull(actualUser.getDeletedAt());
        assertTrue(actualUser.getUpdatedAt().isAfter(updatedAt));
    }
    
    @Test
    void givenAnActiveUser_whenCallDeactivate_thenShouldDeactivateUser() {
        final var expectedName = "John Doe";
        final var expectedDocument = "12345678901";
        final var expectedEmail = "john.doe@example.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);
        
        final var aUser = User.newUser(
                expectedName,
                expectedDocument,
                expectedEmail,
                expectedPhone,
                expectedBirthDate
        );
        
        final var updatedAt = aUser.getUpdatedAt();
        
        // Act
        final var actualUser = aUser.deactivate();
        
        // Assert
        assertNotNull(actualUser);
        assertFalse(actualUser.isActive());
        assertNotNull(actualUser.getDeletedAt());
        assertTrue(actualUser.getUpdatedAt().isAfter(updatedAt));
    }
    
    @Test
    void givenAValidUser_whenCallUpdate_thenShouldUpdateUser() {
        final var expectedName = "John Doe";
        final var expectedDocument = "12345678901";
        final var expectedEmail = "john.doe@example.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);
        
        final var aUser = User.newUser(
                "Old Name",
                "98765432101",
                "old.email@example.com",
                "11912345678",
                LocalDate.of(1985, 5, 10)
        );
        
        final var createdAt = aUser.getCreatedAt();
        final var updatedAt = aUser.getUpdatedAt();
        
        // Act
        final var actualUser = aUser.update(
                expectedName,
                expectedDocument,
                expectedEmail,
                expectedPhone,
                expectedBirthDate
        );
        
        // Assert
        assertNotNull(actualUser);
        assertEquals(expectedName, actualUser.getName());
        assertEquals(expectedDocument, actualUser.getDocument());
        assertEquals(expectedEmail, actualUser.getEmail());
        assertEquals(expectedPhone, actualUser.getPhone());
        assertEquals(expectedBirthDate, actualUser.getBirthDate());
        assertTrue(actualUser.isActive());
        assertEquals(createdAt, actualUser.getCreatedAt());
        assertTrue(actualUser.getUpdatedAt().isAfter(updatedAt));
        assertNull(actualUser.getDeletedAt());
    }
    
    @Test
    void givenAnInvalidName_whenCallUpdate_thenShouldThrowNotificationException() {
        final String expectedName = null;
        final var expectedDocument = "12345678901";
        final var expectedEmail = "john.doe@example.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);
        
        final var aUser = User.newUser(
                "Old Name",
                "98765432101",
                "old.email@example.com",
                "11912345678",
                LocalDate.of(1985, 5, 10)
        );
        
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";
        
        // Act & Assert
        final var actualException = assertThrows(
                NotificationException.class,
                () -> aUser.update(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );
        
        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    void givenAMultipleInvalidParams_whenCallUpdate_thenShouldReceiveAllErrors() {
        final String expectedName = null;
        final String expectedDocument = null;
        final var expectedEmail = "invalid-email";
        final var expectedPhone = "abc"; // invalid phone
        final var expectedBirthDate = LocalDate.now().plusDays(1); // future date

        final var aUser = User.newUser(
                "Old Name",
                "98765432101",
                "old.email@example.com",
                "11912345678",
                LocalDate.of(1985, 5, 10)
        );

        final var expectedErrorCount = 5;

        // Act & Assert
        final var actualException = assertThrows(
                NotificationException.class,
                () -> aUser.update(
                        expectedName,
                        expectedDocument,
                        expectedEmail,
                        expectedPhone,
                        expectedBirthDate
                )
        );

        assertEquals(expectedErrorCount, actualException.getErrors().size());
    }
    
    @Test
    void givenDeactivatedUser_whenUpdate_thenShouldUpdateAndKeepUserDeactivated() {
        final var expectedName = "John Doe";
        final var expectedDocument = "12345678901";
        final var expectedEmail = "john.doe@example.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);
        
        final var aUser = User.newUser(
                "Old Name",
                "98765432101",
                "old.email@example.com",
                "11912345678",
                LocalDate.of(1985, 5, 10)
        );
        
        aUser.deactivate();
        
        final var deletedAt = aUser.getDeletedAt();
        final var updatedAt = aUser.getUpdatedAt();
        
        // Act
        final var actualUser = aUser.update(
                expectedName,
                expectedDocument,
                expectedEmail,
                expectedPhone,
                expectedBirthDate
        );
        
        // Assert
        assertNotNull(actualUser);
        assertEquals(expectedName, actualUser.getName());
        assertEquals(expectedDocument, actualUser.getDocument());
        assertEquals(expectedEmail, actualUser.getEmail());
        assertEquals(expectedPhone, actualUser.getPhone());
        assertEquals(expectedBirthDate, actualUser.getBirthDate());
        assertFalse(actualUser.isActive());
        assertEquals(deletedAt, actualUser.getDeletedAt());
        assertTrue(actualUser.getUpdatedAt().isAfter(updatedAt));
    }
}

