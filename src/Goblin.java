public class Goblin extends Enemy {
    public Goblin() {
        super();
    }

    public Goblin(int health, int speed, int strength, int stamina, int armor, Player target) {
        super(health, speed, strength, stamina, armor, target);
    }

    void slash() {
        setDamage(getStrength() - this.getTarget().getArmor());
        this.getTarget().setHealth(this.getTarget().getHealth() - getDamage());

        System.out.println(getName() + " used slashed " + getTarget().getName() + "!");
        System.out.println(getName() + " dealt " + getDamage() + " damage to "
                + getTarget().getName() + "!");
    }
}