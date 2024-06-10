package task.core.model.parsers.impl;

import task.core.model.UserRecord;
import task.core.model.parsers.FieldParser;

public class FIOParser extends FieldParser {
    @SuppressWarnings("FieldCanBeLocal")
    private final String regexFilter = "[A-z]*[А-я]*";
    private Integer errorCode = 2;

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }
    @Override
    public Integer apply(String userData, UserRecord dbRecord) {
        if (userData.length() > 1) {
            if (userData.matches(regexFilter)) {
                if (dbRecord.getLastName() == null) {
                    dbRecord.setLastName(userData);
                } else if (dbRecord.getFirstName() == null) {
                    dbRecord.setFirstName(userData);
                } else if (dbRecord.getSecondName() == null) {
                    dbRecord.setSecondName(userData);
                    errorCode = 0;
                }
            }
        }
        return errorCode;
    }
}
