package model.task.type;

public enum TypeOfTask {
    PERSONAL("Личная задача"), Working("Рабочая задача");
    private final String name;

    TypeOfTask(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
