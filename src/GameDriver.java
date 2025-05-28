import java.util.Scanner;

public class GameDriver {
    public static void main(String[] args) {
        Goblin defaultEnemy = new Goblin();

        listRoles();
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
        role = role.toLowerCase();

        return switch (role) {
            case "warrior" -> new Warrior();
            case "barbarian" -> new Barbarian();
            default -> getPlayerRole();
        };
    }

    static void playWarriorRound1(Warrior playerWarrior, Goblin defaultEnemy) {
        System.out.println("You see a goblin");
        defaultEnemy.setTarget(playerWarrior);
        playerWarrior.setTarget(defaultEnemy);
        int checkHealth = 0;

        while (checkHealth == 0) {
            System.out.println("Type stats to print out player and enemy statistics");
            listWarriorMoves();

            System.out.print("Pick your move: ");
            Scanner input = new Scanner(System.in);
            String move = input.nextLine();
            move.toLowerCase();

            if(validatePlayerMove(move, playerWarrior, defaultEnemy)) {
                playerWarrior.storeMove(move);
            } else {
                continue;
            }

            defaultEnemy.getEnemyMove();

            if (playerWarrior.getSpeed() >= defaultEnemy.getSpeed()) {
                playerWarrior.moveToUse();
                checkHealth = checkHealth(playerWarrior, defaultEnemy);
                if (checkHealth == 1) {
                    break;
                }
                defaultEnemy.moveToUse();
                checkHealth = checkHealth(playerWarrior, defaultEnemy);
            } else {
                defaultEnemy.moveToUse();
                checkHealth = checkHealth(playerWarrior, defaultEnemy);
                if (checkHealth == 1) {
                    break;
                }
                playerWarrior.moveToUse();
                checkHealth = checkHealth(playerWarrior, defaultEnemy);
            }
        }
    }

    static void playBarbarianRound1(Barbarian playerBarb, Goblin defaultEnemy) {
        System.out.println("You see a goblin");
        defaultEnemy.setTarget(playerBarb);
        playerBarb.setTarget(defaultEnemy);
        int checkHealth = 0;

        while (checkHealth == 0) {
            System.out.println("Type stats to print out player and enemy statistics");
            listBarbarianMoves();

            System.out.print("Pick your move: ");
            Scanner input = new Scanner(System.in);
            String move = input.nextLine();
            move.toLowerCase();

            if(validatePlayerMove(move, playerBarb, defaultEnemy)) {
                playerBarb.storeMove(move);
            } else {
                continue;
            }

            defaultEnemy.getEnemyMove();

            if (playerBarb.getSpeed() >= defaultEnemy.getSpeed()) {
                playerBarb.moveToUse();
                checkHealth = checkHealth(playerBarb, defaultEnemy);
                if (checkHealth == 1) {
                    break;
                }
                defaultEnemy.moveToUse();
                checkHealth = checkHealth(playerBarb, defaultEnemy);
            } else {
                defaultEnemy.moveToUse();
                checkHealth = checkHealth(playerBarb, defaultEnemy);
                if (checkHealth == 1) {
                    break;
                }
                playerBarb.moveToUse();
                checkHealth = checkHealth(playerBarb, defaultEnemy);
            }
        }
    }

    static void listWarriorMoves() {
        System.out.println("List of moves");
        System.out.println(" 1. Crush (2 Stamina)");
        System.out.println(" 2. ArmorUp (2 Stamina)");
        System.out.println(" 3. Rest");
    }

    static void listBarbarianMoves() {
        System.out.println("List of moves");
        System.out.println(" 1. Clobber (2 Stamina)");
        System.out.println(" 2. Enrage (1 Stamina)");
        System.out.println(" 3. Rest");
    }

    static void listRoles() {
        System.out.println("List of roles");
        System.out.println(" 1. Warrior");
        System.out.println(" 2. Barbarian");
    }

    static int checkHealth(Entity entity1, Entity entity2) {
        if (entity1.getHealth() <= 0) {
            System.out.println("You died!");
            return 1;
        } else if (entity2.getHealth() <= 0) {
            System.out.println("You killed " + entity2.getName());
            return 1;
        }

        return 0;
    }

    static boolean validatePlayerMove(String move, Warrior player, Enemy enemy) {
        if (move.equals("crush") && player.getStamina() >= 2) {
            return true;
        } else if (move.equals("armorup") && player.getStamina() >= 2) {
            return true;
        } else if(move.equals("stats")) {
            System.out.println(enemy);
            System.out.println(player);
            return false;
        } else if(move.equals("rest")) {
            return true;
        } else {
            System.out.println("enter valid move");
            return false;
        }
    }

    static boolean validatePlayerMove(String move, Barbarian player, Enemy enemy) {
        if (move.equals("enrage") && player.getStamina() >= 1) {
            return true;
        } else if (move.equals("clobber") && player.getStamina() >= 2) {
            return true;
        } else if(move.equals("stats")) {
            System.out.println(enemy);
            System.out.println(player);
            return false;
        } else if(move.equals("rest")) {
            return true;
        } else {
            System.out.println("enter valid move");
            return false;
        }
    }
}