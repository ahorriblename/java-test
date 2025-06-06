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

        setMagic(0);
        setMaxMagic(0);
        setMagicAbility(0);
    }

    public Goblin(int health, int speed, int strength, int stamina, int armor, int maxStamina,
                  int magic, int maxMagic, int magicAbility, Player target) {
        super(health, speed, strength, stamina, armor, maxStamina, magic, maxMagic,
                magicAbility, target);
    }

    void slash() {
        setDamage(getStrength() - this.getTarget().getArmor());
        setDamageToZeroIfNegative();

        this.getTarget().setHealth(this.getTarget().getHealth() - getDamage());

        setStamina(getStamina() - 1);

        System.out.println("\n" + getName() + " slashed " + getTarget().getName() + "!"
                + Color.ANSI_GREEN + " (1 Stamina)" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_RED + getName() + " dealt " + getDamage()
                + " damage to " + getTarget().getName() + "!" + Color.ANSI_RESET);
    }

    void sneakyStab() {
        setDamage(2 + (getSpeed() / 2)); // ignores armor and scales with speed, rounds down
        setDamageToZeroIfNegative();

        this.getTarget().setHealth(this.getTarget().getHealth() - getDamage());

        setStamina(getStamina() - 2);

        System.out.println("\n" + getName() + " used sneaky stab on " + getTarget().getName()
                + "!" + Color.ANSI_GREEN + " (2 Stamina)" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_RED + getName() + " dealt " + getDamage()
                + " damage to " + getTarget().getName() + "!" + Color.ANSI_RESET);
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