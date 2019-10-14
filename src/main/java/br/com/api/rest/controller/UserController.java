package br.com.api.rest.controller;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.rest.request.UserRequest;
import br.com.api.rest.resource.UserResource;
import br.com.api.rest.response.AllUsersResponse;
import br.com.api.rest.response.GenericResponse;
import br.com.api.rest.response.UserResponse;
import br.com.api.service.UserService;

@RestController
@RequestMapping(value = "/api/users")
public class UserController implements UserResource {
	
	private UserService service;

	@Inject
	public UserController(UserService service) {
		this.service = service;
	}

	@Override
	public ResponseEntity<UserResponse> saveUser(@RequestBody @Validated UserRequest userRequest) {
		return ResponseEntity.ok( service.saveUser(userRequest) );
	}
	
	@Override
	public ResponseEntity<GenericResponse> updateById(@RequestBody @Validated UserRequest userRequest, Long id) {
		return ResponseEntity.ok( service.updateUserEmail(userRequest, id) );
	}

	@Override
	public ResponseEntity<AllUsersResponse> findAllUsers() {
		return ResponseEntity.ok( service.findAllUsers() );
	}
}
