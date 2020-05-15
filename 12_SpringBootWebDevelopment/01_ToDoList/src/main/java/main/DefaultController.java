package main;

import main.model.Task;
import main.model.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Comparator;

@Controller
public class DefaultController {
    @Autowired
    private ToDoListRepository repository;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Task> iterableTask = repository.findAll();
        ArrayList<Task> taskList = new ArrayList<>();
        iterableTask.forEach(taskList::add);
        taskList.sort(Comparator.comparing(Task::getPriority));
        model.addAttribute("taskList", taskList);
        return "index";
    }
}
