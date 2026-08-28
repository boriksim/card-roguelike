package ex06;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
 * Тесты к уроку 2b.6 «Исключения: throw».
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 *
 * Новинка здесь — assertThrows: он проверяет, что код БРОСАЕТ
 * исключение нужного типа:
 *
 *   assertThrows(КакоеИсключениеЖдём.class, () -> код);
 *
 * Запись «() -> ...» — это кусочек кода, который тест выполнит сам
 * и поймает исключение (что это за стрелка — разберём в модуле 4).
 * Если исключение не вылетело или вылетело другого типа — тест красный.
 * assertThrows возвращает пойманное исключение — можно проверить
 * его сообщение.
 */
@Disabled("Убери эту строку, когда начнёшь упражнение (урок 2b.6)")
class LootSplitterTest {

    @Test
    void splitsLootFairly() {
        assertEquals(25, LootSplitter.split(100, 4), "100 монет на четверых — по 25");
        assertEquals(33, LootSplitter.split(100, 3), "Целочисленное деление: остаток пропадает");
        assertEquals(0, LootSplitter.split(0, 5), "Ноль монет — не ошибка: всем по нулю");
    }

    @Test
    void refusesNegativeCoins() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> LootSplitter.split(-1, 3),
                "Отрицательное число монет — ждём IllegalArgumentException");
        assertFalse(e.getMessage() == null || e.getMessage().isBlank(),
                "У исключения должно быть понятное сообщение — его прочитает тот, кто ошибся");
    }

    @Test
    void refusesToSplitAmongNobody() {
        assertThrows(IllegalArgumentException.class, () -> LootSplitter.split(100, 0),
                "Делить на ноль участников нельзя — ждём IllegalArgumentException");
        assertThrows(IllegalArgumentException.class, () -> LootSplitter.split(100, -2),
                "И на отрицательное число участников тоже");
    }
}
