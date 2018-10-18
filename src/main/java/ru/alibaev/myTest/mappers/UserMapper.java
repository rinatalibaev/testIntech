package ru.alibaev.myTest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import ru.alibaev.myTest.model.Users;

@Mapper
public interface UserMapper {

	@Select("SELECT * FROM users")
	List<Users> getAll();

}
