package model.task;

import model.task.type.TypePeriodTask;

import java.time.LocalDateTime;

public class WeeklyTask extends Task{
    public WeeklyTask() {
        super(TypePeriodTask.WEEKLY);
    }

    @Override
    public LocalDateTime getNextDateOfTaskAtTheDate() {
        return getDate().plusWeeks(1);
    }

    @Override
    public LocalDateTime getNextDateOfTaskAtTheDate(LocalDateTime localDateTime) {
        LocalDateTime nextDate = LocalDateTime.of(getDate().toLocalDate(), getDate().toLocalTime());
        if (localDateTime.isAfter(getDate())) {
            while (nextDate.toLocalDate().isBefore(localDateTime.toLocalDate())) {
                nextDate = nextDate.plusWeeks(1);
            }
        }
        return nextDate;
    }
}
