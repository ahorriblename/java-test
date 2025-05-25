public class Warrior extends Player {
    public Warrior() {
        super();
        setHealth(10);
        setArmor(5);
        setSpeed(3);
        setName("Warrior");
        setStamina(5);
        setStrength(5);
    }

    public Warrior(int health, int speed, int strength, int stamina, int armor, Enemy target) {
        super(health, speed, strength, stamina, armor, target);
    }

    void crush() {
        setDamage(getStrength() - this.getTarget().getArmor() + 3);
        this.getTarget().setHealth(this.getTarget().getHealth() - getDamage());
        setDamageToZeroIfNegative();

        System.out.println(getName() + " crushed " + getTarget().getName() + "!");
        System.out.println(getName() + " dealt " + getDamage() + " damage to "
                + getTarget().getName() + "!");
    }

    void moveToUse() {
        switch (getMove()) {
            case "crush" -> crush();
            default -> System.out.print("No move");
        }
    }

    boolean validateMove() {
        return false;
    }
}
