package model.task;

import model.task.type.TypePeriodTask;

import java.time.LocalDateTime;

public class DailyTask extends Task implements RepetitiveTask {

    public DailyTask() {
        super(TypePeriodTask.DAILY);
    }

    @Override
    public LocalDateTime getNextDateOfTaskAtTheDate() {
        return getDate().plusDays(1);
    }

    @Override
    public LocalDateTime getNextDateOfTaskAtTheDate(LocalDateTime localDateTime) {
        LocalDateTime nextDate = LocalDateTime.of(getDate().toLocalDate(), getDate().toLocalTime());
        if (localDateTime.isAfter(getDate())) {
            while (nextDate.toLocalDate().isBefore(localDateTime.toLocalDate())) {
                nextDate = nextDate.plusDays(1);
            }
        }
        return nextDate;
    }
}
