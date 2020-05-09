package main;

import main.model.Task;
import main.model.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ListController
{
	
	@Autowired
	private ToDoListRepository repository;
	
	@GetMapping ("/")
	public List<Task> getAll() {
		ArrayList<Task> tasks = new ArrayList<>();
		repository.findAll().forEach(tasks::add);
		return tasks;
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Task> getByNumber(@PathVariable int id) {
		Optional<Task> optionalTask = repository.findById(id);
		return optionalTask.map(task -> new ResponseEntity<>(task, HttpStatus.OK)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	}
	
	@PostMapping ("/add")
	public int addToList(Task task) {
		return repository.save(task).getId();
	}
	
	@PatchMapping ("/edit/{id}")
	public ResponseEntity edit(@PathVariable int id, String newData) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		
		Object o = new Object();
		synchronized (o) {
			
			Optional<Task> optionalTask = repository.findById(id);
			if (optionalTask.isPresent()) {
				Task task = optionalTask.get();
				task.setData(newData);
				repository.save(task);
				httpStatus = HttpStatus.OK;
			}
			
		}
		return ResponseEntity.status(httpStatus).body(null);
	}
	
	@PatchMapping ("/moveUp/{id}")
	public ResponseEntity moveUp(@PathVariable int id) {
		return swap(id, id - 1);
	}
	
	@PatchMapping ("/moveDown/{id}")
	public ResponseEntity moveDown(@PathVariable int id) {
		return swap(id, id + 1);
	}
	
	@DeleteMapping ("/delete/{id}")
	public ResponseEntity delete(@PathVariable int id) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		
		Optional<Task> optionalTask = repository.findById(id);
		if (optionalTask.isPresent()) {
			repository.deleteById(id);
			httpStatus = HttpStatus.OK;
		}
		return ResponseEntity.status(httpStatus).body(null);
	}
	
	private ResponseEntity swap(int a, int b) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		Object o = new Object();
		synchronized (o) {
			Optional<Task> first = repository.findById(a);
			Optional<Task> second = repository.findById(b);
			
			if (first.isPresent() && second.isPresent()) {
				Task firstTask = first.get();
				Task secondTask = second.get();
				
				String firstData = firstTask.getData();
				
				firstTask.setData(secondTask.getData());
				secondTask.setData(firstData);
				
				repository.save(firstTask);
				repository.save(secondTask);
				
				
				httpStatus = HttpStatus.OK;
			}
		}
		
		return ResponseEntity.status(httpStatus).body(null);
	}
	
}
