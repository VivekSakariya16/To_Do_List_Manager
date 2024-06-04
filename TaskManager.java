package Educational_Initiatives.To_Do_List_Manager;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added successfully.");
    }

    public void markTaskAsCompleted(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                task.setCompleted(true);
                System.out.println("Task marked as completed.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void deleteTask(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                tasks.remove(task);
                System.out.println("Task deleted.");
                return;
            }
            System.out.println("Task not found.");
        }
    }

    public List<Task> viewTasks(String filter) {
        List<Task> filteredTasks = new ArrayList<>();
        switch (filter) {
            case "all":
                filteredTasks.addAll(tasks);
                break;
            case "completed":
                for (Task task : tasks) {
                    if (task.isCompleted()) {
                        filteredTasks.add(task);
                    }
                }
                break;
            case "pending":
                for (Task task : tasks) {
                    if (!task.isCompleted()) {
                        filteredTasks.add(task);
                    }
                }
                break;
            default:
                System.out.println("Invalid filter. Showing all tasks.");
                filteredTasks.addAll(tasks);
        }

        return filteredTasks;
    }
}
