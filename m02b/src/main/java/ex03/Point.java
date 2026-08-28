package ex03;

/*
 * Урок 2b.3 — «Records».
 *
 * record Point — «класс-запись»: одна строка объявления, а компилятор
 * САМ генерирует конструктор, аксессоры x() и y(), equals, hashCode
 * и toString. Поля record неизменяемы: точку нельзя «подвинуть»,
 * можно только создать новую.
 *
 * Проверка: m02b/src/test/java/ex03/PointTest.java (убери @Disabled).
 *
 * ШАГ 1. distanceTo: расстояние от этой точки до other по теореме
 * Пифагора — корень из (dx*dx + dy*dy). Корень — Math.sqrt(...),
 * он принимает и возвращает double. Свои координаты бери как x и y
 * (или x() и y() — это те же данные), чужие — other.x() и other.y().
 *
 * ШАГ 2 (со звёздочкой). static-фабрика origin: верни точку (0, 0).
 */
public record Point(int x, int y) {

    public double distanceTo(Point other) {
        // TODO: замени заглушку
        return 0;
    }

    public static Point origin() {
        // TODO: замени заглушку
        return null;
    }
}
