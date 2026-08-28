package ex01;

/*
 * ШАГ 2. Вторая реализация того же контракта.
 *
 * deliver: верни отчёт ровно в виде
 *   sms -> номер: текст
 * Например, у new SmsNotifier("+48 111 22 33") вызов deliver("Привет")
 * возвращает: sms -> +48 111 22 33: Привет
 *
 * channel: верни "sms".
 *
 * Заметь: у классов-реализаций нет общего предка, кроме Object, —
 * их объединяет только интерфейс. Переменная типа Notifier может
 * хранить любую из реализаций — это полиморфизм через интерфейс.
 */
public class SmsNotifier implements Notifier {

    private final String phone; // куда слать SMS

    public SmsNotifier(String phone) {
        this.phone = phone;
    }

    @Override
    public String deliver(String text) {
        // TODO: замени заглушку
        return "";
    }

    @Override
    public String channel() {
        // TODO: замени заглушку
        return "";
    }
}
