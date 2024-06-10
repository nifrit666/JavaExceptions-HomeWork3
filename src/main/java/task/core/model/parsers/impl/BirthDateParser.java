package task.core.model.parsers.impl;

import task.core.model.UserRecord;
import task.core.model.parsers.FieldParser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BirthDateParser extends FieldParser {
    @SuppressWarnings("FieldCanBeLocal")
    private final String regexFilter = "[0-3][0-9]\\.[0-1][0-9]\\.[0-9]{4}";
    private Integer errorCode = 3;

    public Integer getErrorCode() {
        return errorCode;
    }

    public Integer apply(String userData, UserRecord dbRecord) {
        if (userData.matches(regexFilter)) {
            if (isValidDate(userData)){
                dbRecord.setBirthDate(userData);
                errorCode = 0;
            } else {
                errorCode = 7;
            }
        }
        return errorCode;
    }
    private boolean isValidDate(String date){
        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date.substring(3,5) + "/" + date.substring(0,2) + "/" + date.substring(6,10));
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
