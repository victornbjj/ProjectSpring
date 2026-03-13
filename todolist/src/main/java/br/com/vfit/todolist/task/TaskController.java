package br.com.vfit.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import br.com.vfit.todolist.utils.Ultils;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {


        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID)idUser);

        var currentDate = LocalDateTime.now();

        if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt()) ){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio/ data de termino de ser maior que a data atual");
        }
        if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio deve ser menor data de termino");
        }

        var task = this.taskRepository.save(taskModel);
        return  ResponseEntity.status(HttpStatus.OK).body(task);

    }
    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request){
        var IdUser = request.getAttribute("idUser");
         var tasks =  this.taskRepository.findByIdUser((UUID)IdUser);
        return tasks;
    }
    @PutMapping("/{id}")
    public TaskModel upate(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id){




        var task=  this.taskRepository.findById(id).orElse( null);
        Ultils.copyNonNullProperties(taskModel, task);


        return this.taskRepository.save(task);
    }


}
