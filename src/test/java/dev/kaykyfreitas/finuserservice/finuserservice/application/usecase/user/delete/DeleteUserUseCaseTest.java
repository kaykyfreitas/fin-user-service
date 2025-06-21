package dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.delete;

import dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.UseCaseTest;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.UserGateway;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.UserId;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteUserUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultDeleteUserUseCase useCase;

    @Mock
    private UserGateway userGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(userGateway);
    }

    @Test
    void givenAValidId_whenCallDeleteUser_shouldDeleteUser() {
        // Given
        final var validId = "123";
        final var userId = UserId.from(validId);

        // When
        useCase.execute(validId);

        // Then
        verify(userGateway, times(1)).delete(userId);
        verifyNoMoreInteractions(userGateway);
    }

    @Test
    void givenAValidId_whenGatewayThrowsRandomException_thenShouldReturnAnException() {
        // Given
        final var validId = "123";
        final var userId = UserId.from(validId);

        final var expectedErrorMessage = "gateway error";

        doThrow(new IllegalStateException(expectedErrorMessage)).when(userGateway).delete(userId);

        // When
        final var actualException = assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(validId)
        );

        // Then
        assertEquals(expectedErrorMessage, actualException.getMessage());

        verify(userGateway, times(1)).delete(userId);
        verifyNoMoreInteractions(userGateway);
    }

}