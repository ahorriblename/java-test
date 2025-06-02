public class Entity {
    private int health;
    private int speed;
    private int strength;
    private int damage;
    private int stamina;
    private int maxStamina;
    private int armor;
    private String name;
    private String move;

    final private String ANSI_RESET = "\u001B[0m";
    final private String ANSI_RED = "\u001B[31m";
    final private String ANSI_GREEN = "\u001B[32m";
    final private String ANSI_YELLOW = "\u001B[33m";
    final private String ANSI_BLUE = "\u001B[34m";
    final private String ANSI_PURPLE = "\u001B[35m";

    /* will try 0-10 for stats
        0 = Abysmal
        1 = Atrocious
        2 = Weak
        3 = Bad
        4 = Below Average
        5 = Okay
        6 = Above Average
        7 = Great
        8 = Epic
        9 = Astounding
        10 = Legendary
     */

    Entity() {
        this.health = 100;
        this.speed = 5;
        this.strength = 5;
        this.stamina = 5;
        this.maxStamina = 5;
        this.armor = 5;
    }

    Entity(int health, int speed, int strength, int stamina, int armor, int maxStamina) {
        this.health = health;
        this.speed = speed;
        this.strength = strength;
        this.stamina = stamina;
        this.maxStamina = maxStamina;
        this.armor = armor;
    }

    int getHealth() {
        return this.health;
    }

    void setHealth(int health) {
        this.health = health;
    }

    int getSpeed() {
        return this.speed;
    }

    void setSpeed(int speed) {
        this.speed = speed;
    }

    int getStrength() {
        return this.strength;
    }

    void setStrength(int strength) {
        this.strength = strength;
    }

    int getStamina() {
        return this.stamina;
    }

    void setStamina(int stamina) {
        this.stamina = stamina;
    }

    int getMaxStamina() {
        return this.maxStamina;
    }

    void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    int getArmor() {
        return this.armor;
    }

    void setArmor(int armor) {
        this.armor = armor;
    }

    String getName() {
        return this.name;
    }

    void setName(String name) {
        this.name = name;
    }

    void setDamage(int damage) {
        this.damage = damage;
    }

    int getDamage() {
        return this.damage;
    }

    void setTarget(Entity target) {
    }

    void setDamageToZeroIfNegative() {
        if (this.damage < 0) {
            this.damage = 0;
        }
    }

    void storeMove(String move) {
        this.move = move;
    }

    String getMove() {
        return this.move;
    }

    // universal move
    void rest() {
        this.stamina += (this.maxStamina / 2);

        if (this.maxStamina < this.stamina) {
            this.stamina = this.maxStamina;
        }

        System.out.println(this.name + " rested...");
        System.out.println("Stamina is now at " + this.stamina);
    }

    @Override
    public String toString() {
        return this.name + " stats\n" + ANSI_RED + " Health: " + this.health + ANSI_RESET
                + ANSI_YELLOW +"\n Speed: " + this.speed + ANSI_RESET
                + ANSI_PURPLE +"\n Strength: " + this.strength + ANSI_RESET
                + ANSI_GREEN + "\n Stamina: " + this.stamina + ANSI_RESET
                + ANSI_BLUE + "\n Armor: " + this.armor + ANSI_RESET;
    }
}
