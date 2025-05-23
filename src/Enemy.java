public class Enemy<T extends Role> extends Entity {
    T role;
    private Player targetPlayer;

    Enemy() {
        super();
    }

    Enemy(T role) {
        this.role = role;
    }

    Enemy(Player target, T role) {
        super();
        this.targetPlayer = target;
        this.role = role;
    }

    Enemy(int health, int speed, int strength, int stamina, int armor, Player target) {
        super(health, speed, strength, stamina, armor);
        this.targetPlayer = target;
    }

    void setRole(T role) {
        this.role = role;
    }

    @Override
    void setTarget(Entity target) {
        this.targetPlayer = (Player) target;
    }

    Player getTarget() {
        return this.targetPlayer;
    }

    @Override
    void dealDamage() {
        setDamage(getStrength() - this.targetPlayer.getArmor());
        this.targetPlayer.setHealth(getStrength() - this.targetPlayer.getArmor());
    }
}
