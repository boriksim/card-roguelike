package game;

public class Card {
    static int totalCreated = 0;

    private final String name;
    private final int cost;
    private final int damage;
    private final int block;
    private final int heal;

    Card(String name, int cost, int damage, int block, int heal) {
        totalCreated++;

        this.name = name;
        this.cost = cost;
        this.damage = damage;
        this.block = block;
        this.heal = heal;
    }

    static Card[] starter() {
        return new Card[] {
                new Card("Удар", 1, 6, 0, 0),
                new Card("Щит", 1, 0, 5, 0),
                new Card("Зелье", 2, 0, 0, 7),
                new Card("Удар Щитом", 3, 5, 4, 0),
        };
    }

    public String describe() {
        String text = "";
        if (damage > 0) {
            text = text + " урон " + damage;
        }
        if (block > 0) {
            text = text + " блок " + block;
        }
        if (heal > 0) {
            text = text + " лечение " + heal;
        }
        return text;
    }

    @Override
    public String toString() {
        return name + " (стоимость " + cost + "):" + describe();
    }

    public String getName() { return name; }
    public int getCost() { return cost; }
    public int getDamage() { return damage; }
    public int getBlock() { return block; }
    public int getHeal() { return heal; }
}
