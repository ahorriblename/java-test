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

            defaultEnemy.getEnemyMove();

            if (playerWarrior.getSpeed() >= defaultEnemy.getSpeed()) {
                playerWarrior.moveToUse();
                if (playerWarrior.isDead() || defaultEnemy.isDead()) {
                    break;
                }
                defaultEnemy.moveToUse();
            } else {
                defaultEnemy.moveToUse();
                if (playerWarrior.isDead() || defaultEnemy.isDead()) {
                    break;
                }
                playerWarrior.moveToUse();
            }
        }
    }

    static void playWarriorRound2(Warrior playerWarrior, Goblin enemy1, Goblin enemy2,
                                  boolean targetChosen) {
        System.out.println("You see a goblin (goblin 1)");
        System.out.println("You see a goblin (goblin 2)");

        enemy1.setTarget(playerWarrior);
        enemy2.setTarget(playerWarrior);
        playerWarrior.setTarget(enemy1);

        while (!playerWarrior.isDead() && (!enemy1.isDead()) || !enemy2.isDead()) {
            Scanner input = new Scanner(System.in);

            System.out.println("\nType stats to print out player and enemy statistics");
            Warrior.listMoves();

            while (!targetChosen) {
                System.out.print("\nPick your target: ");
                String target = input.nextLine();
                target = target.toLowerCase();

                if (target.equals("goblin 1") && !enemy1.getIsDead()) {
                    playerWarrior.setTarget(enemy1);
                    targetChosen = true;
                } else if (target.equals("goblin 2") && !enemy2.getIsDead()) {
                    playerWarrior.setTarget(enemy2);
                    targetChosen = true;
                } else {
                    System.out.println("Invalid target");
                    // to announce what is dead
                    enemy1.isDead();
                    enemy2.isDead();

                    targetChosen = false;
                }
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
                enemy2.getEnemyMove();

                if (playerWarrior.getSpeed() >= enemy2.getSpeed()) {
                    playerWarrior.moveToUse();
                    if (playerWarrior.isDead() || enemy2.isDead()) {
                        break;
                    }

                    enemy2.moveToUse();
                } else {
                    enemy2.moveToUse();
                    if (playerWarrior.isDead() || enemy2.isDead()) {
                        break;
                    }

                    playerWarrior.moveToUse();
                }
            } else if (enemy2.isDead()) {
                enemy1.getEnemyMove();

                if (playerWarrior.getSpeed() >= enemy1.getSpeed()) {
                    playerWarrior.moveToUse();
                    if (playerWarrior.isDead() || enemy1.isDead()) {
                        break;
                    }

                    enemy1.moveToUse();
                } else {
                    enemy1.moveToUse();
                    if (playerWarrior.isDead() || enemy1.isDead()) {
                        break;
                    }

                    playerWarrior.moveToUse();
                }
            } else {
                enemy1.getEnemyMove();
                enemy2.getEnemyMove();

                if (playerWarrior.getSpeed() >= enemy1.getSpeed() && playerWarrior.getSpeed()
                        >= enemy2.getSpeed()) {
                    playerWarrior.moveToUse();
                    if (playerWarrior.isDead() || (enemy1.isDead() && enemy2.isDead())) {
                        break;
                    }

                    enemy1.moveToUse();
                    if (playerWarrior.isDead() || (enemy1.isDead() && enemy2.isDead())) {
                        break;
                    }

                    enemy2.moveToUse();
                } else {
                    enemy1.moveToUse();
                    if (playerWarrior.isDead() || (enemy1.isDead() && enemy2.isDead())) {
                        break;
                    }

                    enemy2.moveToUse();
                    if (playerWarrior.isDead() || (enemy1.isDead() && enemy2.isDead())) {
                        break;
                    }

                    playerWarrior.moveToUse();
                }
            }

            targetChosen = false;
        }
    }

    static void playBarbarianRound1(Barbarian playerBarb, Goblin defaultEnemy) {
        System.out.println("You see a goblin");
        defaultEnemy.setTarget(playerBarb);
        playerBarb.setTarget(defaultEnemy);

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

            defaultEnemy.getEnemyMove();

            if (playerBarb.getSpeed() >= defaultEnemy.getSpeed()) {
                playerBarb.moveToUse();
                if (playerBarb.isDead() || defaultEnemy.isDead()) {
                    break;
                }

                defaultEnemy.moveToUse();
            } else {
                defaultEnemy.moveToUse();
                if (playerBarb.isDead() || defaultEnemy.isDead()) {
                    break;
                }

                playerBarb.moveToUse();
            }
        }
    }

    static void playBarbarianRound2(Barbarian playerBarb, RogueWizard enemy) {
        System.out.println("You see a wizard");

        enemy.setTarget(playerBarb);
        playerBarb.setTarget(enemy);

        while (!playerBarb.isDead() && !enemy.isDead()) {
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

            enemy.getEnemyMove();

            if (playerBarb.getSpeed() >= enemy.getSpeed()) {
                playerBarb.moveToUse();
                if (playerBarb.isDead() || enemy.isDead()) {
                    break;
                }

                enemy.moveToUse();
            } else {
                enemy.moveToUse();
                if (playerBarb.isDead() || enemy.isDead()) {
                    break;
                }

                playerBarb.moveToUse();
            }
        }
    }
}