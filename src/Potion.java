public class Potion {
    private String name;
    private int uses;
    private Entity target;

    Potion() {
        this.name = "";
        this.uses = 0;
        this.target = null;
    }

    Potion(String name, int uses, Entity target) {
        this.name = name;
        this.uses = uses;
        this.target = target;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getUses() {
        return uses;
    }

    void setUses(int uses) {
        this.uses = uses;
    }

    Entity getTarget() {
        return this.target;
    }

    void setTarget(Entity target) {
        this.target = target;
    }
}

class healthPotion extends Potion {
    healthPotion() {
        super("Health Potion", 1, null);
    }

    healthPotion(String name, int uses, Entity target) {
        super(name, uses, target);
    }

    void use() {
        Entity target = getTarget();
        if (getUses() > 0) {
            setUses(getUses() - 1);
            getTarget().setHealth(target.getHealth() + 5);
            System.out.println(target.getName() + " restored 5 health!");
        } else {
            System.out.println("No more uses...");
        }
    }
}
