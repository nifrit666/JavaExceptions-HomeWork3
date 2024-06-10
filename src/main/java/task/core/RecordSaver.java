package task.core;
/*
Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии.
В него в одну строку должны записаться полученные данные, вида
Фамилия Имя Отчество, дата рождения, номер телефона, пол

Однофамильцы должны записаться в один и тот же файл, в отдельные строки.

При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано,
пользователь должен увидеть стектрейс ошибки.
 */

import task.core.model.UserRecord;
import task.core.repository.DBConnector;

import java.util.List;
import java.util.Map;

public class RecordSaver {
    private final String userRecord;
    private UserRecord dbRecord;
    protected static final Map<Integer, String> saveStatus = Map.of(
            0,"Данные сохранены успешно",
            -1,"ERROR: Введено недостаточное количество данных",
            1,"ERROR: Введено избыточное количество данных",
            2,"ERROR: ФИО не найдено",
            3,"ERROR: Дата рождения не найдена",
            4,"ERROR: Номер телефона не найден",
            5,"ERROR: Пол не определен",
            6,"ERROR: Ошибка записи в файл",
            7, "ERROR: Неверный формат даты",
            8, "ERROR: Неверный формат телефонного номера");
    public RecordSaver(String record) {
        if (record.startsWith("\s")) {
            record = record.replaceFirst("[\s]+","");
        }
        this.userRecord = record;
        this.dbRecord = new UserRecord();
    }
    public String saveRecord() {
        RecordParser recParser = new RecordParser(userRecord);
        int errorCode = recParser.parseRecord();
        if (errorCode == 0){
            this.dbRecord = recParser.getDbRecord();
            DBConnector db = new DBConnector(dbRecord.getLastName()+".txt");
            List<String> dbRecords = db.readAll();
            if (!dbRecords.contains(dbRecord.toString())){
                db.add(dbRecord.toString());
            } else {
                System.out.println("Данные уже содержатся в базе данных");
                errorCode = 6;
            }
        }
        return saveStatus.get(errorCode);
    }
}
