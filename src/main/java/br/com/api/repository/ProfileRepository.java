package br.com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, String>{
	
	Profile findByNameIgnoreCase(String name);
}
