package com.hoanv.SpringBootApi;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
	public User getUser(String username, String password);

	public void addUser(User user);

	public User findUserByName(String username);

	public List<User> getAllUser();

	public User getUserById(int id);

	public int update(User user);
}
