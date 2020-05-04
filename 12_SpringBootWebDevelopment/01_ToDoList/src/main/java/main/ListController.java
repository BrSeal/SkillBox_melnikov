package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ListController {
    @GetMapping("/")
    public List<String> getAll() {
        return Storage.getTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getByNumber(@PathVariable int id) {
        try {
            return new ResponseEntity<>(Storage.getById(id), HttpStatus.OK);
        } catch (IndexOutOfBoundsException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Integer> addToList(String data) {
        if(data.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return new ResponseEntity<>(Storage.add(data),HttpStatus.OK);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity edit(@PathVariable int id, String data) {
        try {
            Storage.edit(id, data);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (IndexOutOfBoundsException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("/moveUp/{id}")
    public ResponseEntity moveUp(@PathVariable int id) {
        synchronized (Storage.class) {
            if (id <= 0 || Storage.getSize() < 2) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            swap(id, id - 1);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PatchMapping("/moveDown/{id}")
    public ResponseEntity moveDown(@PathVariable int id) {
        synchronized (Storage.class) {
            if (id >= Storage.getSize() - 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            swap(id, id + 1);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            return new ResponseEntity<>(Storage.delete(id), HttpStatus.OK);
        } catch (IndexOutOfBoundsException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    private void swap(int a, int b) {
        synchronized (Storage.class) {
            String aValue = Storage.getById(a);
            String bValue = Storage.getById(b);
            Storage.edit(a, bValue);
            Storage.edit(b, aValue);
        }
    }

}
