package model.task;

import model.task.type.TypeOfTask;
import model.task.type.TypePeriodTask;

import java.time.LocalDateTime;

public abstract class Task implements RepetitiveTask{
    private static int globalId;
    private int id;
    private String title;
    private String description;
    private TypeOfTask typeOfTask;
    private LocalDateTime date;
    private TypePeriodTask typePeriodTask;
    private boolean isDelete = false;

    public Task(TypePeriodTask typePeriodTask) {
        id = ++globalId;
        this.typePeriodTask = typePeriodTask;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeOfTask getTypeOfTask() {
        return typeOfTask;
    }

    public void setTypeOfTask(TypeOfTask typeOfTask) {
        this.typeOfTask = typeOfTask;
    }

    public TypePeriodTask getTypePeriodTask() {
        return typePeriodTask;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        return id == task.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return ("Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", typeOfTask=" + typeOfTask +
                ", date=" + date +
                ", typePeriodTask=" + typePeriodTask +
                '}').replace(",", ",\n");
    }
}
