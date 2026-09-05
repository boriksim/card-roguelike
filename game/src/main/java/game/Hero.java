package game;

public class Hero {
    private final String name;
    private final int maxHp;
    private int hp;
    private int block;

    public Hero(String name, int maxHp) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;      // герой рождается здоровым
    }

    public void takeDamage(int damage) {
        int hit = Math.max(0, damage - block);
        block = Math.max(0, block - damage);
        hp = Math.max(0, hp - hit);
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }

    public void addBlock(int amount) {
        block = block + Math.max(0, amount);
    }

    public void resetBlock() {
        block = 0;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getBlock() { return block; }
    public String getName() { return name; }
}
