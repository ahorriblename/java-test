public class Player extends Entity {
    private Enemy targetEnemy;

    Player() {
        super();
    }

    Player(Enemy target) {
        super();
        this.targetEnemy = target;
    }

    Player(int health, int speed, int strength, int stamina, int armor, int maxStamina,
           Enemy target) {
        super(health, speed, strength, stamina, armor, maxStamina);
        this.targetEnemy = target;
    }

    @Override
    void setTarget(Entity target) {
        this.targetEnemy = (Enemy) target;
    }

    Enemy getTarget() {
        return this.targetEnemy;
    }
}
