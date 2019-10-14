package br.com.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.entity.Profile;
import br.com.api.entity.UserEntity;
import br.com.api.entity.UserProfile;
import br.com.api.model.MessagesModel;
import br.com.api.repository.ProfileRepository;
import br.com.api.repository.UserProfileRepository;
import br.com.api.repository.UserRepository;
import br.com.api.rest.request.UserRequest;
import br.com.api.rest.response.AllUsersResponse;
import br.com.api.rest.response.GenericResponse;
import br.com.api.rest.response.UserResponse;
import br.com.api.service.UserService;
import br.com.api.utils.CryptUtils;
import br.com.api.utils.ParseUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
	
	private static final String ROLE_USER = "ROLE_USER";

	private UserRepository repository;
	private ProfileRepository profileRepository;
	private UserProfileRepository userProfileRepository;
	private ParseUtil parseUtil;
	private MessagesModel message;

	@Inject
	public UserServiceImpl(UserRepository repository, ProfileRepository profileRepository, 
			UserProfileRepository userProfileRepository, ParseUtil parseUtil, MessagesModel message) {
		this.repository = repository;
		this.profileRepository = profileRepository;
		this.userProfileRepository = userProfileRepository;
		this.parseUtil = parseUtil;
		this.message = message;
	}

	@Override
	public UserResponse saveUser(UserRequest userRequest) {
		UserResponse response = new UserResponse();
		
		if ( isEmailRegistered(userRequest.getEmail()) ) {
			
			response.setErros(String.format(message.getEmailAlreadyRegistered(), userRequest.getEmail()));
			
			return response;
		}
		
		if ( isLoginRegistered(userRequest.getLogin()) ) {
			
			response.setErros(String.format(message.getLoginAlreadyRegistered(), userRequest.getLogin()));
			
			return response;
		}
		
		UserEntity user = parseUtil.objectParser(userRequest, UserEntity.class);
		
		user.setSenha(CryptUtils.generateBCrypt(user.getSenha()));
		user = this.repository.save(user);
		
		Profile profile = this.profileRepository.findByNameIgnoreCase(ROLE_USER);
		
		UserProfile userProfile = new UserProfile();
		userProfile.setUserEntity(user);
		userProfile.setProfile(profile);
		
		this.userProfileRepository.save(userProfile);
		
		return parseUtil.objectParser(user, UserResponse.class);
	}

	@Override
	public GenericResponse updateUserEmail(UserRequest userRequest, Long id) {
		GenericResponse response = new GenericResponse();
		
		if ( isEmailRegistered(userRequest.getEmail()) ) {
			response.setData(String.format(message.getEmailAlreadyRegistered(), userRequest.getEmail()));
			response.setUpdated(false);
			
			return response;
		}
		
		UserEntity user = this.repository.findById(id).get();
		user.setEmail(userRequest.getEmail());
		
		this.repository.save(user);
		
		response.setData(message.getEmailSucessChanged());
		response.setUpdated(true);
		
		return response;
	}

	@Override
	public AllUsersResponse findAllUsers() {
		List<UserEntity> listUserEntities = this.repository.findAll();
		AllUsersResponse response = new AllUsersResponse();
		
		List<UserResponse> listUserResponse = listUserEntities.stream()
				.map( userEntity -> parseUtil.objectParser(userEntity, UserResponse.class) )
				.collect( Collectors.toList() );
		
		response.setUsers(listUserResponse);
		
		return response;
	}
	
	public boolean isEmailRegistered(String email) {
		Optional<UserEntity> userOptional = this.repository.findByEmailIgnoreCase(email);
		
		return userOptional.isPresent();
	}
	
	
	private boolean isLoginRegistered(String login) {
		Optional<UserEntity> userOptional = this.repository.findByLoginIgnoreCase(login);
		
		return userOptional.isPresent();
	}
}
