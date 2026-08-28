package ex05;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Тесты к уроку 2b.5 «Исключения: try/catch».
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 */
@Disabled("Убери эту строку, когда начнёшь упражнение (урок 2b.5)")
class SafeParserTest {

    @Test
    void parsesNormalNumbers() {
        assertEquals(42, SafeParser.safeParse("42", 0), "Обычное число разбирается как есть");
        assertEquals(-5, SafeParser.safeParse("-5", 0), "Отрицательные числа тоже числа");
        assertEquals(7, SafeParser.safeParse(" 7 ", 0), "Пробелы по краям — не ошибка: trim()");
    }

    @Test
    void fallsBackOnGarbage() {
        assertEquals(0, SafeParser.safeParse("abc", 0), "Буквы — не число: возвращается fallback");
        assertEquals(0, SafeParser.safeParse("", 0), "Пустая строка — не число");
        assertEquals(0, SafeParser.safeParse("12abc", 0), "Число с хвостом из букв — тоже не число");
    }

    @Test
    void fallbackIsWhatCallerAskedFor() {
        assertEquals(99, SafeParser.safeParse("мусор", 99),
                "Запасное значение задаёт вызывающий код, а не метод");
        assertEquals(-1, SafeParser.safeParse("2 + 2", -1),
                "parseInt не калькулятор: выражение — это мусор, вернётся fallback");
    }
}
