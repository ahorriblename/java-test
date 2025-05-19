public class GameDriver {
    public static void main(String[] args) {
        Enemy defaultEnemy = new Enemy();
        Player player1 = new Player(defaultEnemy);

        System.out.println("You drank a strength potion! Strength set to max! (10)");
        player1.setStrength(10);

        player1.dealDamage();
        System.out.println("You dealt " + player1.getDamage() + " damage!");
    }
}