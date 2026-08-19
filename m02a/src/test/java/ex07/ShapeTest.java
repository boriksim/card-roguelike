package ex07;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Тесты к уроку 2a.7 «Абстрактные классы».
 * Убери строку @Disabled ниже, когда возьмёшься за упражнение.
 */
@Disabled("Убери эту строку, когда начнёшь упражнение (урок 2a.7)")
class ShapeTest {

    @Test
    void rectKnowsItsArea() {
        assertEquals(12.0, new Rect(3, 4).area(), 1e-9, "Площадь прямоугольника 3 x 4 равна 12");
        assertEquals(2.25, new Rect(1.5, 1.5).area(), 1e-9, "Площадь прямоугольника 1.5 x 1.5 равна 2.25");
    }

    @Test
    void circleKnowsItsArea() {
        assertEquals(Math.PI * 4, new Circle(2).area(), 1e-9,
                "Площадь круга радиуса 2: Math.PI * 2 * 2");
    }

    @Test
    void commonMethodWorksThroughAbstractArea() {
        Shape rect = new Rect(3, 4);   // площадь 12
        Shape circle = new Circle(1);  // площадь ~3.14
        assertTrue(rect.biggerThan(circle),
                "biggerThan написан в Shape один раз, а сравнивает любые фигуры через их area()");
        assertFalse(circle.biggerThan(rect), "Круг радиуса 1 меньше прямоугольника 3 x 4");
    }

    @Test
    void differentShapesLiveInOneArray() {
        Shape[] shapes = { new Rect(2, 3), new Circle(1), new Rect(1, 1) };
        double total = 0;
        for (Shape shape : shapes) {
            total = total + shape.area(); // каждый считает площадь по-своему
        }
        assertEquals(6 + Math.PI + 1, total, 1e-9,
                "Массив имеет тип Shape[], но площадь каждая фигура считает своей формулой");
    }
}
