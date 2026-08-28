package ex04;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Тесты к уроку 2b.4 «Аннотации».
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 *
 * Здесь НЕ нужно писать новый код — нужно найти и починить ошибку
 * в LoudAlarm. Красный первый тест покажет её симптом.
 */
@Disabled("Убери эту строку, когда начнёшь упражнение (урок 2b.4)")
class AlarmTest {

    @Test
    void loudAlarmShouts() {
        // Полиморфизм: переменная типа Alarm, объект — LoudAlarm.
        // Какой warning() выполнится, решает ОБЪЕКТ — но только если
        // метод действительно переопределён.
        Alarm alarm = new LoudAlarm();
        assertEquals("ВНИМАНИЕ!!!", alarm.warning(),
                "Громкая сигнализация должна кричать. Если тут тихое «Внимание!» — "
                        + "метод родителя не подменился: сравни имена методов по буквам");
    }

    @Test
    void baseAlarmStaysCalm() {
        Alarm alarm = new Alarm();
        assertEquals("Внимание!", alarm.warning(),
                "Базовая сигнализация правильная — её менять не нужно");
    }
}
