package br.com.gustavocarvalho.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired //gerencia a instancia no repositorio 
    private ITaskRepository taskRepository;

    @PostMapping("/")//criar a tarefa 
    public TaskModel create(@RequestBody TaskModel taskModel){
         var task = this.taskRepository.save(taskModel);
         return task;
    }
   
}
