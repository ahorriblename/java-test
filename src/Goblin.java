public class Goblin extends Enemy {
    public Goblin() {
        super();
        setHealth(10);
        setArmor(2);
        setSpeed(5);
        setName("Goblin");
        setStamina(2);
        setStrength(3);
    }

    public Goblin(int health, int speed, int strength, int stamina, int armor, Player target) {
        super(health, speed, strength, stamina, armor, target);
    }

    void slash() {
        setDamage(getStrength() - this.getTarget().getArmor());
        this.getTarget().setHealth(this.getTarget().getHealth() - getDamage());
        setDamageToZeroIfNegative();

        System.out.println(getName() + " used slashed " + getTarget().getName() + "!");
        System.out.println(getName() + " dealt " + getDamage() + " damage to "
                + getTarget().getName() + "!");
    }

    void moveToUse() {
        switch(getMove()) {
            case "slash" -> slash();
            default -> System.out.print("No move");
        }
    }
}