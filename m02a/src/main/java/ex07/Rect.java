package ex07;

/*
 * Урок 2a.7 — «Абстрактные классы».
 *
 * ШАГ 1. Прямоугольник:
 *   - объяви private-поля width и height типа double;
 *   - сохрани параметры конструктора в поля;
 *   - реализуй area(): ширина умножить на высоту.
 *
 * Проверка — JUnit-тесты: m02a/src/test/java/ex07/ShapeTest.java
 * (не забудь убрать там @Disabled).
 */
public class Rect extends Shape {

    // TODO: поля

    public Rect(double width, double height) {
        // TODO: сохрани параметры
    }

    @Override
    public double area() {
        // TODO: замени заглушку
        return -1;
    }
}
