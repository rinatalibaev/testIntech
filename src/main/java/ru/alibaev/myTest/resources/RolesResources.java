package ru.alibaev.myTest.resources;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.alibaev.myTest.mappers.RolesMapper;
import ru.alibaev.myTest.model.Roles;

@RestController
@RequestMapping("/roles")
public class RolesResources {
	
	public RolesMapper rolesMapper;
	
	public RolesResources(RolesMapper rolesMapper) {
		this.rolesMapper = rolesMapper;
	}

	@GetMapping("/getAll")
	public List<Roles> getAllRoles() {
		return rolesMapper.getAll();
	};
	
}
