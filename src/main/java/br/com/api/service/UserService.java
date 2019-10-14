package br.com.api.service;

import br.com.api.rest.request.UserRequest;
import br.com.api.rest.response.AllUsersResponse;
import br.com.api.rest.response.GenericResponse;
import br.com.api.rest.response.UserResponse;

public interface UserService {
	
	UserResponse saveUser(UserRequest userRequest);
	
	GenericResponse updateUserEmail(UserRequest userRequest, Long id);
	
	AllUsersResponse findAllUsers();
}
