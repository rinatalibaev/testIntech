package ru.alibaev.myTest.resources;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.alibaev.myTest.mappers.UserMapper;
import ru.alibaev.myTest.model.Users;

@RestController
@RequestMapping("/users")
public class UserResources {

	public UserMapper userMapper;
	
	public UserResources(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	@GetMapping("/getAll")
	public List<Users> getAllUsers() {
		return userMapper.getAll();
	};
	
	
}
