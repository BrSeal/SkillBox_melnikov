package module05.task02.ToDoList;

import java.util.ArrayList;
import java.util.Scanner;

public class Loader
{
	private static ArrayList<String> toDoList;
	private Scanner scanner;
	
	private static final String EXIT = "EXIT";
	private static final String LIST = "LIST";
	private static final String ADD = "ADD";
	private static final String DELETE = "DELETE";
	private static final String EDIT = "EDIT";
	private static final String HELP = "HELP";
	
	public static void main(String[] args) {
		toDoList = new ArrayList<>();
		Loader loader = new Loader();
		loader.scanner = new Scanner(System.in);
		String input;
		while (true) {
			System.out.println("Введите команду:");
			input = loader.scanner.nextLine();
			loader.command(input);
			
		}
		
	}
	
	private void command(String input) {
		String[] in = input.split(" ", 2);
		try {
			
			switch (in[0].toUpperCase()) {
				case EXIT:
					exit();
				
				case LIST:
					printList();
					break;
				
				case ADD:
					addToList(in[1]);
					break;
				
				case DELETE:
					deleteFromList(Integer.parseInt(in[1]));
					break;
				
				case EDIT:
					editData(in[1]);
					break;
				
				case HELP:
					help();
					break;
				
				default:
					throw new Exception();
			}
		}catch (NegativeIndexException e) {
			System.out.println("Индекс элемента не может быть отрицательным!");
		}catch (Exception e) {
			System.out.println("Неверно набрана команда!");
			help();
		}
		
		
	}
	
	private void addToList(String input) throws NegativeIndexException {
		String[] in = input.split(" ", 2);
		if (isNum(in[0])) {
			int addIndex = Integer.parseInt(in[0]);
            if (addIndex < 0) { throw new NegativeIndexException(); }
			while (toDoList.size() < addIndex) {
				toDoList.add("");
			}
			toDoList.add(addIndex, in[1]);
		}
		else {
			toDoList.add(input);
		}
	}
	
	private void exit() {
		scanner.close();
		System.exit(0);
	}
	
	private void printList() {
		if (toDoList.isEmpty()) {
			System.out.println("Список дел пуст");
		}
		else {
			int i = 0;
			for (String task : toDoList) {
				System.out.println(i + " " + task);
				i++;
			}
		}
	}
	
	private void deleteFromList(int in) {
		toDoList.remove(in);
	}
	
	private void editData(String input) {
		String[] in = input.split(" ", 2);
		toDoList.set(Integer.parseInt(in[0]), in[1]);
	}
	
	private void help() {
		System.out.println("Допустимые команды:\nLIST \nADD \nEDIT \nDELETE\nHELP\nEXIT");
	}
	
	private static boolean isNum(String num) {
		try {
			Integer.parseInt(num);
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
