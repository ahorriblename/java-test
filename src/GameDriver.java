import java.util.Scanner;

public class GameDriver {
    public static void main(String[] args) {
        Goblin defaultEnemy = new Goblin();
        Warrior player1 = new Warrior();

        defaultEnemy.setTarget(player1);
        player1.setTarget(defaultEnemy);

        player1.setName(getPlayerName());
        System.out.println("Hello " + player1.getName() + "!");

        /*
        player1.crush();

        defaultEnemy.slash();

        System.out.println(defaultEnemy);
        System.out.println(player1);
         */
    }

    static String getPlayerName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        return input.nextLine();
    }
}