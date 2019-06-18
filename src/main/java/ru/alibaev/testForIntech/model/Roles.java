package ru.alibaev.testForIntech.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data

@Entity
public class Roles implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String role;

// Из-за того, что были bidirectional-связи программа уходила в бесконечный цикл и stackoverflow)
//	@OneToMany(mappedBy = "authority")
//	private Set<Users> users;

}
