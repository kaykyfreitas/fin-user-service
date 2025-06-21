package dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.retrieve.get;

import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.User;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.UserGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetUserByIdUseCaseTest {

    @InjectMocks
    private DefaultGetUserByIdUseCase useCase;

    @Mock
    private UserGateway userGateway;

    @Test
    void givenValidId_whenUserExists_shouldReturnUser() {
        // Given
        final var expectedName = "John Doe";
        final var expectedDocument = "12345678901";
        final var expectedEmail = "john.doe@example.com";
        final var expectedPhone = "11987654321";
        final var expectedBirthDate = LocalDate.of(1990, 1, 1);

        final var user = User.newUser(
                expectedName,
                expectedDocument,
                expectedEmail,
                expectedPhone,
                expectedBirthDate
        );

        final var expectedId = user.getId();

        when(userGateway.getById(expectedId)).thenReturn(Optional.of(user));

        // When
        final var output = useCase.execute(expectedId.getValue());

        // Then
        assertNotNull(output);
        assertEquals(expectedId.getValue(), output.id());
        assertEquals(expectedName, output.name());
        assertEquals(expectedDocument, output.document());
        assertEquals(expectedEmail, output.email());
        assertEquals(expectedPhone, output.phone());
        assertEquals(expectedBirthDate.toString(), output.birthDate());
        assertTrue(output.active());
        assertNotNull(output.createdAt());
        assertNotNull(output.updatedAt());

        verify(userGateway, times(1)).getById(expectedId);
        verifyNoMoreInteractions(userGateway);
    }

    @Test
    void givenAnInvalidUser_whenCallGetUserById_shouldReceiveError() {
        // Given
        final var expectedErrorMessage = "gateway error";

        doThrow(new IllegalStateException(expectedErrorMessage)).when(userGateway).getById(any());

        // When
        final var actualException = assertThrows(
                IllegalStateException.class,
                () -> useCase.execute("123")
        );

        // Then
        assertEquals(expectedErrorMessage, actualException.getMessage());

        verify(userGateway, times(1)).getById(any());
        verifyNoMoreInteractions(userGateway);
    }

}
