public class Warrior extends Player {
    private HealthPotion healthPotion;

    public Warrior() {
        super();
        setHealth(10);
        setArmor(5);
        setSpeed(3);
        setName("Warrior");
        setStamina(5);
        setMaxStamina(5);
        setStrength(5);
        this.healthPotion = new HealthPotion("Health Potion", 1, this);
    }

    public Warrior(int health, int speed, int strength, int stamina, int armor, int maxStamina,
                   Enemy target) {
        super(health, speed, strength, stamina, armor, maxStamina, target);
        this.healthPotion = new HealthPotion("Health Potion", 1, this);
    }

    int getHealthPotionUses() {
        return this.healthPotion.getUses();
    }

    void crush() {
        setDamage(getStrength() - this.getTarget().getArmor() + 3);
        setDamageToZeroIfNegative();

        this.getTarget().setHealth(this.getTarget().getHealth() - getDamage());

        setStamina(getStamina() - 2);

        System.out.println("\n" + getName() + " crushed " + getTarget().getName() + "!"
                + Color.ANSI_GREEN + " (2 Stamina)" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_RED + getName() + " dealt " + getDamage()
                + " damage to " + getTarget().getName() + "!" + Color.ANSI_RESET);
    }

    void armorUp() {
        setArmor(getArmor() + 1);

        setStamina(getStamina() - 2);

        System.out.println("\n" + Color.ANSI_BLUE + getName() + " armored up 1! (Now "
                + getArmor() + ")" + Color.ANSI_RESET + Color.ANSI_GREEN + " (2 Stamina)"
                + Color.ANSI_RESET);
    }

    void moveToUse() {
        switch (getMove()) {
            case "crush" -> crush();
            case "armorup" -> armorUp();
            case "rest" -> rest();
            case "healthpotion" -> this.healthPotion.use();
            default -> System.out.println("No move");
        }
    }

    boolean validateMove(String move) {
        // false continues loop

        if (move.equals("crush") && this.getStamina() >= 2) {
            return true;
        } else if (move.equals("armorup") && this.getStamina() >= 2) {
            return true;
        } else if (move.equals("stats")) {
            System.out.println(this.getTarget());
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
        System.out.println(" 1. Crush (2 Stamina)");
        System.out.println(" 2. ArmorUp (2 Stamina)");
        System.out.println(" 3. Rest");
        System.out.println(" 4. HealthPotion\n");
    }
}
