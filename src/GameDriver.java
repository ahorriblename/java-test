import java.util.Scanner;

public class GameDriver {
    public static void main(String[] args) {
        Goblin defaultEnemy = new Goblin();

        Player playerTemp = getPlayerRole();
        String playerRole = playerTemp.getClass().getSimpleName();
        System.out.println("You chose " + playerRole);

        Warrior playerWarrior = null;
        Barbarian playerBarb = null;

        if (playerTemp instanceof Warrior) {
            playerWarrior = (Warrior) playerTemp;
        } else if (playerTemp instanceof Barbarian) {
            playerBarb = (Barbarian) playerTemp;
        } else {
            playerWarrior = (Warrior) playerTemp;
            System.out.println("Default role");
        }

        if (playerWarrior != null) {
            playerWarrior.setName(getPlayerName());
            playWarriorRound1(playerWarrior, defaultEnemy);
        } else {
            playerBarb.setName(getPlayerName());
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
            System.out.println("Type stats to print out player and enemy statistics");
            listWarriorMoves();

            System.out.print("Pick your move: ");
            Scanner input = new Scanner(System.in);
            String move = input.nextLine();
            move.toLowerCase();

            if (move.equals("crush")) {
                playerWarrior.storeMove(move);
            } else if (move.equals("armorup")) {
                playerWarrior.storeMove(move);
            } else if(move.equals("stats")) {
                System.out.println(defaultEnemy);
                System.out.println(playerWarrior);
                continue;
            } else {
                System.out.println("enter valid move");
                continue;
            }

            defaultEnemy.storeMove("slash");

            if (playerWarrior.getSpeed() >= defaultEnemy.getSpeed()) {
                playerWarrior.moveToUse();
                defaultEnemy.moveToUse();
            } else {
                defaultEnemy.moveToUse();
                playerWarrior.moveToUse();
            }

            if (playerWarrior.getHealth() <= 0) {
                System.out.println("You died!");
                break;
            } else if (defaultEnemy.getHealth() <= 0) {
                System.out.println("You killed " + defaultEnemy.getName());
                break;
            }
        }
    }

    static void playBarbarianRound1(Barbarian playerBarb, Goblin defaultEnemy) {
        System.out.println("You see a goblin");
        defaultEnemy.setTarget(playerBarb);
        playerBarb.setTarget(defaultEnemy);

        while (true) {
            System.out.println("Type stats to print out player and enemy statistics");
            listBarbarianMoves();

            System.out.print("Pick your move: ");
            Scanner input = new Scanner(System.in);
            String move = input.nextLine();
            move.toLowerCase();

            if (move.equals("clobber")) {
                playerBarb.storeMove(move);
            } else if (move.equals("enrage")) {
                playerBarb.storeMove(move);
            } else if(move.equals("stats")) {
                System.out.println(defaultEnemy);
                System.out.println(playerBarb);
                continue;
            } else {
                System.out.println("enter valid move");
                continue;
            }

            defaultEnemy.storeMove("slash");

            if (playerBarb.getSpeed() >= defaultEnemy.getSpeed()) {
                playerBarb.moveToUse();
                defaultEnemy.moveToUse();
            } else {
                defaultEnemy.moveToUse();
                playerBarb.moveToUse();
            }

            if (playerBarb.getHealth() <= 0) {
                System.out.println("You died!");
                break;
            } else if (defaultEnemy.getHealth() <= 0) {
                System.out.println("You killed " + defaultEnemy.getName());
                break;
            }
        }
    }

    static void listWarriorMoves() {
        System.out.println("List of moves");
        System.out.println(" 1. Crush");
        System.out.println(" 2. ArmorUp");
    }

    static void listBarbarianMoves() {
        System.out.println("List of moves");
        System.out.println(" 1. Clobber");
        System.out.println(" 2. Enrage");
    }
}