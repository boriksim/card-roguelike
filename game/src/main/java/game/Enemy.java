package game;

abstract class Enemy {

    private final String name;
    private final int maxHp;
    private int hp;
    private int block;

    public Enemy(String name, int maxHp) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
    }

    // Входящий удар: блок гасит урон, остаток снимает hp (но не ниже 0).
    public void takeDamage(int damage) {
        int through = Math.max(0, damage - block);
        block = Math.max(0, block - damage);
        hp = Math.max(0, hp - through);
    }

    abstract String chooseIntent();
    abstract void act(Hero hero);

    public void addBlock(int amount) { block = block + Math.max(0, amount); }
    public void resetBlock() { block = 0; }
    public boolean isAlive() { return hp > 0; }

    public String getName() { return this.name; }
    public int getMaxHp()   { return this.maxHp; }
    public int getHp()      { return this.hp; }
    public int getBlock()   { return this.block; }
}
