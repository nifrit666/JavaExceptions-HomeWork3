package task.ui;
/*
запрашиваем у пользователя данные в произвольном порядке,
разделенные пробелом (данные вводятся одной строкой без запятых):
Фамилия Имя Отчество, дата рождения, номер телефона, пол
 */
import task.core.RecordSaver;

import java.util.Scanner;

public class AppView {

    public void run(){
        String userData = prompt("""
                Введите в произвольном порядке, одной сторокой без запятых, следующие данные:
                 - Фамилия, имя, отчество - строки символов (латиница или кириллица);
                 - Дата рождения - строка формата dd.mm.yyyy;
                 - Номер телефона - целое беззнаковое число;
                 - Пол - символ латиницей f или m.
                >\s""");
        RecordSaver rec = new RecordSaver(userData.toLowerCase());
        System.out.println(rec.saveRecord());
    }

    public String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
