package dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.create;

import dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.UseCaseTest;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.exception.NotificationException;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.UserGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateUserUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultCreateUserUseCase useCase;

    @Mock
    private UserGateway userGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(userGateway);
    }

    @Test
    void givenValidCommand_whenCallCreateUser_shouldReturnUser() {
        // Given
        final var expectedName = "John Doe";
        final var expectedDocument = "12345678900";
        final var expectedEmail = "john.doe@email.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(2000, 1, 1);

        final var aCommand = CreateUserCommand.with(
                expectedName,
                expectedDocument,
                expectedEmail,
                expectedPhone,
                expectedBirthDate
        );

        when(userGateway.create(any())).thenAnswer(returnsFirstArg());

        // When
        final var actualOutput = useCase.execute(aCommand);

        // Then
        assertNotNull(actualOutput);
        assertNotNull(actualOutput.id());
        assertEquals(expectedName, actualOutput.name());
        assertEquals(expectedDocument, actualOutput.document());
        assertEquals(expectedEmail, actualOutput.email());
        assertEquals(expectedPhone, actualOutput.phone());
        assertEquals(expectedBirthDate.toString(), actualOutput.birthDate());
        assertTrue(actualOutput.active());
        assertNotNull(actualOutput.createdAt());
        assertNotNull(actualOutput.updatedAt());
        assertEquals(actualOutput.createdAt(), actualOutput.updatedAt());
        assertNull(actualOutput.deletedAt());

        verify(userGateway, times(1)).create(any());
        verifyNoMoreInteractions(userGateway);
    }

    @Test
    void givenAnInvalidCommand_whenCallCreateUser_shouldReceiveErro() {
        // Given
        final var expectedName = "";
        final var expectedDocument = "12345678900";
        final var expectedEmail = "john.doe@email.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(2000, 1, 1);

        final var aCommand = CreateUserCommand.with(
                expectedName,
                expectedDocument,
                expectedEmail,
                expectedPhone,
                expectedBirthDate
        );

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        // When
        final var actualException = assertThrows(
                NotificationException.class,
                () -> useCase.execute(aCommand)
        );

        // Then
        assertNotNull(actualException);
        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());

        verifyNoMoreInteractions(userGateway);
    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsRandomException_thenShouldReturnAnException() {
        // Given
        final var expectedName = "John Doe";
        final var expectedDocument = "12345678900";
        final var expectedEmail = "john.doe@email.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(2000, 1, 1);

        final var aCommand = CreateUserCommand.with(
                expectedName,
                expectedDocument,
                expectedEmail,
                expectedPhone,
                expectedBirthDate
        );

        final var expectedErrorMessage = "gateway error";

        doThrow(new IllegalStateException(expectedErrorMessage)).when(userGateway).create(any());

        // When
        final var actualException = assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(aCommand)
        );

        // Then
        assertEquals(expectedErrorMessage, actualException.getMessage());

        verify(userGateway, times(1)).create(any());
        verifyNoMoreInteractions(userGateway);
    }


}