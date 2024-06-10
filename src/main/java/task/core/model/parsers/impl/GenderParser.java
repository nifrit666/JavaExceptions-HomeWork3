package task.core.model.parsers.impl;

import task.core.model.UserRecord;
import task.core.model.parsers.FieldParser;

public class GenderParser extends FieldParser {
    @SuppressWarnings("FieldCanBeLocal")
    private final String regexFilter = "[FfMm]";
    private Integer errorCode = 5;

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public Integer apply(String userData, UserRecord dbRecord) {
        if (userData.matches(regexFilter)) {
            dbRecord.setGender(userData.charAt(0));
            errorCode = 0;
        }
        return errorCode;
    }
}
