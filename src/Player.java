public class Player extends Entity{
    private Enemy targetEnemy;

    Player() {
        super();
    }

    Player(Enemy target) {
        super();
        this.targetEnemy = target;
    }

    Player(int health, int speed, int strength, int stamina, int armor, Enemy target) {
        super(health, speed, strength, stamina, armor);
        this.targetEnemy = target;
    }

    @Override
    void dealDamage() {
        setDamage(getStrength() - this.targetEnemy.getArmor());
        this.targetEnemy.setHealth(getStrength() - this.targetEnemy.getArmor());
    }
}
