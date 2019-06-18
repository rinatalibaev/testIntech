package ru.alibaev.testForIntech.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data

@Entity
public class Users implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String login;
	private String username;
	private String surname;
	private String password;
	private Boolean enabled;

	@ManyToOne
	private Roles authority;

// Из-за того, что были bidirectional-связи программа уходила в бесконечный цикл и stackoverflow)
//	@OneToMany(mappedBy = "message_creator")
//	private Messages message;

	
	
}
