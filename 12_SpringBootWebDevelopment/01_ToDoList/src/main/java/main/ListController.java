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
public class ListController {
    private static final ResponseEntity BAD_REQUEST = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    private static final ResponseEntity NOT_FOUND = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    private static final ResponseEntity OK = ResponseEntity.status(HttpStatus.OK).body(null);

    @Autowired
    private ToDoListRepository repository;

    @GetMapping("/tasks/")
    public List<Task> getAll() {
        ArrayList<Task> tasks = new ArrayList<>();
        repository.findAll().forEach(tasks::add);
        return tasks;
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity getByNumber(@PathVariable int id) {
        Optional<Task> optionalTask = repository.findById(id);
        return optionalTask.map(task -> new ResponseEntity<>(task, HttpStatus.OK)).orElse(NOT_FOUND);
    }

    @PostMapping(value = "/tasks/add")
    @ResponseBody
    public ResponseEntity<Integer> addToList( Task task) {
        if (task.getData().isEmpty()||task.getData() == null || task.getPriority() == null) {
            return BAD_REQUEST;
        }

        return new ResponseEntity<>(repository.save(task).getId(), HttpStatus.OK);
    }

    @PutMapping("/tasks/edit/{id}")
    @ResponseBody
    public ResponseEntity<String> edit(@PathVariable int id, Task changes) {
        Optional<Task> optionalTask = repository.findById(id);
        
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();

            if (changes.getData().isEmpty()) {
                return BAD_REQUEST;
            }
	        task.setData(changes.getData());
            if (changes.getPriority() != null) {
                task.setPriority(changes.getPriority());
            }
            task.setCompleted(changes.isCompleted());

            return new ResponseEntity<>(repository.save(task).getData(), HttpStatus.OK);
        }
        return NOT_FOUND;
    }

    @DeleteMapping("/tasks/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<Task> optionalTask = repository.findById(id);

        if (optionalTask.isPresent()) {
            repository.deleteById(id);
            return OK;
        }
        return NOT_FOUND;
    }
}
