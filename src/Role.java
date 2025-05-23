public class Role extends Entity{
}

class Warrior extends Role{
    Warrior() {
        super();
    }

    void sayHello(){
        System.out.println("Hello, i am a Warrior");
    }
}

class Goblin extends Role{
    Goblin() {
        super();
    }

    void sayHello(){
        System.out.println("Hello, i am a Goblin");
    }
}
