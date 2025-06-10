public class Barbarian extends Player {
    private HealthPotion healthPotion;

    Barbarian() {
        super();
        setHealth(12);
        setSpeed(6);
        setStrength(6);
        setStamina(5);
        setMaxStamina(5);
        setArmor(0);
        setName("Barbarian");

        setMagic(0);
        setMaxMagic(0);
        setMagicAbility(0);

        this.healthPotion = new HealthPotion("Health Potion+", 2, this);
    }

    Barbarian(int health, int speed, int strength, int stamina, int maxStamina, int armor,
              String name, int magic, int maxMagic, int magicAbility, Enemy target) {
        super(health, speed, strength, stamina, maxStamina, armor, name, magic, maxMagic,
                magicAbility, target);

        this.healthPotion = new HealthPotion("Health Potion+", 2, this);
    }

    int getHealthPotionUses() {
        return this.healthPotion.getUses();
    }

    void clobber() {
        setDamage(getStrength() - this.getTarget().getArmor() + 1);
        setDamageToZeroIfNegative();

        this.getTarget().setHealth(this.getTarget().getHealth() - getDamage());

        setStamina(getStamina() - 2);

        System.out.println("\n" + getName() + " clobbered " + getTarget().getName() + "!"
                + Color.ANSI_GREEN + " (2 Stamina)" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_RED + getName() + " dealt " + getDamage() + " damage to "
                + getTarget().getName() + "!" + Color.ANSI_RESET);
    }

    void enrage() {
        setStrength(getStrength() + 2);
        setHealth(getHealth() - 1);

        setStamina(getStamina() - 1);

        System.out.println("\n" + Color.ANSI_PURPLE + getName() + " enraged, strength "
                + "increased by 2! (Now " + getStrength() + ")" + Color.ANSI_RESET
                + Color.ANSI_GREEN + " (1 Stamina)" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_RED + "Took 1 damage from reckless anger!"
                + Color.ANSI_RESET);
    }

    void moveToUse() {
        switch (getMove()) {
            case "clobber" -> clobber();
            case "enrage" -> enrage();
            case "rest" -> rest();
            case "healthpotion" -> this.healthPotion.use();
            default -> System.out.print("No move");
        }
    }

    boolean validateMove(String move) {
        // false continues loop

        if (move.equals("enrage") && this.getStamina() >= 1) {
            return true;
        } else if (move.equals("clobber") && this.getStamina() >= 2) {
            return true;
        } else if (move.equals("stats")) {
            System.out.println(getTarget());
            System.out.println(this);
            return false;
        } else if (move.equals("rest")) {
            return true;
        } else if (move.equals("healthpotion")) {
            return true;
        } else {
            System.out.println("enter valid move");
            return false;
        }
    }

    static void listMoves() {
        System.out.println("List of moves");
        System.out.println(" 1. Clobber (2 Stamina)");
        System.out.println(" 2. Enrage (1 Stamina)");
        System.out.println(" 3. Rest");
        System.out.println(" 4. HealthPotion\n");
    }
}
