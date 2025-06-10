public class Enemy extends Entity {
    private Player targetPlayer;

    Enemy() {
        super();
    }

    Enemy(int health, int speed, int strength, int stamina, int maxStamina, int armor,
          String name, int magic, int maxMagic, int magicAbility, Player target) {
        super(health, speed, strength, stamina, maxStamina, armor, name, magic, maxMagic,
                magicAbility);
        this.targetPlayer = target;
    }

    @Override
    void setTarget(Entity target) {
        this.targetPlayer = (Player) target;
    }

    Player getTarget() {
        return this.targetPlayer;
    }
}
