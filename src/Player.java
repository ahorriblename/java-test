public class Player<T extends Role> extends Entity {
    T role;
    private Enemy targetEnemy;

    Player() {
        super();
    }

    Player(T role) {
        this.role = role;
    }

    Player(Enemy target) {
        super();
        this.targetEnemy = target;
    }

    Player(int health, int speed, int strength, int stamina, int armor, Enemy target) {
        super(health, speed, strength, stamina, armor);
        this.targetEnemy = target;
    }

    void setRole(T role) {
        this.role = role;
    }

    @Override
    void setTarget(Entity target) {
        this.targetEnemy = (Enemy) target;
    }

    Enemy getTarget() {
        return this.targetEnemy;
    }

    @Override
    void dealDamage() {
        setDamage(getStrength() - this.targetEnemy.getArmor());
        this.targetEnemy.setHealth(getStrength() - this.targetEnemy.getArmor());
    }
}
