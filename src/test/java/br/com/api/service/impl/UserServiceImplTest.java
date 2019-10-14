package br.com.api.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.api.entity.UserEntity;
import br.com.api.entity.UserProfile;
import br.com.api.enums.MessagesEnum;
import br.com.api.repository.ProfileRepository;
import br.com.api.repository.UserProfileRepository;
import br.com.api.repository.UserRepository;
import br.com.api.rest.response.AllUsersResponse;
import br.com.api.rest.response.GenericResponse;
import br.com.api.rest.response.UserResponse;
import br.com.api.service.UserService;
import br.com.api.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
public class UserServiceImplTest {
	
	@Inject
	private TestUtils utils;
	
	@Inject
	private UserService userService;
	
	@MockBean
	private UserRepository userRepositoryMock;
	@MockBean
	private ProfileRepository profileRepositoryMock;
	@MockBean
	private UserProfileRepository userProfileRepositoryMock;
	
	@Test
	public void saveUserTest() {
		given( userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()) )
			.willReturn( Optional.empty() );
		
		given( userRepositoryMock.save(Mockito.any(UserEntity.class)) )
			.willReturn( utils.generateOneUsertEntityOptional().get() );
		
		given( profileRepositoryMock.findByNameIgnoreCase(Mockito.anyString()) )
			.willReturn( utils.generateOneProfileOptional().get() );
		
		given( userProfileRepositoryMock.save(Mockito.any(UserProfile.class)) )
			.willReturn( utils.generateOneUserProfileOptional().get() );
		
		UserResponse response = this.userService.saveUser( utils.generateOneUserRequest() );
		
		assertThat(response.getId() == null, equalTo(false));
	}
	
	@Test
	public void saveUserWithAlreadyRegisteresEmailTest() {
		given( userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()) )
			.willReturn( utils.generateOneUsertEntityOptional() );
		
		UserResponse response = this.userService.saveUser( utils.generateOneUserRequest() );
		
		assertThat(response.getErros() == null, equalTo(false));
		assertThat(response.getId() == null, equalTo(true));
		
	}
	
	@Test
	public void saveUserWithAlreadyRegisteresLoginTest() {
		given( userRepositoryMock.findByLoginIgnoreCase(Mockito.anyString()) )
			.willReturn( utils.generateOneUsertEntityOptional() );
		
		UserResponse response = this.userService.saveUser( utils.generateOneUserRequest() );
		
		assertThat(response.getErros() == null, equalTo(false));
		assertThat(response.getId() == null, equalTo(true));
	}

	@Test
	public void updateUserEmailTest() {
		Long userId = 1L;
		
		given( userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()) )
			.willReturn(Optional.empty());
		
		given( userRepositoryMock.findById(Mockito.anyLong()) )
		.willReturn( utils.generateOneUsertEntityOptional() );
		
		given( userRepositoryMock.save(Mockito.any(UserEntity.class)) )
			.willReturn( utils.generateOneUsertEntityOptional().get() );
		
		GenericResponse response = this.userService.updateUserEmail( utils.generateOneUserRequest(), userId );
		
		assertThat(response.getData().toString().toUpperCase().contains(MessagesEnum.USER_EMAIL_UPDATED_MESSAGE.getMessage()), equalTo(true));
		assertThat(response.getUpdated(), equalTo(true));
	}
	
	@Test
	public void updateUserEmailWithEmailAlreadyRegisteredTest() {
		Long userId = 1L;
		
		given(userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()))
			.willReturn( utils.generateOneUsertEntityOptional() );
		
		GenericResponse response = this.userService.updateUserEmail( utils.generateOneUserRequest(), userId );
		
		assertThat(response.getData() == null, equalTo(false));
		assertThat(response.getUpdated(), equalTo(false));
	}

	@Test
	public void testFindAllUsers() {
		given(userRepositoryMock.findAll()).willReturn( utils.generateOneListOfUsers() );
		
		AllUsersResponse response = this.userService.findAllUsers();
		
		assertThat(response.getUsers().isEmpty(), equalTo(false));
	}
}
