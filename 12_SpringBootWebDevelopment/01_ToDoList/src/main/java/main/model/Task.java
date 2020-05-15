package main.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table (name = "tasks")
public class Task
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int Id;

	@Enumerated (EnumType.STRING)
	@Column (nullable = false)
	private Priority priority;

	@Column (nullable = false)
	private String data;

	private boolean completed;
}
