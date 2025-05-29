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

        Goblin bigGoblin = new Goblin();
        bigGoblin.setName("Big Goblin");
        bigGoblin.setStrength(4);
        bigGoblin.setHealth(12);

        Goblin newGoblin = new Goblin();

        boolean targetChosen = false;

        playWarriorRound2(playerWarrior, newGoblin, bigGoblin, targetChosen);

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

            if (validatePlayerMove(move, playerWarrior, defaultEnemy)) {
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

    static void playWarriorRound2(Warrior playerWarrior, Goblin enemy1, Goblin enemy2, boolean targetChosen) {
        System.out.println("You see a goblin (goblin 1)");
        System.out.println("You see a goblin (goblin 2)");

        enemy1.setTarget(playerWarrior);
        enemy2.setTarget(playerWarrior);
        playerWarrior.setTarget(enemy1);
        int checkHealth = 0;
        Goblin chosenTarget = null;

        while (checkHealth == 0) {
            Scanner input = new Scanner(System.in);

            System.out.println("Type stats to print out player and enemy statistics");
            listWarriorMoves();

            System.out.println();
            if (targetChosen) {
            } else {
                chosenTarget = null;
            }

            while (targetChosen == false) {
                System.out.print("\n Pick your target: ");
                String target = input.nextLine();
                target.toLowerCase();

                if (target.equals("goblin 1")) {
                    playerWarrior.setTarget(enemy1);
                    chosenTarget = enemy1;
                    targetChosen = true;
                } else if (target.equals("goblin 2")) {
                    playerWarrior.setTarget(enemy2);
                    chosenTarget = enemy2;
                    targetChosen = true;
                } else {
                    System.out.println("Invalid target");
                    targetChosen = false;
                }
            }

            System.out.print("Pick your move: ");
            String move = input.nextLine();
            move.toLowerCase();

            if (validatePlayerMove(move, playerWarrior, enemy1, enemy2)) {
                playerWarrior.storeMove(move);
            } else {
                continue;
            }

            enemy1.getEnemyMove();
            enemy2.getEnemyMove();

            if (playerWarrior.getSpeed() >= enemy1.getSpeed() && playerWarrior.getSpeed()
                    >= enemy2.getSpeed()) {
                playerWarrior.moveToUse();
                checkHealth = checkHealth(playerWarrior, chosenTarget);
                if (checkHealth == 1) {
                    break;
                }
                enemy1.moveToUse();
                checkHealth = checkHealth(playerWarrior, enemy1);
                if (checkHealth == 1) {
                    break;
                }
                enemy2.moveToUse();
                checkHealth = checkHealth(playerWarrior, enemy2);
            } else {
                enemy1.moveToUse();
                checkHealth = checkHealth(playerWarrior, enemy1);
                if (checkHealth == 1) {
                    break;
                }
                enemy2.moveToUse();
                checkHealth = checkHealth(playerWarrior, enemy2);
                if (checkHealth == 1) {
                    break;
                }
                playerWarrior.moveToUse();
                checkHealth = checkHealth(playerWarrior, chosenTarget);
            }

            targetChosen = false;
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

            if (validatePlayerMove(move, playerBarb, defaultEnemy)) {
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
        System.out.println(" 4. HealthPotion");
    }

    static void listBarbarianMoves() {
        System.out.println("List of moves");
        System.out.println(" 1. Clobber (2 Stamina)");
        System.out.println(" 2. Enrage (1 Stamina)");
        System.out.println(" 3. Rest");
        System.out.println(" 4. HealthPotion");
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
        } else if (move.equals("stats")) {
            System.out.println(enemy);
            System.out.println(player);
            return false;
        } else if (move.equals("rest")) {
            return true;
        } else if (move.equals("healthpotion")) {
            return true;
        } else {
            System.out.println("enter valid move");
            return false;
        }
    }

    static boolean validatePlayerMove(String move, Warrior player, Enemy enemy1, Enemy enemy2) {
        if (move.equals("crush") && player.getStamina() >= 2) {
            return true;
        } else if (move.equals("armorup") && player.getStamina() >= 2) {
            return true;
        } else if (move.equals("stats")) {
            System.out.println(enemy1);
            System.out.println(enemy2);
            System.out.println(player);
            return false;
        } else if (move.equals("rest")) {
            return true;
        } else if (move.equals("healthpotion")) {
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
        } else if (move.equals("stats")) {
            System.out.println(enemy);
            System.out.println(player);
            return false;
        } else if (move.equals("rest")) {
            return true;
        } else if (move.equals("healthpotion")) {
            return true;
        } else {
            System.out.println("enter valid move");
            return false;
        }
    }

    static boolean validatePlayerMove(String move, Barbarian player, Enemy enemy1, Enemy enemy2) {
        if (move.equals("enrage") && player.getStamina() >= 1) {
            return true;
        } else if (move.equals("clobber") && player.getStamina() >= 2) {
            return true;
        } else if (move.equals("stats")) {
            System.out.println(enemy1);
            System.out.println(enemy2);
            System.out.println(player);
            return false;
        } else if (move.equals("rest")) {
            return true;
        } else if (move.equals("healthpotion")) {
            return true;
        } else {
            System.out.println("enter valid move");
            return false;
        }
    }
}