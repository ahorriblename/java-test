public class Warrior extends Player {
    public Warrior() {
        super();
    }

    public Warrior(int health, int speed, int strength, int stamina, int armor, Enemy target) {
        super(health, speed, strength, stamina, armor, target);
    }

    void crush() {
        setDamage(getStrength() - this.getTarget().getArmor() + 3);
        this.getTarget().setHealth(this.getTarget().getHealth() - getDamage());

        System.out.println(getName() + " crushed " + getTarget().getName() + "!");
        System.out.println(getName() + " dealt " + getDamage() + " damage to "
                + getTarget().getName() + "!");
    }
}
