package ru.alibaev.myTest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import ru.alibaev.myTest.model.Roles;

@Mapper
public interface RolesMapper {

	@Select("SELECT * FROM user_roles")
	List<Roles> getAll();
}
