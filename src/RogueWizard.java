public class RogueWizard extends Enemy {
    private MagicPotion magicPotion;

    RogueWizard() {
        super();
        setHealth(10);
        setSpeed(5);
        setStrength(1);
        setStamina(1);
        setMaxStamina(1);
        setArmor(0);
        setName("Wizard");

        setMagic(5);
        setMaxMagic(5);
        setMagicAbility(5);

        this.magicPotion = new MagicPotion("Magic Potion", 1, this);
    }

    RogueWizard(int health, int speed, int strength, int stamina, int maxStamina, int armor,
                String name, int magic, int maxMagic, int magicAbility ,Player target) {
        super(health, speed, strength, stamina, maxStamina, armor, name, magic, maxMagic,
                magicAbility, target);

        this.magicPotion = new MagicPotion("Magic Potion", 1, this);
    }

    void magicMissile() {
        Player target = getTarget();
        setDamage(getMagicAbility());
        setDamageToZeroIfNegative();

        target.setHealth(target.getHealth() - getDamage());

        setMagic(getMagic() - 5);

        System.out.println("\n" + getName() + " fired a magic missile at " + target.getName()
                + "!" + Color.ANSI_CYAN + " (5 magic)" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_RED + getName() + " dealt " + getDamage() + " damage to "
                + target.getName() + "!" + Color.ANSI_RESET);
    }

    void fireBall() {
        Player target = getTarget();
        setDamage(getMagicAbility() / 2);
        setDamageToZeroIfNegative();

        target.setHealth(target.getHealth() - getDamage());

        setMagic(getMagic() - 2);

        System.out.println("\n" + getName() + " threw a fireball at " + target.getName()
                + "!" + Color.ANSI_CYAN + " (2 magic)" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_RED + getName() + " dealt " + getDamage() + "to "
                + target.getName() + "!" + Color.ANSI_RESET);
    }

    void slash() {
        setDamage(getStrength() - this.getTarget().getArmor());
        setDamageToZeroIfNegative();

        this.getTarget().setHealth(this.getTarget().getHealth() - getDamage());

        setStamina(getStamina() - 1);

        System.out.println("\n" + getName() + " slashed " + getTarget().getName() + "!"
                + Color.ANSI_GREEN + " (1 Stamina)" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_RED + getName() + " dealt " + getDamage()
                + " damage to " + getTarget().getName() + "!" + Color.ANSI_RESET);
    }

    void moveToUse() {
        switch (getMove()) {
            case "magicmissile" -> magicMissile();
            case "fireball" -> fireBall();
            case "slash" -> slash();
            case "rest" -> rest();
            case "magicpotion" -> this.magicPotion.use();
            default -> System.out.println("No move!");
        }
    }

    void getEnemyMove() {
        Player target = getTarget();

        if ((target.getArmor() >= 0 || target.getHealth() >= getMagicAbility())
                && getMagic() >= 5) {
            storeMove("magicmissile");
        } else if (getMagic() >= 2) {
            storeMove("fireball");
        } else if (getMagic() < 2 && this.magicPotion.getUses() >= 1) {
            storeMove("magicpotion");
        } else if (getStamina() >= 1) {
            storeMove("slash");
        } else {
            storeMove("rest");
        }
    }
}
