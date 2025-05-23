public class GameDriver {
    public static void main(String[] args) {
        Enemy<Goblin> defaultEnemy = new Enemy<>(new Goblin());
        Player<Warrior> player1 = new Player<>(defaultEnemy);
        defaultEnemy.setTarget(player1);
        defaultEnemy.setName("Default Enemy");
        player1.setName("Player 1");

        player1.setRole(new Warrior());
        player1.getRole().sayHello();
        defaultEnemy.getRole().sayHello();

        System.out.println(player1.getRole().toString());

        System.out.println(player1.getName() + " drank a strength potion! Strength set to" +
                " max! (10)");
        player1.setStrength(10);
        player1.getRole().setStrength(10);

        player1.dealDamage();
        System.out.println(player1.getName() + " dealt " + player1.getDamage() + " damage!");

        System.out.println(defaultEnemy.getName() + " used armor piercing spell! " +
                "Player armor reduced to 0!");
        player1.setArmor(0);
        player1.getRole().setArmor(0);

        defaultEnemy.dealDamage();
        System.out.println(defaultEnemy.getName() + " dealt " + defaultEnemy.getDamage()
                + " damage!");

        System.out.println(defaultEnemy);
        System.out.println(player1);
        System.out.println(player1.getRole().toString());
    }
}