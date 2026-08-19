package ex08;

/*
 * Урок 2a.8 — мини-задача на всё сразу.
 *
 * ГЛАВНАЯ работа урока — в модуле game: доведи игру до v0.2
 * (классы Card / Hero / Enemy, три вида врагов и босс) по чек-листу
 * урока и закоммить с пометкой v0.2.
 *
 * А здесь — песочница на все концепты модуля без игры: классы,
 * инкапсуляция, наследование, полиморфизм, abstract и static.
 * Городская служба доставки выбирает транспорт для заказа.
 *
 * ГОТОВАЯ часть этого файла — поля, конструктор и геттеры. Твой ход —
 * ШАГ 3 внизу. Начинай с Taxi.java (ШАГ 1) и Bus.java (ШАГ 2).
 *
 * Проверка — JUnit-тесты: m02a/src/test/java/ex08/FleetTest.java
 * (не забудь убрать там @Disabled).
 */
public abstract class Vehicle {

    // Инкапсуляция: состояние private, снаружи — только геттеры.
    private final String name;
    private final int capacity; // сколько посылок увозит за рейс

    // Конструктор родителя: наследники зовут его через super(...).
    public Vehicle(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    // Цена рейса на dist километров — у каждого транспорта своя формула.
    public abstract int tripPrice(int dist);

    // ШАГ 3 (после Taxi и Bus). Общий метод для ВСЕХ наследников:
    // цена доставки одной посылки = цена рейса, делённая на вместимость
    // (целочисленно). Пиши через tripPrice(dist) и getCapacity() —
    // и метод будет работать для любого транспорта, даже будущего.
    public int pricePerParcel(int dist) {
        // TODO: замени заглушку
        return -1;
    }
}
