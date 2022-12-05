package model.task;

import model.task.type.TypePeriodTask;

import java.time.LocalDateTime;

public class AnnualTask extends Task{
    public AnnualTask() {
        super(TypePeriodTask.ANNUAL);
    }

    @Override
    public LocalDateTime getNextDateOfTaskAtTheDate() {
        return getDate().plusYears(1);
    }

    @Override
    public LocalDateTime getNextDateOfTaskAtTheDate(LocalDateTime localDateTime) {
        LocalDateTime nextDate = LocalDateTime.of(getDate().toLocalDate(), getDate().toLocalTime());
        if (localDateTime.isAfter(getDate())) {
            while (nextDate.toLocalDate().isBefore(localDateTime.toLocalDate())) {
                nextDate = nextDate.plusYears(1);
            }
        }
        return nextDate;
    }
}
