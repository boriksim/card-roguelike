package ex05;

/*
 * ГОТОВЫЙ базовый класс — читай, но не меняй.
 * Твоя работа — в файле Wolf.java рядом.
 */
public class Animal {

    // protected: поля видны этому классу и его наследникам.
    protected String name;
    protected int hp;

    // Запасной конструктор «без всего». Если наследник не вызовет
    // super(...) сам, Java тихо вызовет именно этот — и зверь
    // останется безымянным. Помни об этом, когда возьмёшься за Wolf!
    public Animal() {
        this("Безымянный зверь", 1);
    }

    public Animal(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void takeDamage(int damage) {
        hp = Math.max(0, hp - damage);
    }

    public boolean isAlive() {
        return hp > 0;
    }
}
