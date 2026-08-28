package ex03;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/*
 * Тесты к уроку 2b.3 «Records».
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 *
 * Половина тестов здесь проверяет то, что ты НЕ писал: аксессоры,
 * equals и toString record генерирует сам. Убедись, что они зелёные
 * «бесплатно», — в этом и сила records.
 */
@Disabled("Убери эту строку, когда начнёшь упражнение (урок 2b.3)")
class PointTest {

    @Test
    void accessorsComeForFree() {
        Point point = new Point(3, 4);
        assertEquals(3, point.x(), "Аксессор x() генерируется компилятором");
        assertEquals(4, point.y(), "Аксессор y() генерируется компилятором");
    }

    @Test
    void equalsComparesByValueNotByReference() {
        // У обычного класса new Point(3,4).equals(new Point(3,4)) — false:
        // Object.equals сравнивает ссылки. У record — true: компилятор
        // написал equals, который сравнивает значения полей.
        assertEquals(new Point(3, 4), new Point(3, 4),
                "Два record с одинаковыми полями равны");
        assertNotEquals(new Point(3, 4), new Point(4, 3),
                "Порядок полей важен: (3,4) и (4,3) — разные точки");
    }

    @Test
    void toStringComesForFree() {
        assertEquals("Point[x=3, y=4]", new Point(3, 4).toString(),
                "toString у record генерируется в формате Имя[поле=значение, ...]");
    }

    @Test
    void distanceUsesPythagoras() {
        Point a = new Point(0, 0);
        Point b = new Point(3, 4);
        // Третий аргумент — допуск: double нельзя сравнивать на точное равенство.
        assertEquals(5.0, a.distanceTo(b), 1e-9, "Египетский треугольник: 3-4-5");
        assertEquals(5.0, b.distanceTo(a), 1e-9, "Расстояние одинаково в обе стороны");
        assertEquals(0.0, a.distanceTo(Point.origin()), 1e-9,
                "origin() — точка (0,0); расстояние от (0,0) до неё — ноль");
    }
}
