package form;

import exception.IncorrectArgumentException;

public class UpdateTaskForm {
    private int id;
    private String title;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(String id) throws IncorrectArgumentException {
        if (!id.matches("\\d*")) {
            throw new IncorrectArgumentException("Введите айди задачи в формате числа:");
        }
        this.id = Integer.parseInt(id);
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
}
