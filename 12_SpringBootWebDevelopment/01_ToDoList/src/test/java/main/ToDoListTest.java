package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest
{
	@BeforeEach
	void init(){
		Storage.add("1111111");
		Storage.add("2222222");
		Storage.add("3333333");
	}
	
	@Test
	void getById() {
		assertEquals("2222222",Storage.getById(1));
	}
	
	@Test
	void edit() {
		Storage.edit(2,"abc");
		assertEquals("abc",Storage.getById(2));
	}
	
	@Test
	void add() {
		Storage.delete(17);
	}
	
	@Test
	void delete() {
	}
	
	@Test
	void liftUp() {
	}
	
	@Test
	void tearDown() {
	}
}