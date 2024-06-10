package task.core;
/*
Приложение должно проверить введенные данные по количеству.
Если количество не совпадает с требуемым, вернуть код ошибки.
Создать метод, который обработает его и покажет пользователю сообщение,
что он ввел меньше или больше данных, чем требуется.

Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры.
Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы.
Можно использовать встроенные типы java или создать свои. Исключение должно быть корректно обработано,
пользователю выведено сообщение с информацией, что именно неверно.
 */

import task.core.model.UserRecord;
import task.core.model.parsers.FieldParser;
import task.core.model.parsers.impl.BirthDateParser;
import task.core.model.parsers.impl.FIOParser;
import task.core.model.parsers.impl.GenderParser;
import task.core.model.parsers.impl.PhoneParser;

import java.util.ArrayList;
import java.util.List;

public class RecordParser {
    private final String userRecord;
    private final UserRecord dbRecord;

    public UserRecord getDbRecord() {
        return dbRecord;
    }

    public RecordParser(String record) {
        this.userRecord = record;
        this.dbRecord = new UserRecord();
    }
    public Integer parseRecord() {
        String[] userData = userRecord.split("[\s]+");
        Integer errorCode = isEnoughArguments(userData);
        if (errorCode == 0) {
            List<FieldParser> parsers = initParsers();
            errorCode = ParseUserData(userData, parsers);
        }
        return errorCode;
    }
    private Integer ParseUserData(String[] userData, List<FieldParser> parsers) {
        int errorCode = 0;
        for (String field: userData) {
            for (FieldParser parser : parsers){
                parser.apply(field, dbRecord);
            }
        }
        for (FieldParser parser : parsers){
            if (parser.getErrorCode() != 0){
                System.out.println(RecordSaver.saveStatus.get(parser.getErrorCode()));
                errorCode = 6;
            }
        }
        return errorCode;
    }

    private List<FieldParser> initParsers() {
        List<FieldParser> parsers = new ArrayList<>();
        parsers.add(new FIOParser());
        parsers.add(new BirthDateParser());
        parsers.add(new PhoneParser());
        parsers.add(new GenderParser());
        return parsers;
    }

    private Integer isEnoughArguments(String[] userData ) {
        int argsCountNeeded = dbRecord.getClass().getDeclaredFields().length;
        int argsCountProvided = userData.length;
        return Integer.compare(argsCountProvided, argsCountNeeded);
    }
}
