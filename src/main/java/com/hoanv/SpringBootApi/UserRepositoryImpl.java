package com.hoanv.SpringBootApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public User getUser(String username, String password) {
		String sql = "SELECT * FROM user where username = ? and password = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { username, password }, new UserMapper());
	}

	@Override
	public void addUser(User user) {
		String sql = "INSERT INTO user (fullname,username,password,email) VALUES(?,?,?,?)";
		this.jdbcTemplate.update(sql,
				new Object[] { user.getFullname(), user.getUsername(), user.getPassword(), user.getEmail() });
	}

	@Override
	public User findUserByName(String username) {
		String sql = "SELECT * FROM user where username = ?";
		try {
			return jdbcTemplate.queryForObject(sql, new Object[] { username }, new UserMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public List<User> getAllUser() {
		String sql = "SELECT * FROM user";
		return this.jdbcTemplate.query(sql, new UserMapper());
	}

	@Override
	public User getUserById(int id) {
		String sql = "SELECT * FROM user where id = ?";
		try {
			return jdbcTemplate.queryForObject(sql, new Object[] { id }, new UserMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public int update(User user) {
		return jdbcTemplate.update("update user set fullname = ?, username = ?, password = ?, email = ? where id = ?",
				user.getFullname(), user.getUsername(), user.getPassword(), user.getEmail(), user.getId());
	}

}
