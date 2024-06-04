package Educational_Initiatives.To_Do_List_Manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ToDoList {
    private static final TaskManager manager = new TaskManager();
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean shouldContinue = true;

    public static void main(String[] args) {
        System.out.println("Welcome to the To-Do List Manager CLI!");

        while (shouldContinue) {
            displayMenu();
            int choice = getChoice();
            execute(choice);
        }
    }

    private static void displayMenu() {
        System.out.println("\nMenu:\n");
        System.out.println("1. Add Task");
        System.out.println("2. Mark Task as Completed");
        System.out.println("3. Delete Task");
        System.out.println("4. View All Tasks");
        System.out.println("5. View Completed Tasks");
        System.out.println("6. View Pending Tasks");
        System.out.println("7. Exit");
        System.out.print("Enter your choice (1-7): ");
    }

    private static int getChoice() {
        int choice = 0;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
        } catch (java.util.InputMismatchException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        return choice;
    }

    private static void execute(int choice) {
        switch (choice) {
            case 1:
                addTask();
                break;
            case 2:
                markTaskAsCompleted();
                break;
            case 3:
                deleteTask();
                break;
            case 4:
                viewAllTasks();
                break;
            case 5:
                viewCompletedTasks();
                break;
            case 6:
                viewPendingTasks();
                break;
            case 7:
                System.out.println("Exiting To-Do List Manager.");
                shouldContinue=false;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
        }
    }

    private static void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task note: ");
        String note = scanner.nextLine();
        Date dueDate = null;
        while(true){
            System.out.print("Enter due date [follow \"yyyy-MM-dd\" format] or leave empty: ");
            String dueDateStr = scanner.nextLine();
            if (!dueDateStr.isEmpty()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                boolean isCatch = true;
                try {
                    dueDate = dateFormat.parse(dueDateStr);
                } catch (ParseException e) {
                    isCatch = false;
                    System.out.println("Invalid date format. Please again enter valid due date.");
                    System.out.println();
                }
                if(isCatch) break;
            }else{
                break;
            }
        }

        Task task = new Task.Builder(description).dueDate(dueDate).note(note).build();
        manager.addTask(task);
    }

    private static void markTaskAsCompleted() {
        boolean isTaskPresent = viewAllTasksDescription(manager.viewTasks("pending"));
        if(isTaskPresent) {
            System.out.print("Enter task description to mark as completed: ");
            String description = scanner.nextLine();
            manager.markTaskAsCompleted(description);
        }
    }

    private static void deleteTask() {
        boolean isTaskPresent =viewAllTasksDescription(manager.viewTasks("all"));
        if(isTaskPresent) {
            System.out.print("Enter task description to delete: ");
            String description = scanner.nextLine();
            manager.deleteTask(description);
        }
    }

    private static void viewAllTasks() {
        List<Task> tasks = manager.viewTasks("all");
        displayTasks("All Tasks", tasks);
    }

    private static void viewCompletedTasks() {
        List<Task> tasks = manager.viewTasks("completed");
        displayTasks("Completed Tasks", tasks);
    }

    private static void viewPendingTasks() {
        List<Task> tasks = manager.viewTasks("pending");
        displayTasks("Pending Tasks", tasks);
    }

    private static void displayTasks(String title, List<Task> tasks) {
        System.out.println("\n" + title + ":\n");
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (Task task : tasks) {
                System.out.println("Description: " + task.getDescription());
                System.out.println("Due Date: " + (task.getDueDate() != null ? task.getDueDate() : "N/A"));
                System.out.println("Note: "+ task.getNote());
                System.out.println("Status: " + (task.isCompleted() ? "Completed" : "Pending"));
                System.out.println();
            }
        }
    }

    private static boolean viewAllTasksDescription(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            System.out.println("Please add task first.");
            return false;
        } else {
            for (Task task : tasks) {
                System.out.println("Description: " + task.getDescription());
            }
            System.out.println();
            return true;
        }
    }
}