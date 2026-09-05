package ex04;

/*
 * Урок 2a.4 — «static: у класса или у объекта?».
 *
 * Фабрика штампует миньонов. У каждого миньона СВОЁ имя и СВОЙ номер (id),
 * а счётчик выпущенных — ОДИН НА ВСЕХ: он принадлежит классу, а не
 * объекту. Такие поля и помечают словом static.
 *
 * Проверка — JUnit-тесты: m02a/src/test/java/ex04/MinionTest.java
 * (не забудь убрать там @Disabled).
 */
public class Minion {

    // static-поле: общий счётчик созданных миньонов. Он существует
    // в одном экземпляре, сколько бы объектов мы ни создали.
    private static int totalCreated = 0;

    // Обычные (не static) поля: у каждого миньона свои.
    private String name;
    private int id;

    // ШАГ 1. Допиши конструктор:
    //   - увеличь общий счётчик totalCreated на 1;
    //   - сохрани имя в поле объекта;
    //   - выдай миньону id, равный новому значению счётчика
    //     (первый созданный — id 1, второй — id 2, ...).
    public Minion(String name) {
        totalCreated++;
        this.name = name;
        this.id = totalCreated;
    }

    // ШАГ 2. Геттеры объекта: имя и номер ЭТОГО миньона.
    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    // ШАГ 3. static-метод: сколько миньонов создано ВСЕГО.
    // Заметь: ему не нужен объект — спросить можно у самого класса,
    // Minion.getTotalCreated(). Внутри static-метода видны только
    // static-поля: полей name и id здесь «не существует».
    public static int getTotalCreated() {
        return totalCreated;
    }

    // Сброс счётчика — нужен тестам, чтобы каждый тест начинался
    // с чистого листа. Готовый метод, менять не нужно.
    static void resetCounter() {
        totalCreated = 0;
    }
}
