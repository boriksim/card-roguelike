package ex05;

/*
 * Урок 2a.5 — «Наследование».
 *
 * Wolf extends Animal: волк УЖЕ умеет всё, что умеет Animal —
 * getName(), getHp(), takeDamage() и isAlive() заново писать не нужно.
 *
 * Проверка — JUnit-тесты: m02a/src/test/java/ex05/WolfTest.java
 * (не забудь убрать там @Disabled).
 */
public class Wolf extends Animal {

    public static final int BITE_DAMAGE = 6;

    // ШАГ 1. Конструктор пуст, поэтому Java сама вызывает Animal() —
    // запасной конструктор родителя, — и любой волк рождается
    // «Безымянным зверем» с 1 HP. Почини: передай name и hp родителю
    // строкой super(name, hp); — она должна быть первой.
    public Wolf(String name, int hp) {
        // TODO: одна строка
    }

    // ШАГ 2. Новая способность, которой у Animal нет: охота.
    //   - укуси добычу на BITE_DAMAGE — у неё есть takeDamage(),
    //     она ведь тоже Animal;
    //   - после удачной охоты волк отъедается: подними СВОЁ hp на 2
    //     (поле hp у родителя protected — наследнику можно напрямую).
    public void hunt(Animal prey) {
        // TODO
    }
}
