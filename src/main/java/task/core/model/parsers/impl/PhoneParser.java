package task.core.model.parsers.impl;

import task.core.model.UserRecord;
import task.core.model.parsers.FieldParser;

public class PhoneParser extends FieldParser {
    @SuppressWarnings("FieldCanBeLocal")
    private final String regexFilter = "[\\d]+";
    private Integer errorCode = 4;

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public Integer apply(String userData, UserRecord dbRecord) {
        if (userData.matches(regexFilter))
            try {
                dbRecord.setPhoneNumber(Long.parseLong(userData));
                errorCode = 0;
            } catch (IllegalArgumentException e) {
                errorCode = 8;
            }
        return errorCode;
    }
}
