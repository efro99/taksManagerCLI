import TaskManager.controller.TaskController;
import TaskManager.model.TaskRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TaskRepository repository = new TaskRepository();
        TaskController controller = new TaskController(repository);
    }
}