package ex03;

import java.util.Locale;

/*
 * Урок 1.3 — «Строки».
 *
 * Задание: собери текстовое описание карты тремя способами.
 *
 * Проверка: запусти (▶ слева от main) и сверь вывод с expected-output.txt.
 */
public class Exercise {

    public static void main(String[] args) {
        // Исходные данные карты — уже объявлены, используй их.
        String cardName = "Удар";
        int damage = 6;
        int cost = 1;

        // ШАГ 1. Конкатенацией (+) собери и выведи строку:
        // Карта: Удар (урон 6)
        System.out.println("Карта: " + cardName + " (урон " + damage + ")");

        // ШАГ 2. То же самое, но через String.format:
        // [Удар] урон 6, стоимость 1
        // Подсказка: %s — подставить строку, %d — подставить целое число.
        // String.format("[%s] урон %d, стоимость %d", cardName, damage, cost)
        System.out.println(String.format("[%s] урон %d, стоимость %d", cardName, damage, cost));

        // ШАГ 3. Оформи «шапку» карты методами String:
        //   - имя капсом:                 cardName.toUpperCase()
        //   - разделитель из 20 знаков =: "=".repeat(20)
        //   - длина имени:                cardName.length()
        // Чтобы получилось:
        // УДАР
        // ====================
        // Символов в имени: 4
        System.out.println(cardName.toUpperCase());
        System.out.println("=".repeat(20));
        System.out.println("Символов в имени: " + cardName.length());
    }
}
