package main;

import java.util.ArrayList;
import java.util.List;

public class Storage
{
	private static final List<String> tasks = new ArrayList<>();
	
	public static List<String> getTasks() {
		return tasks;
	}
	
	public static String getById(int id) {
		return tasks.get(id);
	}
	
	public static synchronized void edit(int id, String data) {
		tasks.set(id, data);
	}
	
	public static synchronized int add(String data) {
		tasks.add(data);
		return tasks.size();
	}
	
	public static synchronized String delete(int id) {
		return tasks.remove(id);
	}

	public static int getSize(){
		return tasks.size();
	}
	
}
