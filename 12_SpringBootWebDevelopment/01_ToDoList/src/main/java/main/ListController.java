package main;

import main.model.Priority;
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
	private static final Priority DEFAULT_PRIORITY = Priority.MEDIUM;
	
	@Autowired
	private ToDoListRepository repository;
	
	@GetMapping ("/tasks/")
	public List<Task> getAll() {
		ArrayList<Task> tasks = new ArrayList<>();
		repository.findAll().forEach(tasks::add);
		return tasks;
	}
	
	@GetMapping ("/tasks/{id}")
	public ResponseEntity<Task> getByNumber(@PathVariable int id) {
		Optional<Task> optionalTask = repository.findById(id);
		return optionalTask.map(task -> new ResponseEntity<>(task, HttpStatus.OK)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	}
	
	@PostMapping (value = "/tasks/add")
	public ResponseEntity<Integer> addToList(@RequestBody Task task) {
		if (task.getData() == null) { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); }
		
		if (task.getPriority() == null) { task.setPriority(DEFAULT_PRIORITY); }
		
		return new ResponseEntity<>(repository.save(task).getId(), HttpStatus.OK);
	}
	
	@PutMapping ("/tasks/edit/{id}")
	public ResponseEntity<?> edit(@PathVariable int id, @RequestBody Task changes) {
		Optional<Task> optionalTask = repository.findById(id);
		
		if (optionalTask.isPresent()) {
			Task task = optionalTask.get();
			
			if (changes.getData() != null) { task.setData(changes.getData()); }
			if (changes.getPriority() != null) { task.setPriority(changes.getPriority()); }
			
			repository.save(task);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@DeleteMapping ("/tasks/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		Optional<Task> optionalTask = repository.findById(id);
		
		if (optionalTask.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}
