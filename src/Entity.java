public class Entity {
    private int health;
    private int speed;
    private int strength;
    private int damage;
    private int stamina;
    private int armor;

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

    Entity(){
        this.health = 100;
        this.speed = 5;
        this.strength = 5;
        this.damage = 5;
        this.stamina = 5;
        this.armor = 5;
    }

    Entity(int health, int speed, int strength, int damage, int stamina, int armor){
        this.health = health;
        this.speed = speed;
        this.strength = strength;
        this.damage = damage;
        this.stamina = stamina;
        this.armor = armor;
    }

    int getHealth(){
        return this.health;
    }

    void setHealth(int health){
        this.health = health;
    }

    int getSpeed(){
        return this.speed;
    }

    void setSpeed(int speed){
        this.speed = speed;
    }

    int getStrength(){
        return this.strength;
    }

    void setStrength(int strength){
        this.strength = strength;
    }

    int getDamage(){
        return this.damage;
    }

    int getStamina(){
        return this.stamina;
    }

    void setStamina(int stamina) {
        this.stamina = stamina;
    }

    int getArmor(){
        return this.armor;
    }

    void setArmor(int armor) {
        this.armor = armor;
    }
}
