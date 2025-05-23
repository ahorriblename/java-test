public class Role extends Entity {
    Role() {
        super();
    }

    Role(int health, int speed, int strength, int stamina, int armor) {
        super(health, speed, strength, stamina, armor);
    }
}

class Warrior extends Role {
    Warrior() {
        super();
        this.setName("Warrior");
    }

    Warrior(int health, int speed, int strength, int stamina, int armor) {
        super(health, speed, strength, stamina, armor);
        this.setName("Warrior");
    }

    void sayHello() {
        System.out.println("Hello, i am a Warrior");
    }
}

class Goblin extends Role {
    Goblin() {
        super();
        this.setName("Goblin");
    }

    Goblin(int health, int speed, int strength, int stamina, int armor) {
        super(health, speed, strength, stamina, armor);
        this.setName("Goblin");
    }

    void sayHello() {
        System.out.println("Hello, i am a Goblin");
    }
}
