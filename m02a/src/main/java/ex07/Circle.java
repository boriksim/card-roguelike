package ex07;

/*
 * Урок 2a.7 — «Абстрактные классы».
 *
 * ШАГ 2. Круг:
 *   - объяви private-поле radius типа double;
 *   - сохрани параметр конструктора;
 *   - реализуй area(): Math.PI * radius * radius.
 */
public class Circle extends Shape {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }
}
