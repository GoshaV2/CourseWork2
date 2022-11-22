package model.task;

import java.time.LocalDateTime;

public interface RepetitiveTask {
    LocalDateTime getNextDateOfTaskAtTheDate();

    LocalDateTime getNextDateOfTaskAtTheDate(LocalDateTime localDateTime);
}
