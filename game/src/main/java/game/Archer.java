package game;

public class Archer extends Enemy {

    static final int HP = 18;
    static final int ARROW_DAMAGE = 5;
    static final int SHOTS = 2;

    private int turn = 0;
    private boolean shootsNow; // план: стрелять в этот ход?

    public Archer() {
        super("Лучник", HP);
    }

    @Override
    public String chooseIntent() {
        turn = turn + 1;
        shootsNow = (turn % 2 == 0); // нечётный ход — прицел, чётный — залп
        if (shootsNow) {
            return "двойной выстрел (" + SHOTS + " x " + ARROW_DAMAGE + ")";
        }
        return "прицеливается (в следующий ход будет больно)";
    }

    @Override
    public void act(Hero hero) {
        if (!shootsNow) {
            System.out.println(Ansi.RED + getName() + " прицеливается..." + Ansi.RESET);
            return; // ранний выход: в этот ход урона нет
        }
        for (int shot = 1; shot <= SHOTS; shot++) {
            int before = hero.getHp();
            hero.takeDamage(ARROW_DAMAGE);
            System.out.println(Ansi.RED + getName() + " стреляет (" + shot + "-я стрела): "
                    + hero.getName() + " получает " + (before - hero.getHp()) + " урона." + Ansi.RESET);
        }
    }
}