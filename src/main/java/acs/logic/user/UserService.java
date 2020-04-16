package acs.logic.user;

import java.util.List;

import acs.rest.users.UserBoundary;

public interface UserService {
	public UserBoundary createUser(UserBoundary user);
	public UserBoundary login(String userDomain, String userEmail);
	public UserBoundary updateUser(String userDomain, String userEmail, UserBoundary update);
	public List<UserBoundary> getAllUsers(String adminDomain, String adminEmail);
	public void deleteAllUsers(String adminDomain, String adminEmail);

}
