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

            if (!playerWarrior.getIsDead()) {
                Goblin bigGoblin = new Goblin();
                bigGoblin.setName("Big Goblin");
                bigGoblin.setStrength(4);
                bigGoblin.setHealth(12);

                Goblin newGoblin = new Goblin();

                boolean targetChosen = false;

                playWarriorRound2(playerWarrior, newGoblin, bigGoblin, targetChosen);
            }
        } else {
            playerBarb.setName(getPlayerName());
            playBarbarianRound1(playerBarb, defaultEnemy);

            RogueWizard enemyWizard = new RogueWizard();
            enemyWizard.setName("Hostile Wizard");

            if (!playerBarb.getIsDead()) {
                playBarbarianRound2(playerBarb, enemyWizard);
            }
        }
    }

    static void listRoles() {
        System.out.println("List of roles");
        System.out.println(" 1. Warrior");
        System.out.println(" 2. Barbarian");
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

        System.out.println("\nIt's turn " + playerWarrior.getTurn());

        while (!playerWarrior.isDead() && !defaultEnemy.isDead()) {
            System.out.println("\nType stats to print out player and enemy statistics");
            Warrior.listMoves();

            System.out.print("Pick your move: ");
            Scanner input = new Scanner(System.in);
            String move = input.nextLine();
            move = move.toLowerCase();

            if (playerWarrior.validateMove(move)) {
                playerWarrior.storeMove(move);
            } else {
                continue;
            }

            if (!runMoves(playerWarrior, defaultEnemy)) {
                break;
            }

            playerWarrior.incrementTurn();
            defaultEnemy.incrementTurn();
            System.out.println("\nIt's turn " + playerWarrior.getTurn());
        }
    }

    static void playWarriorRound2(Warrior playerWarrior, Goblin enemy1, Goblin enemy2,
                                  boolean targetChosen) {
        System.out.println("You see a goblin (Goblin)");
        System.out.println("You see a goblin (Big Goblin)");

        enemy1.setTarget(playerWarrior);
        enemy2.setTarget(playerWarrior);
        playerWarrior.setTarget(enemy1);

        while (!playerWarrior.isDead() && (!enemy1.isDead()) || !enemy2.isDead()) {
            Scanner input = new Scanner(System.in);

            System.out.println("\nType stats to print out player and enemy statistics");
            Warrior.listMoves();

            while(!targetChosen) {
                targetChosen = chooseTarget(playerWarrior, enemy1, enemy2);
            }

            System.out.print("Pick your move: ");
            String move = input.nextLine();
            move = move.toLowerCase();

            if (playerWarrior.validateMove(move)) {
                playerWarrior.storeMove(move);
            } else {
                continue;
            }

            if (enemy1.isDead()) {
                if (!runMoves(playerWarrior, enemy2)) {
                    break;
                }
            } else if (enemy2.isDead()) {
                if (!runMoves(playerWarrior, enemy1)) {
                    break;
                }
            } else {
                if (!runMoves(playerWarrior, enemy1, enemy2)) {
                    break;
                }
            }

            targetChosen = false;

            playerWarrior.incrementTurn();
            enemy1.incrementTurn();
            enemy2.incrementTurn();
            System.out.println("\nIt's turn " + playerWarrior.getTurn());
        }
    }

    static void playBarbarianRound1(Barbarian playerBarb, Goblin defaultEnemy) {
        System.out.println("You see a goblin");
        defaultEnemy.setTarget(playerBarb);
        playerBarb.setTarget(defaultEnemy);

        System.out.println("\nIt's turn " + playerBarb.getTurn());

        while (!playerBarb.isDead() && !defaultEnemy.isDead()) {
            System.out.println("\nType stats to print out player and enemy statistics");
            Barbarian.listMoves();

            System.out.print("Pick your move: ");
            Scanner input = new Scanner(System.in);
            String move = input.nextLine();
            move = move.toLowerCase();

            if (playerBarb.validateMove(move)) {
                playerBarb.storeMove(move);
            } else {
                continue;
            }

            if (!runMoves(playerBarb, defaultEnemy)) {
                break;
            }

            playerBarb.incrementTurn();
            defaultEnemy.incrementTurn();
            System.out.println("\nIt's turn " + playerBarb.getTurn());
        }
    }

    static void playBarbarianRound2(Barbarian playerBarb, RogueWizard enemy1) {
        System.out.println("You see a wizard (Hostile Wizard)");
        enemy1.setTarget(playerBarb);

        while (!playerBarb.isDead() && !enemy1.isDead()) {
            Scanner input = new Scanner(System.in);

            System.out.println("\nType stats to print out player and enemy statistics");
            Barbarian.listMoves();

            System.out.print("Pick your move: ");
            String move = input.nextLine();
            move = move.toLowerCase();

            if (playerBarb.validateMove(move)) {
                playerBarb.storeMove(move);
            } else {
                continue;
            }

            if (!runMoves(playerBarb, enemy1)) {
                break;
            }

            playerBarb.incrementTurn();
            enemy1.incrementTurn();
            System.out.println("\nIt's turn " + playerBarb.getTurn());
        }
    }

    static boolean runMoves(Warrior player, Goblin enemy) {
        enemy.getEnemyMove();

        if (player.getSpeed() >= enemy.getSpeed()) {
            player.moveToUse();
            if (player.isDead() || enemy.isDead()) {
                return false;
            }
            enemy.moveToUse();
        } else {
            enemy.moveToUse();
            if (player.isDead() || enemy.isDead()) {
                return false;
            }
            player.moveToUse();
        }

        return true;
    }

    static boolean runMoves(Barbarian player, Goblin enemy) {
        enemy.getEnemyMove();

        if (player.getSpeed() >= enemy.getSpeed()) {
            player.moveToUse();
            if (player.isDead() || enemy.isDead()) {
                return false;
            }
            enemy.moveToUse();
        } else {
            enemy.moveToUse();
            if (player.isDead() || enemy.isDead()) {
                return false;
            }
            player.moveToUse();
        }

        return true;
    }

    static boolean runMoves(Barbarian player, RogueWizard enemy) {
        enemy.getEnemyMove();

        if (player.getSpeed() >= enemy.getSpeed()) {
            player.moveToUse();
            if (player.isDead() || enemy.isDead()) {
                return false;
            }
            enemy.moveToUse();
        } else {
            enemy.moveToUse();
            if (player.isDead() || enemy.isDead()) {
                return false;
            }
            player.moveToUse();
        }

        return true;
    }

    static boolean runMoves(Warrior player, Goblin enemy1, Goblin enemy2) {
        enemy1.getEnemyMove();
        enemy2.getEnemyMove();

        if (player.getSpeed() >= enemy1.getSpeed() && player.getSpeed()
                >= enemy2.getSpeed()) {
            player.moveToUse();
            if (player.isDead() || (enemy1.isDead() && enemy2.isDead())) {
                return false;
            }

            enemy1.moveToUse();
            if (player.isDead() || (enemy1.isDead() && enemy2.isDead())) {
                return false;
            }

            enemy2.moveToUse();
        } else {
            enemy1.moveToUse();
            if (player.isDead() || (enemy1.isDead() && enemy2.isDead())) {
                return false;
            }

            enemy2.moveToUse();
            if (player.isDead() || (enemy1.isDead() && enemy2.isDead())) {
                return false;
            }

            player.moveToUse();
        }

        return true;
    }

    static boolean chooseTarget(Player player, Enemy enemy1, Enemy enemy2) {
        Scanner input = new Scanner(System.in);

        System.out.print("\nPick your target: ");
        String target = input.nextLine();
        target = target.toLowerCase();

        if (target.equals(enemy1.getName().toLowerCase()) && !enemy1.getIsDead()) {
            player.setTarget(enemy1);
            return true;
        } else if (target.equals(enemy2.getName().toLowerCase()) && !enemy2.getIsDead()) {
            player.setTarget(enemy2);
            return true;
        } else {
            System.out.println("Invalid target");
            // to announce what is dead
            enemy1.isDead();
            enemy2.isDead();

            return false;
        }
    }
}