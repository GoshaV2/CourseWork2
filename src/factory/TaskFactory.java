package factory;

import model.task.*;
import model.task.type.TypePeriodTask;

public class TaskFactory {

    public static Task createTask(TypePeriodTask typePeriodTask) {
        if (typePeriodTask.equals(TypePeriodTask.ANNUAL)) {
            return new AnnualTask();
        } else if (typePeriodTask.equals(TypePeriodTask.MONTHLY)) {
            return new MonthlyTask();
        } else if (typePeriodTask.equals(TypePeriodTask.WEEKLY)) {
            return new WeeklyTask();
        } else if (typePeriodTask.equals(TypePeriodTask.DAILY)) {
            return new DailyTask();
        } else {
            return new OneTimeTask();
        }
    }
}
