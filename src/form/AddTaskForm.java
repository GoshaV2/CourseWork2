package form;

import exception.IncorrectArgumentException;
import model.task.type.TypeOfTask;
import model.task.type.TypePeriodTask;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddTaskForm {
    private String title;
    private String description;
    private String date;
    private int typeTask;
    private int typePeriodTask;

    public AddTaskForm() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IncorrectArgumentException {
        if (title == null || title.isBlank()) {
            throw new IncorrectArgumentException("Введите корректный заголовок задачи:");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IncorrectArgumentException {
        if (description == null || description.isBlank()) {
            throw new IncorrectArgumentException("Введите корректное описание задачи:");
        }
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) throws IncorrectArgumentException {
        if (!date.matches("\\d{2}-\\d{2}-\\d* \\d{2}:\\d{2}")) {
            throw new IncorrectArgumentException("Введите корректную дату в формате '12-01-2022 03:01':");
        }
        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            df.setLenient(false);
            df.parse(date);
        } catch (ParseException e) {
            throw new IncorrectArgumentException("Введите корректную дату в формате '12-01-2022 03:01':");
        }
        this.date = date;
    }

    public int getTypeTask() {
        return typeTask;
    }

    public void setTypeTask(String typeTaskString) throws IncorrectArgumentException {
        int typeTask = valueOf(typeTaskString);
        if (typeTask < 0 || typeTask >= TypeOfTask.values().length) {
            throw new IncorrectArgumentException("Выберите корректный тип задачи из списка:");
        }
        this.typeTask = typeTask;
    }

    public int getTypePeriodTask() {
        return typePeriodTask;
    }

    public void setTypePeriodTask(String typePeriodTaskString) throws IncorrectArgumentException {
        int typePeriodTask = valueOf(typePeriodTaskString);
        if (typePeriodTask < 0 || typePeriodTask >= TypePeriodTask.values().length) {
            throw new IncorrectArgumentException("Выберите корректный тип перидичности задачи из списка:");
        }
        this.typePeriodTask = typePeriodTask;
    }

    private int valueOf(String digit) throws IncorrectArgumentException {
        if (!digit.matches("\\d*")) {
            throw new IncorrectArgumentException("Введите число а не название:");
        }
        return Integer.parseInt(digit);
    }
}
