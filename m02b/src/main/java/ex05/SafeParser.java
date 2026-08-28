package ex05;

/*
 * Урок 2b.5 — «Исключения: try/catch».
 *
 * Integer.parseInt превращает строку в число, но если в строке
 * НЕ число — бросает NumberFormatException, и программа падает.
 * Ровно так падал бы readInt в игре, если бы не try/catch.
 *
 * Проверка: m02b/src/test/java/ex05/SafeParserTest.java (убери @Disabled).
 *
 * ШАГ 1. safeParse: верни число из строки text; если превратить
 * не получилось (буквы, пустая строка, мусор) — верни fallback.
 * Схема:
 *   try {
 *       // попытка: parseInt
 *   } catch (NumberFormatException e) {
 *       // запасной план: fallback
 *   }
 * Пробелы по краям (" 7 ") — не ошибка: убери их методом trim()
 * ДО разбора.
 */
public class SafeParser {

    public static int safeParse(String text, int fallback) {
        // TODO: замени заглушку
        return 0;
    }
}
