package model.task;

import model.task.type.TypePeriodTask;

import java.time.LocalDateTime;

public class MonthlyTask extends Task{

    public MonthlyTask() {
        super(TypePeriodTask.MONTHLY);
    }

    @Override
    public LocalDateTime getNextDateOfTaskAtTheDate() {
        return getDate().plusMonths(1);
    }

    @Override
    public LocalDateTime getNextDateOfTaskAtTheDate(LocalDateTime localDateTime) {
        LocalDateTime nextDate = LocalDateTime.of(getDate().toLocalDate(), getDate().toLocalTime());
        if (localDateTime.isAfter(getDate())) {
            while (nextDate.toLocalDate().isBefore(localDateTime.toLocalDate())) {
                nextDate = nextDate.plusMonths(1);
            }
        }
        return nextDate;
    }
}
