package form;

import exception.IncorrectArgumentException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GetTasksForDateIntervalForm {
    private String localDateTimeFirst;
    private String localDateTimeSecond;

    public String getLocalDateTimeFirst() {
        return localDateTimeFirst;
    }

    public void setLocalDateTimeFirst(String localDateTimeFirst) throws IncorrectArgumentException {
        if (!localDateTimeFirst.matches("\\d{2}-\\d{2}-\\d* \\d{2}:\\d{2}")) {
            throw new IncorrectArgumentException("Введите корректную дату в формате '12-01-2022 03:01':");
        }

        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            df.setLenient(false);
            df.parse(localDateTimeFirst);
        } catch (ParseException e) {
            throw new IncorrectArgumentException("Введите корректную дату в формате '12-01-2022 03:01':");
        }
        this.localDateTimeFirst = localDateTimeFirst;
    }

    public String getLocalDateTimeSecond() {
        return localDateTimeSecond;
    }

    public void setLocalDateTimeSecond(String localDateTimeSecond) throws IncorrectArgumentException {
        if (!localDateTimeSecond.matches("\\d{2}-\\d{2}-\\d* \\d{2}:\\d{2}")) {
            throw new IncorrectArgumentException("Введите корректную дату в формате '12-01-2022 03:01':");
        }
        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            df.setLenient(false);
            df.parse(localDateTimeSecond);
        } catch (ParseException e) {
            throw new IncorrectArgumentException("Введите корректную дату в формате '12-01-2022 03:01':");
        }
        this.localDateTimeSecond = localDateTimeSecond;
    }
}
