package ex08;

/*
 * ШАГ 1. Такси: возит 2 посылки, цена рейса — 50 за подачу
 * плюс 15 за каждый километр.
 */
public class Taxi extends Vehicle {

    public Taxi() {
        super("Такси", 2); // имя и вместимость уезжают в поля родителя
    }

    @Override
    public int tripPrice(int dist) {
        return 50 + 15 * dist;
    }
}
