public class GameDriver {
    public static void main(String[] args) {
        Goblin defaultEnemy = new Goblin();
        Warrior player1 = new Warrior();

        defaultEnemy.setTarget(player1);
        player1.setTarget(defaultEnemy);

        player1.setName("Player 1");

        player1.crush();

        defaultEnemy.slash();

        System.out.println(defaultEnemy);
        System.out.println(player1);
    }
}