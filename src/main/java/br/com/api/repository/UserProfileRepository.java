package br.com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.api.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
	
	@Query(value = "SELECT u FROM UserProfile u WHERE u.userEntity.id = :userId")
	List<UserProfile> findByUserId(@Param("userId") Long userId);

}