package service;

import exception.NotFoundElementException;
import factory.TaskFactory;
import form.*;
import model.task.RepetitiveTask;
import model.task.Task;
import model.task.type.TypeOfTask;
import model.task.type.TypePeriodTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskManagerService {
    private static Map<Integer, Task> tasks = new HashMap<>();

    private static List<Task> deletedTaskArchive = new ArrayList<>();

    public TaskManagerService() {
    }

    public Task getTaskById(int id) throws NotFoundElementException {
        if (!tasks.containsKey(id)) {
            throw new NotFoundElementException("Задача с id=" + id + " не была найдена!");
        }
        return tasks.get(id);
    }

    public Task addTask(AddTaskForm form) {
        TypePeriodTask typePeriodTask = TypePeriodTask.values()[form.getTypePeriodTask()];
        Task task = TaskFactory.createTask(typePeriodTask);

        task.setTypeOfTask(TypeOfTask.values()[form.getTypeTask()]);
        task.setDescription(form.getDescription());
        task.setDate(parseDateTime(form.getDate()));
        task.setTitle(form.getTitle());
        tasks.put(task.getId(), task);
        return task;
    }

    public Task updateTaskById(UpdateTaskForm form) throws NotFoundElementException {
        if (!tasks.containsKey(form.getId())) {
            throw new NotFoundElementException("Задача с id=" + form.getId() + " не была найдена!");
        }
        Task task = tasks.get(form.getId());
        task.setTitle(form.getTitle());
        task.setDescription(form.getDescription());

        return task;
    }

    public Task deleteTask(DeleteTaskForm form) throws NotFoundElementException {
        if (!tasks.containsKey(form.getId())) {
            throw new NotFoundElementException("Задача по id=" + form.getId() + " не найдена!");
        }
        Task task = tasks.get(form.getId());
        task.setDelete(true);

        deletedTaskArchive.add(task);
        tasks.remove(task.getId());

        return task;
    }

    public List<Task> getTaskForDay(TaskForDayForm form) {
        List<Task> tasksForDay = new ArrayList<>();
        for (Map.Entry<Integer, Task> taskEntry : tasks.entrySet()) {
            if (checkDateForTaskCompletion(taskEntry.getValue(), parseDateTime(form.getDate()))) {
                tasksForDay.add(taskEntry.getValue());
            }
        }
        return tasksForDay;
    }

    public List<Task> getTaskForDay(LocalDateTime localDateTime) {
        List<Task> tasksForDay = new ArrayList<>();
        for (Map.Entry<Integer, Task> taskEntry : tasks.entrySet()) {
            if (checkDateForTaskCompletion(taskEntry.getValue(), localDateTime)) {
                tasksForDay.add(taskEntry.getValue());
            }
        }
        return tasksForDay;
    }

    public List<Task> getDeletedTasks() {
        return deletedTaskArchive;
    }

    public Map<LocalDateTime, List<Task>> getTaskForIntervalDate(GetTasksForDateIntervalForm form) {
        Map<LocalDateTime, List<Task>> tasksForIntervalDate = new TreeMap<>();
        LocalDateTime firstDate = parseDateTime(form.getLocalDateTimeFirst());
        LocalDateTime secondDate = parseDateTime(form.getLocalDateTimeSecond());
        for (; !firstDate.isAfter(secondDate); firstDate = firstDate.plusDays(1)) {
            tasksForIntervalDate.put(firstDate, getTaskForDay(firstDate));
        }
        return tasksForIntervalDate;
    }

    private boolean checkDateForTaskCompletion(Task task, LocalDateTime dateTime) {
        if (dateTime.toLocalDate().isAfter(task.getDate().toLocalDate())) {
            return dateTime.toLocalDate().
                    isEqual(task.getNextDateOfTaskAtTheDate(dateTime).toLocalDate());
        }
        return dateTime.toLocalDate().isEqual(task.getDate().toLocalDate());
    }

    private LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        /*String date[] = dateTime.split(" ")[0].split("-");
        String time[] = dateTime.split(" ")[1].split(":");
        int year = Integer.parseInt(date[2]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[0]);
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);*/
        return LocalDateTime.parse(dateTime,dateTimeFormatter);
    }
}
