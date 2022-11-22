package service;

import exception.IncorrectArgumentException;
import exception.NotFoundElementException;
import form.*;
import model.task.Task;
import model.task.type.TypeOfTask;
import model.task.type.TypePeriodTask;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputService {
    TaskManagerService taskManagerService;
    Scanner scanner;

    public InputService() {
        this.taskManagerService = new TaskManagerService();
        scanner = new Scanner(System.in);
    }

    public void startInput() {
        label:
        while (true) {
            printMenu();
            System.out.print("Выберите пункт меню: ");
            if (scanner.hasNextInt()) {
                int menu = scanner.nextInt();
                switch (menu) {
                    case 1:
                        inputTask();
                        break;
                    case 2:
                        deleteTask();
                        break;
                    case 3:
                        getTasksForDay();
                        break;
                    case 4:
                        getDeletedTasks();
                        break;
                    case 5:
                        updateTask();
                        break;
                    case 6:
                        getTasksForDateInterval();
                        break;
                    case 0:
                        break label;
                }
            } else {
                scanner.next();
                System.out.println("Выберите пункт меню из списка!");
            }
        }
    }

    private void inputTask() {
        AddTaskForm addTaskForm = new AddTaskForm();
        System.out.println("Введите название задачи: ");
        while (true) {
            String taskTitle = scanner.nextLine();
            if (taskTitle.isBlank()) {
                continue;
            }
            try {
                addTaskForm.setTitle(taskTitle);
                break;
            } catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Введите описание задачи: ");
        while (true) {
            String taskDescription = scanner.nextLine();
            if (taskDescription.isBlank()) {
                continue;
            }
            try {
                addTaskForm.setDescription(taskDescription);
                break;
            } catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Введите номер типа задачи: ");
        printTaskType();
        while (true) {
            String taskType = scanner.next();
            try {
                addTaskForm.setTypeTask(taskType);
                break;
            } catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
                printTaskType();
            }
        }
        System.out.println("Введите номер переодичности задачи: ");
        printTaskPeriodType();
        while (true) {
            String taskPeriodType = scanner.next();
            try {
                addTaskForm.setTypePeriodTask(taskPeriodType);
                break;
            } catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
                printTaskPeriodType();
            }
        }
        System.out.println("Введите дату задачи в формате '12-01-2022 03:01':");
        while (true) {
            String taskDate = scanner.nextLine();
            if (taskDate.isBlank()) {
                continue;
            }
            try {
                addTaskForm.setDate(taskDate);
                break;
            } catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Task newTask = taskManagerService.addTask(addTaskForm);

        System.out.println("Новая задача с id=" + newTask.getId() + " успешно добавлена!");
    }

    private void deleteTask() {
        System.out.print("Введите id задачи, которую хотите удалить:");
        DeleteTaskForm deleteTaskForm = new DeleteTaskForm();
        while (true) {
            if (scanner.hasNext()) {
                String taskId = scanner.next();
                try {
                    deleteTaskForm.setId(taskId);
                    break;
                } catch (IncorrectArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        try {
            Task task = taskManagerService.deleteTask(deleteTaskForm);
            System.out.println("Ваша задача успешно перемещана в архив удалённых задач!");
            System.out.println(task);
        } catch (NotFoundElementException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getDeletedTasks() {
        List<Task> deletedTasks = taskManagerService.getDeletedTasks();
        if (deletedTasks.size() == 0) {
            System.out.println("Удалленых задач в архиве не обнаружено!");
        } else {
            System.out.println("Удаленные задачи в архиве:");
            for (int i = 0; i < deletedTasks.size(); i++) {
                String start = "----------------Задача №" + deletedTasks.get(i).getId() + " " + deletedTasks.get(i).getTitle() + ", " + deletedTasks.get(i).getTypeOfTask().getName() + "----------------";
                String end = "";
                for (char el : start.toCharArray()) {
                    end += "-";
                }
                System.out.println(start);
                System.out.println("Вам необходимо сделать: " + deletedTasks.get(i).getDescription());
                System.out.println(end);
            }
        }
    }

    private void getTasksForDay() {
        System.out.println("Введите дату, на которую хотите получить список задач:");
        TaskForDayForm form = new TaskForDayForm();
        while (true) {
            if (scanner.hasNextLine()) {
                String date = scanner.nextLine();
                if (date.isBlank()) {
                    continue;
                }
                try {
                    form.setDate(date);
                    break;
                } catch (IncorrectArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        List<Task> tasks = taskManagerService.getTaskForDay(form);
        if (tasks.size() == 0) {
            System.out.println("Задач на день нету!");
        } else {
            System.out.println("Задачи на день:");
            for (int i = 0; i < tasks.size(); i++) {
                String start = "----------------Задача №" + tasks.get(i).getId() + " " + tasks.get(i).getTitle() + ", " + tasks.get(i).getTypeOfTask().getName() + "----------------";
                String end = "";
                for (char el : start.toCharArray()) {
                    end += "-";
                }
                System.out.println(start);
                System.out.println("Вам необходимо сделать: " + tasks.get(i).getDescription());
                System.out.println(end);
            }
        }
    }

    private void updateTask() {
        System.out.println("Введите id задача,которую нужно редактировать");
        UpdateTaskForm updateTaskForm = new UpdateTaskForm();
        Task task;
        while (true) {
            String taskId = scanner.next();
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            try {
                updateTaskForm.setId(taskId);
                task = taskManagerService.getTaskById(updateTaskForm.getId());
                break;
            } catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            } catch (NotFoundElementException e) {
                System.out.println(e.getMessage());
                System.out.println("Введите id существующей задачи:");
            }
        }
        System.out.println("Введите новое название задачи, или ничего для пропуска");
        while (true) {
            System.out.println("Старое название:" + task.getTitle());
            try {
                if (scanner.hasNextLine()) {
                    String newTaskTitle = scanner.nextLine();
                    if (newTaskTitle.isBlank()) {
                        updateTaskForm.setTitle(task.getTitle());
                    } else {
                        updateTaskForm.setTitle(newTaskTitle);
                    }
                    break;
                }
            } catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Введите новое описание задачи, или ничего для пропуска");
        while (true) {
            System.out.println("Старое описание:" + task.getDescription());
            try {
                if (scanner.hasNextLine()) {
                    String newDescription = scanner.nextLine();
                    if (newDescription.isBlank()) {
                        updateTaskForm.setDescription(task.getDescription());
                    } else {
                        updateTaskForm.setDescription(newDescription);
                    }
                    break;
                }
            } catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            Task newTask = taskManagerService.updateTaskById(updateTaskForm);
            System.out.println("Ваша задача с id=" + newTask.getId() + " успешно обновлена!");
        } catch (NotFoundElementException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getTasksForDateInterval() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        GetTasksForDateIntervalForm getTasksForDateIntervalForm = new GetTasksForDateIntervalForm();
        System.out.println("Введите первую дату промежутка:");
        while (true) {
            try {
                String dateFirst = scanner.nextLine();
                getTasksForDateIntervalForm.setLocalDateTimeFirst(dateFirst);
                break;
            } catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Введите вторую дату промежутка:");
        while (true) {
            try {
                String dateSecond = scanner.nextLine();
                getTasksForDateIntervalForm.setLocalDateTimeSecond(dateSecond);
                break;
            } catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Map<LocalDateTime, List<Task>> tasksForIntervalDate = taskManagerService
                .getTaskForIntervalDate(getTasksForDateIntervalForm);

        StringBuffer stringBuffer = new StringBuffer();

        for (Map.Entry<LocalDateTime, List<Task>> taskList : tasksForIntervalDate.entrySet()) {
            if (!taskList.getValue().isEmpty()) {
                stringBuffer.append("Ваши задачи на дату :" + taskList.getKey().toLocalDate() + "!\n");
                for (Task task : taskList.getValue()) {
                    String start = "----------------Задача №" + task.getId() + " " + task.getTitle() + ", " + task.getTypeOfTask().getName() + "----------------";
                    String end = "";
                    for (char el : start.toCharArray()) {
                        end += "-";
                    }
                    stringBuffer.append(start + "\n");
                    stringBuffer.append("Вам необходимо сделать: " + task.getDescription() + "\n");
                    stringBuffer.append(end + "\n");
                }
            }
        }
        if (stringBuffer.toString().equals("")) {
            System.out.println("Задач на заданный промежуток нету!");
        } else {
            System.out.println(stringBuffer);
        }
    }


    private void printMenu() {
        System.out.println("1. Добавить задачу\n" +
                "2. Удалить задачу\n" +
                "3. Получить задачу на указанный день\n" +
                "4. Получить список удалленых задач из архива\n" +
                "5. Редактировать задачу\n" +
                "6. Получить задачи на указанный промежуток\n" +
                "0. Выход");
    }

    private void printTaskType() {
        for (int i = 0; i < TypeOfTask.values().length; i++) {
            System.out.println(i + ". " + TypeOfTask.values()[i].getName());
        }
    }

    private void printTaskPeriodType() {
        for (int i = 0; i < TypePeriodTask.values().length; i++) {
            System.out.println(i + ". " + TypePeriodTask.values()[i].getName());
        }
    }
}
