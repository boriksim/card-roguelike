package ex01;

/*
 * Урок 2a.1 — «Классы и объекты».
 *
 * Перед тобой класс Potion — чертёж зелья. Поля уже объявлены:
 * у КАЖДОГО объекта Potion будут свои name, volume и healPower.
 *
 * Проверка — JUnit-тесты: m02a/src/test/java/ex01/PotionTest.java.
 * Порядок работы:
 *   1) открой тест и убери строку @Disabled;
 *   2) запусти тест (▶ слева от имени класса) — он красный, так и задумано;
 *   3) реализуй методы ниже, пока все тесты не позеленеют.
 */
public class Potion {

    // Поля объекта. Тест создаёт зелья так:
    //   Potion small = new Potion();
    //   small.name = "Малое зелье";
    String name;    // название
    int volume;     // объём в мл
    int healPower;  // сколько HP восстанавливает

    // ШАГ 1. Верни описание зелья, собранное из ЕГО полей, ровно в виде:
    // Малое зелье (100 мл): +7 HP
    public String describe() {
        return name + " (" + volume + " мл): +" + healPower + " HP";
    }

    // ШАГ 2. Сильнее ли ЭТО зелье, чем other?
    // Сравни healPower этого объекта (можно писать просто healPower,
    // можно this.healPower — это одно и то же) с other.healPower.
    public boolean strongerThan(Potion other) {
        return this.healPower > other.healPower;
    }

    // ШАГ 3 (со звёздочкой). Слить два зелья в одно новое.
    // Создай new Potion() с именем "Микстура"; его объём и сила —
    // суммы объёмов и сил обоих зелий. Верни НОВЫЙ объект:
    // ни this, ни other меняться не должны.
    public Potion mixWith(Potion other) {
        Potion mixedPotion = new Potion();
        mixedPotion.name = "Микстура";
        mixedPotion.volume = this.volume + other.volume;
        mixedPotion.healPower = this.healPower + other.healPower;
        return mixedPotion;
    }
}
