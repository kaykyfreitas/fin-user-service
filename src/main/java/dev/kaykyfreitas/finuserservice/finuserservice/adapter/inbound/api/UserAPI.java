package dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api;

import dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.model.request.CreateUserRequest;
import dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.model.response.CreateUserResponse;
import dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.model.response.GetUserByIdResponse;
import dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.model.response.ListUserResponse;
import dev.kaykyfreitas.finuserservice.finuserservice.adapter.inbound.api.model.response.UpdateUserResponse;
import dev.kaykyfreitas.finuserservice.finuserservice.application.usecase.user.retrieve.list.ListUserOutput;
import dev.kaykyfreitas.finuserservice.finuserservice.domain.pagination.Pagination;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("users")
public interface UserAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<CreateUserResponse> create(@RequestBody CreateUserRequest request);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<UpdateUserResponse> update(@PathVariable String id, @RequestBody CreateUserRequest request);

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<GetUserByIdResponse> getById(@PathVariable String id);

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Pagination<ListUserResponse>> list(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction
    );

    @DeleteMapping(
            value = "{id}"
    )
    ResponseEntity<Void> delete(@PathVariable String id);

}
