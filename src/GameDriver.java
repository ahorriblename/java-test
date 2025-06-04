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

            Goblin bigGoblin = new Goblin();
            bigGoblin.setName("Big Goblin");
            bigGoblin.setStrength(4);
            bigGoblin.setHealth(12);

            Goblin newGoblin = new Goblin();

            boolean targetChosen = false;

            playWarriorRound2(playerWarrior, newGoblin, bigGoblin, targetChosen);
        } else {
            playerBarb.setName(getPlayerName());
            playBarbarianRound1(playerBarb, defaultEnemy);

            RogueWizard enemyWizard = new RogueWizard();
            enemyWizard.setName("Hostile Wizard");

            playBarbarianRound2(playerBarb, enemyWizard);
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
                checkHealth = checkHealth(playerWarrior, defaultEnemy);
                if (checkHealth >= 1) {
                    break;
                }
                defaultEnemy.moveToUse();
                checkHealth = checkHealth(playerWarrior, defaultEnemy);
            } else {
                defaultEnemy.moveToUse();
                checkHealth = checkHealth(playerWarrior, defaultEnemy);
                if (checkHealth >= 1) {
                    break;
                }
                playerWarrior.moveToUse();
                checkHealth = checkHealth(playerWarrior, defaultEnemy);
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
        int checkHealth = 0;
        Goblin chosenTarget = null;

        while (checkHealth < 2) {
            Scanner input = new Scanner(System.in);

            System.out.println("\nType stats to print out player and enemy statistics");
            Warrior.listMoves();

            System.out.println();
            if (targetChosen) {
            } else {
                chosenTarget = null;
            }

            while (targetChosen == false) {
                System.out.print("Pick your target: ");
                String target = input.nextLine();
                target = target.toLowerCase();

                if (target.equals("goblin 1") && enemy1.getHealth() > 0) {
                    playerWarrior.setTarget(enemy1);
                    chosenTarget = enemy1;
                    targetChosen = true;
                } else if (target.equals("goblin 2") && enemy2.getHealth() > 0) {
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
            move = move.toLowerCase();

            if (playerWarrior.validateMove(move)) {
                playerWarrior.storeMove(move);
            } else {
                continue;
            }

            if (enemy1.getHealth() <= 0) {
                enemy2.getEnemyMove();

                if (playerWarrior.getSpeed() >= enemy2.getSpeed()) {
                    playerWarrior.moveToUse();
                    checkHealth += checkHealth(playerWarrior, chosenTarget);
                    if (checkHealth == 2 || checkHealth >= 99) {
                        break;
                    }
                    enemy2.moveToUse();
                    checkHealth += checkHealth(playerWarrior, enemy2);
                    if (checkHealth == 2 || checkHealth >= 99) {
                        break;
                    }
                } else {
                    enemy2.moveToUse();
                    checkHealth += checkHealth(playerWarrior, enemy2);
                    if (checkHealth == 2 || checkHealth >= 99) {
                        break;
                    }
                    playerWarrior.moveToUse();
                    checkHealth += checkHealth(playerWarrior, chosenTarget);
                }
            } else if (enemy2.getHealth() <= 0) {
                enemy1.getEnemyMove();

                if (playerWarrior.getSpeed() >= enemy1.getSpeed()) {
                    playerWarrior.moveToUse();
                    checkHealth += checkHealth(playerWarrior, chosenTarget);
                    if (checkHealth == 2 || checkHealth >= 99) {
                        break;
                    }
                    enemy1.moveToUse();
                    checkHealth += checkHealth(playerWarrior, enemy1);
                    if (checkHealth == 2 || checkHealth >= 99) {
                        break;
                    }
                } else {
                    enemy1.moveToUse();
                    checkHealth += checkHealth(playerWarrior, enemy1);
                    if (checkHealth == 2 || checkHealth >= 99) {
                        break;
                    }
                    playerWarrior.moveToUse();
                    checkHealth += checkHealth(playerWarrior, chosenTarget);
                }
            } else {
                enemy1.getEnemyMove();
                enemy2.getEnemyMove();

                if (playerWarrior.getSpeed() >= enemy1.getSpeed() && playerWarrior.getSpeed()
                        >= enemy2.getSpeed()) {
                    playerWarrior.moveToUse();
                    checkHealth += checkHealth(playerWarrior, chosenTarget);
                    if (checkHealth == 2 || checkHealth >= 99) {
                        break;
                    }
                    enemy1.moveToUse();
                    checkHealth += checkHealth(playerWarrior, enemy1);
                    if (checkHealth == 2 || checkHealth >= 99) {
                        break;
                    }
                    enemy2.moveToUse();
                    checkHealth += checkHealth(playerWarrior, enemy2);
                } else {
                    enemy1.moveToUse();
                    checkHealth += checkHealth(playerWarrior, enemy1);
                    if (checkHealth == 2 || checkHealth >= 99) {
                        break;
                    }
                    enemy2.moveToUse();
                    checkHealth += checkHealth(playerWarrior, enemy2);
                    if (checkHealth == 2 || checkHealth >= 99) {
                        break;
                    }
                    playerWarrior.moveToUse();
                    checkHealth += checkHealth(playerWarrior, chosenTarget);
                }
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
                checkHealth = checkHealth(playerBarb, defaultEnemy);
                if (checkHealth >= 1) {
                    break;
                }
                defaultEnemy.moveToUse();
                checkHealth = checkHealth(playerBarb, defaultEnemy);
            } else {
                defaultEnemy.moveToUse();
                checkHealth = checkHealth(playerBarb, defaultEnemy);
                if (checkHealth >= 1) {
                    break;
                }
                playerBarb.moveToUse();
                checkHealth = checkHealth(playerBarb, defaultEnemy);
            }
        }
    }

    static void playBarbarianRound2(Barbarian playerBarb, RogueWizard enemy) {
        System.out.println("You see a wizard");

        enemy.setTarget(playerBarb);
        playerBarb.setTarget(enemy);
        int checkHealth = 0;

        while (checkHealth == 0) {
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
                checkHealth = checkHealth(playerBarb, enemy);
                if (checkHealth >= 1) {
                    break;
                }
                enemy.moveToUse();
                checkHealth = checkHealth(playerBarb, enemy);
            } else {
                enemy.moveToUse();
                checkHealth = checkHealth(playerBarb, enemy);
                if (checkHealth >= 1) {
                    break;
                }
                playerBarb.moveToUse();
                checkHealth = checkHealth(playerBarb, enemy);
            }
        }
    }

    static void listRoles() {
        System.out.println("List of roles");
        System.out.println(" 1. Warrior");
        System.out.println(" 2. Barbarian");
    }

    static int checkHealth(Entity entity1, Entity entity2) {
        if (entity1.getHealth() <= 0) {
            System.out.println("You died!");
            return 99;
        } else if (entity2.getHealth() <= 0) {
            System.out.println("You killed " + entity2.getName());
            return 1;
        }
        return 0;
    }
}