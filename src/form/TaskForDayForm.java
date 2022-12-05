package form;

import exception.IncorrectArgumentException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TaskForDayForm {
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) throws IncorrectArgumentException {
        if (!date.matches("\\d{2}-\\d{2}-\\d* \\d{2}:\\d{2}")) {
            throw new IncorrectArgumentException("Введите корректную дату в формате '12-01-2022 03:01':");
        }
        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            df.setLenient(false);
            df.parse(date);
        } catch (ParseException e) {
            throw new IncorrectArgumentException("Введите корректную дату в формате '12-01-2022 03:01':");
        }
        this.date = date;
    }
}
