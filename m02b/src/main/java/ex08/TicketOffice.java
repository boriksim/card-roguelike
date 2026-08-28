package ex08;

/*
 * Касса. Проверка: m02b/src/test/java/ex08/TicketOfficeTest.java
 * (убери @Disabled).
 *
 * ШАГ 2. categoryFor: категория по возрасту.
 *   - возраст < 0 — throw new IllegalArgumentException с понятным сообщением;
 *   - до 12 лет (0–11) — CHILD;
 *   - 65 и старше — SENIOR;
 *   - остальные — ADULT.
 *
 * ШАГ 3. priceFor: цена по категории — switch по enum:
 *   - CHILD  — половина basePrice;
 *   - SENIOR — три четверти basePrice (basePrice * 3 / 4);
 *   - ADULT  — полный basePrice.
 *
 * ШАГ 4. sell: собери всё вместе.
 *   - ageText приходит из «поля ввода» — разбери его через
 *     Integer.parseInt в try/catch; если там не число, брось
 *     IllegalArgumentException с понятным сообщением (в catch
 *     можно бросать своё исключение — так мусор на входе
 *     превращается в осмысленную ошибку);
 *   - дальше categoryFor + priceFor и верни новый Ticket.
 */
public class TicketOffice {

    public static Category categoryFor(int age) {
        // TODO: замени заглушку
        return null;
    }

    public static int priceFor(Category category, int basePrice) {
        // TODO: замени заглушку
        return 0;
    }

    public static Ticket sell(String event, String ageText, int basePrice) {
        // TODO: замени заглушку
        return null;
    }
}
