public class Player extends Entity {
    private Enemy targetEnemy;

    Player() {
        super();
    }

    Player(int health, int speed, int strength, int stamina, int maxStamina, int armor,
           String name, int magic, int maxMagic, int magicAbility, Enemy target) {
        super(health, speed, strength, stamina, maxStamina, armor, name, magic, maxMagic,
                magicAbility);
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
