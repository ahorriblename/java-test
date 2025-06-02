public class Goblin extends Enemy {
    public Goblin() {
        super();
        setHealth(10);
        setArmor(2);
        setSpeed(5);
        setName("Goblin");
        setStamina(2);
        setMaxStamina(2);
        setStrength(2);
    }

    public Goblin(int health, int speed, int strength, int stamina, int armor, int maxStamina,
                  Player target) {
        super(health, speed, strength, stamina, armor, maxStamina, target);
    }

    void slash() {
        setDamage(getStrength() - this.getTarget().getArmor());
        setDamageToZeroIfNegative();

        this.getTarget().setHealth(this.getTarget().getHealth() - getDamage());

        setStamina(getStamina() - 1);

        System.out.println(getName() + " used slashed " + getTarget().getName() + "!");
        System.out.println(getName() + " dealt " + getDamage() + " damage to "
                + getTarget().getName() + "!");
    }

    void sneakyStab() {
        setDamage(2 + (getSpeed() / 2)); // ignores armor and scales with speed, rounds down
        setDamageToZeroIfNegative();

        this.getTarget().setHealth(this.getTarget().getHealth() - getDamage());

        setStamina(getStamina() - 2);

        System.out.println(getName() + " used sneaky stab on " + getTarget().getName() + "!");
        System.out.println(getName() + " dealt " + getDamage() + " damage to "
                + getTarget().getName() + "!");
    }

    void moveToUse() {
        switch (getMove()) {
            case "slash" -> slash();
            case "sneakystab" -> sneakyStab();
            case "rest" -> rest();
            default -> System.out.print("No move!");
        }
    }

    void getEnemyMove() {
        if (getTarget().getArmor() > 0 && getStamina() >= 2) {
            storeMove("sneakystab");
        } else if (getTarget().getArmor() < 0 || getStamina() >= 1) {
            storeMove("slash");
        } else if (getStamina() == 0) {
            storeMove("rest");
        } else {
            storeMove("rest");
        }
    }
}