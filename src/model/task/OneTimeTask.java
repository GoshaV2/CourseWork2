package model.task;

import model.task.type.TypePeriodTask;

import java.time.LocalDateTime;

public class OneTimeTask extends Task {
    public OneTimeTask() {
        super(TypePeriodTask.ONETIME);
    }

    @Override
    public LocalDateTime getNextDateOfTaskAtTheDate() {
        return getDate();
    }

    @Override
    public LocalDateTime getNextDateOfTaskAtTheDate(LocalDateTime localDateTime) {
        return getNextDateOfTaskAtTheDate();
    }
}
