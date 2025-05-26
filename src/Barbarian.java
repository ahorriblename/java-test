public class Barbarian extends Player {
    Barbarian() {
        super();
        setHealth(12);
        setArmor(0);
        setSpeed(6);
        setName("Barbarian");
        setStamina(5);
        setStrength(6);
    }

    Barbarian(int health, int speed, int strength, int stamina, int armor, Enemy target) {
        super(health, speed, strength, stamina, armor, target);
    }

    void clobber() {
        setDamage(getStrength() - this.getTarget().getArmor() + 1);
        this.getTarget().setHealth(this.getTarget().getHealth() - getDamage());
        setDamageToZeroIfNegative();

        setStamina(getStamina() - 2);

        System.out.println(getName() + " clobbered " + getTarget().getName() + "!");
        System.out.println(getName() + " dealt " + getDamage() + " damage to "
                + getTarget().getName() + "!");
    }

    void enrage() {
        setStrength(getStrength() + 2);
        setHealth(getHealth() - 1);

        setStamina(getStamina() - 1);

        System.out.println(getName() + " enraged, strength increased by 2! (Now "
                + getStrength() + ")");
        System.out.println("Took 1 damage from reckless anger!");
    }

    void moveToUse() {
        switch (getMove()) {
            case "clobber" -> clobber();
            case "enrage" -> enrage();
            default -> System.out.print("No move");
        }
    }
}
