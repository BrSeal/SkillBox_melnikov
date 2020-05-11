package main.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Task
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String data;
}
