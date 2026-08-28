package ex01;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Тесты к уроку 2b.1 «Интерфейсы».
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 */
@Disabled("Убери эту строку, когда начнёшь упражнение (урок 2b.1)")
class NotifierTest {

    @Test
    void emailNotifierFollowsTheContract() {
        EmailNotifier email = new EmailNotifier("boss@corp.com");
        assertEquals("email", email.channel(), "Канал почтового уведомителя — \"email\"");
        assertEquals("email -> boss@corp.com: Привет", email.deliver("Привет"),
                "Отчёт собирается из канала, адреса и текста — смотри формат в комментарии класса");
    }

    @Test
    void smsNotifierFollowsTheContract() {
        SmsNotifier sms = new SmsNotifier("+48 111 22 33");
        assertEquals("sms", sms.channel(), "Канал SMS-уведомителя — \"sms\"");
        assertEquals("sms -> +48 111 22 33: Привет", sms.deliver("Привет"),
                "Отчёт собирается из канала, номера и текста");
    }

    @Test
    void oneInterfaceVariableFitsBothImplementations() {
        // Массив типа Notifier, а внутри — РАЗНЫЕ классы. Код ниже не знает,
        // с кем работает: он зовёт методы интерфейса, а объект сам решает.
        Notifier[] notifiers = { new EmailNotifier("a@b.c"), new SmsNotifier("+48 000") };
        String report = "";
        for (Notifier notifier : notifiers) {
            report = report + notifier.deliver("тест") + "\n";
        }
        assertTrue(report.contains("email ->"), "В общем отчёте должна быть доставка по email");
        assertTrue(report.contains("sms ->"), "В общем отчёте должна быть доставка по sms");
    }
}
