package br.com.api.rest.resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.api.rest.request.UserRequest;
import br.com.api.rest.response.AllUsersResponse;
import br.com.api.rest.response.GenericResponse;
import br.com.api.rest.response.UserResponse;
import io.swagger.annotations.Api;

@Api(tags = "User")
public interface UserResource {

	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<UserResponse> saveUser(@RequestBody @Validated UserRequest userRequest);
	
	@GetMapping(value = "/")
	ResponseEntity<AllUsersResponse> findAllUsers();
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<GenericResponse> updateById(@RequestBody @Validated UserRequest userRequest, @PathVariable(value = "id") Long id );
}
