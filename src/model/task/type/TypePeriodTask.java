package model.task.type;

public enum TypePeriodTask {
    ANNUAL("Годовая задача"), MONTHLY("Месецовая задача"), WEEKLY("Еженедельная задача"),
    DAILY("Ежедневная задача"), ONETIME("Единоразовая задача");
    private final String name;

    TypePeriodTask(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
