package form;

import exception.IncorrectArgumentException;

public class DeleteTaskForm {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(String idString) throws IncorrectArgumentException {
        if (!idString.matches("\\d*")) {
            throw new IncorrectArgumentException("Введите айди задачи в формате числа:");
        }
        this.id = Integer.parseInt(idString);
    }

}
