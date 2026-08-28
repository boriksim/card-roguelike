package ex01;

/*
 * ШАГ 1. Класс «подписал контракт» (implements Notifier), поэтому ОБЯЗАН
 * реализовать все его методы — попробуй закомментировать один из них
 * и посмотри, что скажет компилятор.
 *
 * deliver: верни отчёт ровно в виде
 *   email -> адрес: текст
 * Например, у new EmailNotifier("boss@corp.com") вызов deliver("Привет")
 * возвращает: email -> boss@corp.com: Привет
 *
 * channel: верни "email".
 */
public class EmailNotifier implements Notifier {

    private final String address; // куда слать письма

    public EmailNotifier(String address) {
        this.address = address;
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
