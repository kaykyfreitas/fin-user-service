package dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.update;

import dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.UseCaseTest;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.exception.NotificationException;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.exception.NotFoundException;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.User;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.UserGateway;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.UserId;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateUserUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultUpdateUserUseCase useCase;

    @Mock
    private UserGateway userGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(userGateway);
    }

    @Test
    void givenValidCommand_whenCallUpdateUser_shouldReturnUser() {
        // Given
        final var id = UserId.unique();
        final var existingUser = User.with(
                id,
                "Old Name",
                "11111111111",
                "old@email.com",
                "11999999999",
                LocalDate.of(1990, 1, 1),
                true,
                Instant.now(),
                Instant.now(),
                null
        );

        final var expectedName = "John Doe";
        final var expectedDocument = "12345678900";
        final var expectedEmail = "john.doe@email.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(2000, 1, 1);

        final var aCommand = UpdateUserCommand.with(
                id.getValue(),
                expectedName,
                expectedDocument,
                expectedEmail,
                expectedPhone,
                expectedBirthDate
        );

        when(userGateway.getById(id)).thenReturn(Optional.of(existingUser));
        when(userGateway.update(any())).thenAnswer(returnsFirstArg());

        // When
        final var actualOutput = useCase.execute(aCommand);

        // Then
        assertNotNull(actualOutput);
        assertEquals(expectedName, actualOutput.name());
        assertEquals(expectedDocument, actualOutput.document());
        assertEquals(expectedEmail, actualOutput.email());
        assertEquals(expectedPhone, actualOutput.phone());
        assertEquals(expectedBirthDate.toString(), actualOutput.birthDate());
        assertTrue(actualOutput.active());
        assertNotNull(actualOutput.createdAt());
        assertNotNull(actualOutput.updatedAt());
        verify(userGateway, times(1)).getById(id);
        verify(userGateway, times(1)).update(any());
        verifyNoMoreInteractions(userGateway);
    }

    @Test
    void givenAnInvalidCommand_whenCallUpdateUser_shouldReceiveError() {
        // Given
        final var id = UserId.unique();
        final var existingUser = User.with(
                id,
                "Old Name",
                "11111111111",
                "old@email.com",
                "11999999999",
                LocalDate.of(1990, 1, 1),
                true,
                Instant.now(),
                Instant.now(),
                null
        );

        final var aCommand = UpdateUserCommand.with(
                id.getValue(),
                "",
                "12345678900",
                "john.doe@email.com",
                "11987654321",
                LocalDate.of(2000, 1, 1)
        );

        when(userGateway.getById(id)).thenReturn(Optional.of(existingUser));

        // When
        final var actualException = assertThrows(
                NotificationException.class,
                () -> useCase.execute(aCommand)
        );

        // Then
        assertNotNull(actualException);
        assertFalse(actualException.getErrors().isEmpty());
        verify(userGateway, times(1)).getById(id);
        verifyNoMoreInteractions(userGateway);
    }

    @Test
    void givenAValidCommand_whenGatewayThrowsRandomException_thenShouldReturnAnException() {
        // Given
        final var id = UserId.unique();
        final var existingUser = User.with(
                id,
                "Old Name",
                "11111111111",
                "old@email.com",
                "11999999999",
                LocalDate.of(1990, 1, 1),
                true,
                Instant.now(),
                Instant.now(),
                null
        );

        final var aCommand = UpdateUserCommand.with(
                id.getValue(),
                "John Doe",
                "12345678900",
                "john.doe@email.com",
                "11987654321",
                LocalDate.of(2000, 1, 1)
        );

        when(userGateway.getById(id)).thenReturn(Optional.of(existingUser));

        final var expectedErrorMessage = "gateway error";

        doThrow(new IllegalStateException(expectedErrorMessage)).when(userGateway).update(any());

        // When
        final var actualException = assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(aCommand)
        );

        // Then
        assertEquals(expectedErrorMessage, actualException.getMessage());
        verify(userGateway, times(1)).getById(id);
        verify(userGateway, times(1)).update(any());
        verifyNoMoreInteractions(userGateway);
    }

    @Test
    void givenNonexistentUserId_whenCallUpdateUser_shouldThrowNotFoundException() {
        // Given
        final var id = UserId.unique();
        final var aCommand = UpdateUserCommand.with(
                id.getValue(),
                "John Doe",
                "12345678900",
                "john.doe@email.com",
                "11987654321",
                LocalDate.of(2000, 1, 1)
        );

        when(userGateway.getById(id)).thenReturn(Optional.empty());

        // When
        final var actualException = assertThrows(
                NotFoundException.class,
                () -> useCase.execute(aCommand)
        );

        // Then
        assertNotNull(actualException);
        verify(userGateway, times(1)).getById(id);
        verifyNoMoreInteractions(userGateway);
    }
}
