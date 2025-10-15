package TaskManager.model;

import TaskManager.exceptions.TaskException;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    List<Task> taskList;

    List<Task> tasks;

    public TaskRepository() {
        this.tasks = new ArrayList<>();// TaskPersistence.loadTask();
    }

    public void saveTask(Task task) throws TaskException{
        if (task == null ) throw new TaskException("Task can not be null");
        if (tasks.contains(task)) throw new TaskException("La tarea ya existe en la base de datos");
        tasks.add(task);
        //TaskPersistence.saveTask(tasks);
    }

    public Task findById(String id){
        for(Task task: tasks) {
            if(task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public List<Task> findCompletedTask() throws TaskException {
        List<Task> completedTask = new ArrayList<>();
        for(Task task: tasks) {
            if(task.getCompleted()) {
                completedTask.add(task);
            }
        }
        if (completedTask.isEmpty()) throw new TaskException("Completed Task not found");

        return completedTask;
    }

    public List<Task> findPendingTask() throws TaskException{
        List<Task> pendingTask = new ArrayList<>();
        for(Task task: tasks) {
            if(!task.getCompleted()) {
                pendingTask.add(task);
            }
        }
        if (pendingTask.isEmpty()) throw new TaskException("Pending Task not found");

        return pendingTask;
    }

    public void remove(String id) throws TaskException {
        Task task = findById(id);
        if (task == null ) throw new TaskException("Task can not be null");
        tasks.remove(task);
     //   TaskPersistence.saveTask(tasks);
    }

    public void remove(Task task) throws TaskException {
        if (task == null ) throw new TaskException("Task can not be null");
        if(!tasks.contains(task)) throw new TaskException("Task not exists");
        tasks.remove(task);
    }

    public List<Task> findAll() throws TaskException{
        if (tasks.isEmpty()) throw new TaskException("Tasks not found!");
        return tasks;
    }

    public int findIndexById(String id) {
        for(int i = 0; i <tasks.size(); i++ ) {
            if(tasks.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void updateTask(Task updateTask) throws TaskException {
        if (updateTask == null) throw new TaskException("Task can not be null");
        int index = findIndexById(updateTask.getId());
        if (index == -1) throw new TaskException("Invalid Index");
        tasks.set(index, updateTask);
      //  TaskPersistence.saveTask(tasks);
    }

    public void updateTaskCompleted(String id, Boolean isCompleted) throws TaskException {
        int index = findIndexById(id);
        System.out.println(index + " - " + id);
        if (index == -1) throw new TaskException("Invalid Index");
        tasks.get(index).setCompleted(isCompleted);
       // TaskPersistence.saveTask(tasks);
    }

}
