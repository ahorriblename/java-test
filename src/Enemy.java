public class Enemy extends Entity{
    private Player targetPlayer;

    Enemy() {
        super();
    }

    Enemy(Player target) {
        super();
        this.targetPlayer = target;
    }

    Enemy(int health, int speed, int strength, int stamina, int armor, Player target) {
        super(health, speed, strength, stamina, armor);
        this.targetPlayer = target;
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
