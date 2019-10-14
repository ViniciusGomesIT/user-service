package br.com.api.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.api.entity.Profile;
import br.com.api.entity.UserEntity;
import br.com.api.entity.UserProfile;
import br.com.api.rest.request.UserRequest;
import br.com.api.rest.response.AllUsersResponse;
import br.com.api.rest.response.GenericResponse;
import br.com.api.rest.response.UserResponse;

@Component
public class TestUtils {

	public UserRequest generateOneUserRequest() {
		UserRequest request = new UserRequest();
		
		request.setEmail("some_email@email.com.br");
		request.setLogin("someLogin123");
		request.setNome("Some Nome Test");
		request.setSenha("123456");
		
		return request;
	}
	
	public List<UserEntity> generateOneListOfUsers() {
		return Arrays.asList(generateOneUsertEntityOptional().get(), generateOneUsertEntityOptional().get());
	}
	
	public Optional<UserEntity> generateOneUsertEntityOptional() {
		UserEntity user = new UserEntity();
		
		user.setId(1L);
		user.setEmail("some_email@email.com.br");
		user.setLogin("someLogin123");
		user.setNome("Some Nome Test");
		user.setSenha("123456");
		
		return Optional.of(user);
	}

	public GenericResponse generateOneGenericResponse(boolean updated) {
		GenericResponse response = new GenericResponse();
		
		response.setData("Some error Message");
		response.setUpdated(updated);
		
		return response;
	}

	public AllUsersResponse generateOneAllUsersResponse() {
		AllUsersResponse response = new AllUsersResponse();
		
		response.setUsers(Arrays.asList(generateOneUserResponse(), generateOneUserResponse()));
		
		return response;
	}
	
	public UserResponse generateOneUserResponse() {
		UserResponse response = new UserResponse();
		
		response.setId(1L);
		response.setEmail("some_email@email.com.br");
		response.setLogin("someLogin123");
		response.setNome("Some Nome Test");
		response.setSenha("123456");
		
		return response;
	}

	public Optional<Profile> generateOneProfileOptional() {
		Profile profile = new Profile();
		
		profile.setAuthorityId(1L);
		profile.setName("ROLE_USER");
		
		return Optional.of(profile);
	}

	public Optional<UserProfile> generateOneUserProfileOptional() {
		UserProfile userProfile = new UserProfile();
		
		userProfile.setId(1L);
		userProfile.setProfile(generateOneProfileOptional().get());
		userProfile.setUserEntity(generateOneUsertEntityOptional().get());
		
		return Optional.of(userProfile);
	}

}
