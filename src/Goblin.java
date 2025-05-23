public class Goblin extends Enemy {
    public Goblin() {
        super();
    }

    public Goblin(int health, int speed, int strength, int stamina, int armor, Player target){
        super(health, speed, strength, stamina, armor, target);
    }

    void slash() {
        setDamage(getStrength() - this.getTarget().getArmor());
        this.getTarget().setHealth(getHealth() - getDamage());
    }
}
