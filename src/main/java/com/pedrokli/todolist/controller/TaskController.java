package com.pedrokli.todolist.controller;


import com.pedrokli.todolist.model.Task;
import com.pedrokli.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j

public class TaskController {

    TaskService taskService;

    @Operation(summary = "Criando uma nova tarefa")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Houve um erro ao criar a tarefa, verifique as informações")
    })

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask (@RequestBody Task task){
        log.info("Criando uma nova tarefa com as informações [{}]", task);
        return taskService.createTask(task);
    }

    @Operation(summary = "Listando todas as tarefas")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Tarefas listadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Houve um erro ao listas as tarefas")
    })

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks(){
        log.info("Listando todas as tarefas cadastradas");
        return taskService.listAllTasks();
    }

    @Operation(summary = "Buscando uma tarefa pelo id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Tarefa econtrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrada nenhuma tarefa com esse id")
    })

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTaskById(@PathVariable (value = "id") Long id){
        log.info("Buscando tarefas com o id [{}]", id);
        return taskService.findTaskById(id);
    }

    @Operation(summary = "Atualizando uma tarefa")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi possível atualizar a tarefa")
    })
    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTaskById(@PathVariable (value = "id") Long id, @RequestBody Task task){
        log.info("Atualizando a tarefa com id [{}] as novas informações são : [{}]", id, task);

        return taskService.updateTaskById(task,id);
    }
    @Operation(summary = "Excluindo uma tarefa")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204", description = "Tarefa excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi possível excluir a tarefa - Tarefa não encontrada")
    })
    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTaskById(@PathVariable (value = "id") Long id){
        log.info("Excluindo tarefa com o id [{}]", id);
        return taskService.deleteById(id);
    }

}
