package br.com.api.rest.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.api.entity.UserEntity;
import br.com.api.entity.UserProfile;
import br.com.api.repository.ProfileRepository;
import br.com.api.repository.UserProfileRepository;
import br.com.api.repository.UserRepository;
import br.com.api.rest.request.UserRequest;
import br.com.api.rest.resource.UserResource;
import br.com.api.rest.response.AllUsersResponse;
import br.com.api.rest.response.GenericResponse;
import br.com.api.rest.response.UserResponse;
import br.com.api.service.UserService;
import br.com.api.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
public class UserControllerTest {
	
	@Inject
	UserResource resource;
	
	@Inject
	TestUtils utils;
	
	@Mock
	UserService userServiceMock;
	
	@MockBean
	UserRepository userRepositoryMock;
	@MockBean
	ProfileRepository profileRepositoryMock;
	@MockBean
	UserProfileRepository userProfilerepositoryMock;

	@Test
	public void saveUserTest() {
		given( userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()) )
			.willReturn( Optional.empty() );
		
		given( profileRepositoryMock.findByNameIgnoreCase(Mockito.anyString()) )
			.willReturn( utils.generateOneProfileOptional().get() );
		
		given( userProfilerepositoryMock.save(Mockito.any(UserProfile.class)) )
			.willReturn( utils.generateOneUserProfileOptional().get() );
		
		given( userRepositoryMock.save(Mockito.any(UserEntity.class)) )
			.willReturn( utils.generateOneUsertEntityOptional().get() );
		
		when( userServiceMock.saveUser(Mockito.any(UserRequest.class)) )
			.thenReturn( utils.generateOneUserResponse() );
		
		ResponseEntity<UserResponse> response = this.resource.saveUser( utils.generateOneUserRequest() );
		
		assertThat(response.getBody().getId() == null, equalTo(false));
	}
	
	@Test
	public void saveUserWithEmailAlreadyRegisteredTest() {
		given( userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()) )
			.willReturn( utils.generateOneUsertEntityOptional() );
		
		when( userServiceMock.saveUser(Mockito.any(UserRequest.class)) )
			.thenReturn( new UserResponse() );
		
		ResponseEntity<UserResponse> response = this.resource.saveUser( utils.generateOneUserRequest() );
		
		assertThat(response.getBody().getId() == null, equalTo(true));
	}

	@Test
	public void updateByIdTest() {
		given( userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()) )
			.willReturn( Optional.empty() );
		
		given( userRepositoryMock.save(Mockito.any(UserEntity.class)) )
			.willReturn( utils.generateOneUsertEntityOptional().get() );
		
		given( userRepositoryMock.findById(Mockito.anyLong()) )
			.willReturn( utils.generateOneUsertEntityOptional() );
		
		when( userServiceMock.updateUserEmail(Mockito.any(UserRequest.class), Mockito.anyLong()) )
			.thenReturn( utils.generateOneGenericResponse(true) );
		
		ResponseEntity<GenericResponse> response = this.resource.updateById(utils.generateOneUserRequest(), 1L);
		
		assertThat(response.getBody().getUpdated(), equalTo(true));
		
	}
	
	@Test
	public void updateByIdWithAlreadyRegisteredEmailTest() {
		given( userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()) )
			.willReturn( utils.generateOneUsertEntityOptional() );
		
		when( userServiceMock.updateUserEmail(Mockito.any(UserRequest.class), Mockito.anyLong()) )
			.thenReturn( utils.generateOneGenericResponse(false) );
		
		ResponseEntity<GenericResponse> response = this.resource.updateById(utils.generateOneUserRequest(), 1L);
		
		assertThat(response.getBody().getUpdated(), equalTo(false));
	}
	
	@Test
	public void updateByIdWithAlreadyRegisteredLoginTest() {
		given( userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()) )
			.willReturn( utils.generateOneUsertEntityOptional() );
		
		when( userServiceMock.updateUserEmail(Mockito.any(UserRequest.class), Mockito.anyLong()) )
			.thenReturn( utils.generateOneGenericResponse(false) );
		
		ResponseEntity<GenericResponse> response = this.resource.updateById(utils.generateOneUserRequest(), 1L);
		
		assertThat(response.getBody().getUpdated(), equalTo(false));
	}

	@Test
	public void testFindAllUsers() {
		given( userRepositoryMock.findAll() ).willReturn( utils.generateOneListOfUsers() );
		
		when( userServiceMock.findAllUsers() ).thenReturn( utils.generateOneAllUsersResponse() );
		
		ResponseEntity<AllUsersResponse> response = this.resource.findAllUsers();
		
		assertThat(response.getBody().getUsers().isEmpty(), equalTo(false));
	}
}
