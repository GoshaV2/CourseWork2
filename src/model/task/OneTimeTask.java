package model.task;

import model.task.type.TypePeriodTask;

public class OneTimeTask extends Task {
    public OneTimeTask() {
        super(TypePeriodTask.ONETIME);
    }
}
