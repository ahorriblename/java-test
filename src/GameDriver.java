import java.util.Scanner;

public class GameDriver {
    public static void main(String[] args) {
        Goblin defaultEnemy = new Goblin();

        Player playerTemp = getPlayerRole();
        String playerRole = playerTemp.getClass().getSimpleName();
        System.out.println("You chose " + playerRole);

        // lines 12-21 band-aid fix
        Warrior playerWarrior = null;
        Barbarian playerBarb = null;

        if (playerTemp instanceof Warrior) {
            playerWarrior = (Warrior) playerTemp;
        } else if (playerTemp instanceof Barbarian) {
            playerBarb = (Barbarian) playerTemp;
        } else {
            playerWarrior = (Warrior) playerTemp;
        }

        if (playerWarrior != null) {
            playWarriorRound1(playerWarrior, defaultEnemy);
        } else {
            playBarbarianRound1(playerBarb, defaultEnemy);
        }

    }

    static String getPlayerName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        return input.nextLine();
    }

    static Player getPlayerRole() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your role: ");
        String role = input.nextLine();
        role.toLowerCase();

        return switch (role) {
            case "warrior" -> new Warrior();
            case "barbarian" -> new Barbarian();
            default -> new Warrior();
        };
    }

    static void playWarriorRound1(Warrior playerWarrior, Goblin defaultEnemy) {
        System.out.println("You see a goblin");
        defaultEnemy.setTarget(playerWarrior);
        playerWarrior.setTarget(defaultEnemy);

        while (true) {
            System.out.println("Pick your move");
            Scanner input = new Scanner(System.in);
            String move = input.nextLine();
            move.toLowerCase();

            if(move.equals("crush")) {
                playerWarrior.storeMove(move);
            } else {
                System.out.println("enter valid move");
                continue;
            }

            defaultEnemy.storeMove("slash");

            if(playerWarrior.getSpeed() >= defaultEnemy.getSpeed()){
                playerWarrior.moveToUse();
                defaultEnemy.moveToUse();
            } else {
                defaultEnemy.moveToUse();
                playerWarrior.moveToUse();
            }

            System.out.println(defaultEnemy);
            System.out.println(playerWarrior);

            if(playerWarrior.getHealth() <= 0) {
                System.out.println("You died!");
                break;
            } else if (defaultEnemy.getHealth() <= 0) {
                System.out.println("You killed "+ defaultEnemy.getName());
                break;
            }
        }
    }

    static void playBarbarianRound1(Barbarian playerBarb, Goblin defaultEnemy) {
        System.out.println("You see a goblin");
        defaultEnemy.setTarget(playerBarb);
        playerBarb.setTarget(defaultEnemy);

        while (true) {
            System.out.println("Pick your move");
            Scanner input = new Scanner(System.in);
            String move = input.nextLine();
            move.toLowerCase();

            if(move.equals("clobber")) {
                playerBarb.storeMove(move);
            } else {
                System.out.println("enter valid move");
                continue;
            }

            defaultEnemy.storeMove("slash");

            if(playerBarb.getSpeed() >= defaultEnemy.getSpeed()){
                playerBarb.moveToUse();
                defaultEnemy.moveToUse();
            } else {
                defaultEnemy.moveToUse();
                playerBarb.moveToUse();
            }

            System.out.println(defaultEnemy);
            System.out.println(playerBarb);

            if(playerBarb.getHealth() <= 0) {
                System.out.println("You died!");
                break;
            } else if (defaultEnemy.getHealth() <= 0) {
                System.out.println("You killed "+ defaultEnemy.getName());
                break;
            }
        }
    }
}