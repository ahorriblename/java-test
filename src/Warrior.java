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
        this.getTarget().setHealth(this.getTarget().getHealth() - getDamage());
        setDamageToZeroIfNegative();

        setStamina(getStamina() - 2);

        System.out.println(getName() + " crushed " + getTarget().getName() + "!");
        System.out.println(getName() + " dealt " + getDamage() + " damage to "
                + getTarget().getName() + "!");
    }

    void armorUp() {
        setArmor(getArmor() + 1);

        setStamina(getStamina() - 2);

        System.out.println(getName() + " armored up 1! (Now " + getArmor() + ")");
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

    boolean validateMove() {
        return false;
    }
}
