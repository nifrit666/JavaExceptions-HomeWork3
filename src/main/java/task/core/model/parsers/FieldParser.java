package task.core.model.parsers;

import task.core.model.UserRecord;

public abstract class FieldParser {
    private final Integer errorCode = 0;

    public Integer getErrorCode() {
        return errorCode;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Integer apply(String userData, UserRecord dbRecord){return errorCode;}
}
