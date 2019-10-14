package br.com.api.rest.response;

import java.util.List;

public class AllUsersResponse {

	List<UserResponse> users;

	public List<UserResponse> getUsers() {
		return users;
	}

	public void setUsers(List<UserResponse> users) {
		this.users = users;
	}
}
