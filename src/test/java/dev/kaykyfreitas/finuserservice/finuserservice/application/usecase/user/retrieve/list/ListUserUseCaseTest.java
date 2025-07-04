package dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.retrieve.list;

import dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.UseCaseTest;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.pagination.Pagination;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.pagination.SearchQuery;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.User;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.user.UserGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ListUserUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultListUserUseCase useCase;

    @Mock
    private UserGateway userGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(userGateway);
    }

    @Test
    void givenValidQuery_whenCallsListUsers_shouldReturnAll() {
        // Given
        final var john = User.newUser(
                "John Doe",
                "12345678901",
                "john.doe@example.com",
                "11987654321",
                LocalDate.of(1990, 1, 1)
        );

        final var jane = User.newUser(
                "Jane Doe",
                "98765432101",
                "jane.doe@example.com",
                "11123456789",
                LocalDate.of(1990, 1, 1)
        );

        final var users = List.of(john, jane);

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "createdAt";
        final var expectedDirection = "asc";
        final var expectedTotal = 2;

        final var expectedItems = users.stream()
                .map(ListUserOutput::from)
                .toList();

        final var expectedPagination = new Pagination<>(
                expectedPage,
                expectedPerPage,
                expectedTotal,
                users
        );

        when(userGateway.getAll(any()))
                .thenReturn(expectedPagination);

        final var query = new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        // When
        final var actualOutput = useCase.execute(query);

        // Then
        Assertions.assertEquals(expectedPage, actualOutput.currentPage());
        Assertions.assertEquals(expectedPerPage, actualOutput.perPage());
        Assertions.assertEquals(expectedTotal, actualOutput.total());
        Assertions.assertEquals(expectedItems, actualOutput.items());

        verify(userGateway, times(1)).getAll(any());
        verifyNoMoreInteractions(userGateway);
    }

    @Test
    void givenValidQuery_whenCallsListUsersAndIsEmpty_shouldReturnEmpty() {
        // Given
        final var users = List.<User>of();

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "createdAt";
        final var expectedDirection = "asc";
        final var expectedTotal = 0;

        final var expectedItems = List.<ListUserOutput>of();

        final var expectedPagination = new Pagination<>(
                expectedPage,
                expectedPerPage,
                expectedTotal,
                users
        );

        when(userGateway.getAll(any()))
                .thenReturn(expectedPagination);

        final var query = new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        // When
        final var actualOutput = useCase.execute(query);

        // Then
        Assertions.assertEquals(expectedPage, actualOutput.currentPage());
        Assertions.assertEquals(expectedPerPage, actualOutput.perPage());
        Assertions.assertEquals(expectedTotal, actualOutput.total());
        Assertions.assertEquals(expectedItems, actualOutput.items());

        verify(userGateway, times(1)).getAll(any());
        verifyNoMoreInteractions(userGateway);
    }

    @Test
    void givenValidQuery_whenCallsListUsersAndGatewayThrowsAnException_shouldReturnError() {
        // Given
        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "jo";
        final var expectedSort = "createdAt";
        final var expectedDirection = "asc";

        final var expectedErrorMessage = "Gateway error";

        when(userGateway.getAll(any()))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var query = new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        // When
        final var actualException = assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(query)
        );

        // Then
        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

        verify(userGateway, times(1)).getAll(any());
        verifyNoMoreInteractions(userGateway);
    }

}