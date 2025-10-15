package TaskManager.controller;

import TaskManager.exceptions.TaskException;
import TaskManager.exceptions.TaskValidationException;
import TaskManager.model.Task;
import TaskManager.model.TaskRepository;

import java.util.List;

public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String id, String title, String description, Boolean completed) throws TaskValidationException, TaskException {
        validateTaskData(id, title, description,  completed);
        Task task = new Task(id, title, description,  completed);
        this.taskRepository.saveTask(task);
        System.out.println("Task saved");
    }

    public void removeTask(String id) throws TaskValidationException, TaskException {
        if (id == null || id.trim().isEmpty()) throw new TaskValidationException("Id must have value");

        this.taskRepository.remove(id);
    }

    public void showTask() throws TaskValidationException, TaskException {
        List<Task> tasks =  this.taskRepository.findAll();
        if(tasks.isEmpty()) throw new TaskValidationException("Empty List!");
        tasks.forEach(System.out::println);
    }

    public void showComletedpTask() throws TaskValidationException, TaskException {
        List<Task> tasks =  this.taskRepository.findCompletedTask();
        if(tasks.isEmpty()) throw new TaskValidationException("Empty List!");
        tasks.forEach(System.out::println);
    }

    public void showPendingTask() throws TaskValidationException, TaskException {
        List<Task> tasks =  this.taskRepository.findPendingTask();
        if(tasks.isEmpty()) throw new TaskValidationException("Empty List!");
        tasks.forEach(System.out::println);
    }

    public void updateTask(String id, String title, String description, Boolean completed) throws TaskValidationException, TaskException {
        validateTaskData(id, title, description,  completed);
        Task updateTask = new Task(id, title, description, completed);
        this.taskRepository.updateTask(updateTask);
    }

    public void updateTask(String id, Boolean completed) throws TaskValidationException, TaskException {
        validateTaskData(id, completed);
        this.taskRepository.updateTaskCompleted(id, completed);
    }

    private void validateTaskData(String id, String title, String description, Boolean completed) throws TaskValidationException {
        if (id == null || id.trim().isEmpty()) throw new TaskValidationException("Id must have value");
        if (title == null || title.trim().isEmpty()) throw new TaskValidationException("Title must have value");
        if (description == null || description.trim().isEmpty()) throw new TaskValidationException("Description must have value");
        if (completed == null ) throw new TaskValidationException("Completed must have value");
    }

    private void validateTaskData(String id, Boolean completed) throws TaskValidationException {
        if (id == null || id.trim().isEmpty()) throw new TaskValidationException("Id must have value");
        if (completed == null ) throw new TaskValidationException("Completed must have value");
    }

}
