package battleship;

import java.util.*;

public class App {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println
        (
            "You need to bomb 5 ships to win the game.\n\n" +
            "Choose a stage by entering a number:\n" +
            "+------------+-------+-----------------+--------+\n" +
            "|   Stage    | Ships | Traps | Potions | Number |\n" +
            "+------------+-------+-------+---------+--------+\n" +
            "|Beginner    |   40  |  30   |    20   |    1   |\n" +
            "+------------+-------+-------+---------+--------+\n" +
            "|Intermdiate |   30  |  60   |    40   |    2   |\n" +
            "+------------+-------+-------+---------+--------+\n" +
            "|Advance     |   30  |  90   |    60   |    3   |\n" +
            "+------------+-------+-------+---------+--------+\n"
        );

        System.out.print("Enter number: ");
        String number = input.nextLine();
        int totalShips = 0;
        int totalTraps = 0;
        int totalPotions = 0;

        switch (number) {

            case "1":
                totalShips = 40;
                totalTraps = 30;
                totalPotions = 20;
                break;

            case "2":
                totalShips = 30;
                totalTraps = 60;
                totalPotions = 40;
                break;

            case "3":
                totalShips = 30;
                totalTraps = 90;
                totalPotions = 60;
                break;

            default:
                System.out.println("Invalid input!!");
                break;
        }

        Game game = new Game();
        game.createShips(totalShips);
        game.createTraps(totalTraps);
        game.createPotions(totalPotions);

        //uncomment this region to see all ship, trap and potion coordinates
        /*
        for (SeaObject eachSeaObject: game.getList()) {
            
            if (eachSeaObject instanceof Ship) {
                ((Ship) eachSeaObject).printCoordinates(1);

            } else if (eachSeaObject instanceof Trap) {
                ((Trap) eachSeaObject).printCoordinate(1);
                
            } else if (eachSeaObject instanceof Potion) {
                ((Potion) eachSeaObject).printCoordinate(1);
            }
        }
        */

        boolean keepPlaying = true;
        while (keepPlaying) {

            game.getSea().printMap();

            System.out.println
            (
                "--------------------------------------------\n" +
                "Ships bombed: " + game.getTotalShipsBombed() + "\n" +
                "Lives left: " + game.getPlayer().getLivesLeft()
            );

            if (game.getTotalShipsBombed() == 5 || game.getPlayer().getLivesLeft() == 0) {
                keepPlaying = false;

            } else {

                game.getPlayer().enterCoordinate();

                int[][] playerCoordinate = game.getPlayer().getCoordinate();
                int[][] seaObjectCoordinate = new int[1][2];

                boolean playerHitSeaObject = false;
                int tempIndex = 0;

                if (game.checkInputOutOfRange(playerCoordinate) != true) {

                    for (int index = 0; index < game.getList().size(); index++) {

                        if (game.getSeaObject(index) instanceof Ship) {
                            seaObjectCoordinate = game.getShip(index).getCoordinates();

                        } else if (game.getSeaObject(index) instanceof Trap) {
                            seaObjectCoordinate = game.getTrap(index).getCoordinate();

                        } else if (game.getSeaObject(index) instanceof Potion) {
                            seaObjectCoordinate = game.getPotion(index).getCoordinate();
                        }

                        if (game.checkDuplicate(playerCoordinate, seaObjectCoordinate) == true) {
                            playerHitSeaObject = true;
                            tempIndex = index;
                            break;
                        }
                    }//end for loop

                    if (playerHitSeaObject == true) {

                        if (game.getSeaObject(tempIndex) instanceof Ship) {

                            if (game.getShip(tempIndex).getIsBombed() == false) {

                                System.out.println
                                (
                                    "\n+------------------------------------------+" +
                                    "\n|         You have bombed a ship!!         |" +
                                    "\n+------------------------------------------+\n"
                                );

                                game.getShip(tempIndex).isBombed();
                                game.increaseTotalShipsBombed();

                                int[][] shipCoordinates = game.getShip(tempIndex).getCoordinates();
                                char shipSymbol = game.getShip(tempIndex).getSymbol();
                                game.getSea().updateMap(shipCoordinates, shipSymbol);

                                game.getPlayer().reduceLives();

                            } else {
                                System.out.println
                                (
                                    "\n+------------------------------------------+" +
                                    "\n|   This ship have been bombed before!!    |" +
                                    "\n+------------------------------------------+\n"
                                );
                            }

                        //check from here
                        } else if (game.getSeaObject(tempIndex) instanceof Trap) {

                            if (game.getTrap(tempIndex).getIsBombed() == false) {

                                //update attributes of trap object
                                game.getTrap(tempIndex).isBombed();

                                //update game attributes for trap object
                                game.increaseTotalTrapsFound();

                                //update map of the sea
                                int[][] trapCoordinate = game.getTrap(tempIndex).getCoordinate();
                                char trapSymbol = game.getTrap(tempIndex).getSymbol();
                                game.getSea().updateMap(trapCoordinate, trapSymbol);

                                //apply trap effect
                                game.trapEffect(game.getTrap(tempIndex));

                                game.getPlayer().reduceLives();

                            } else {
                                System.out.println
                                (
                                    "\n+------------------------------------------+" +
                                    "\n|    This trap have been found before!!    |" +
                                    "\n+------------------------------------------+\n"
                                );
                            }

                        } else if (game.getSeaObject(tempIndex) instanceof Potion) {

                            if (game.getPotion(tempIndex).getIsBombed() == false) {

                                if (game.getPotion(tempIndex) instanceof LifeSaver) {

                                    //update attributes of potion object
                                    game.getPotion(tempIndex).isBombed();

                                    //update map of the sea
                                    int[][] potionCoordinate = game.getPotion(tempIndex).getCoordinate();
                                    char potionSymbol = game.getPotion(tempIndex).getSymbol();
                                    game.getSea().updateMap(potionCoordinate, potionSymbol);

                                    //apply potion effect
                                    game.potionEffect(game.getLifeSaver(tempIndex));

                                } else if (game.getPotion(tempIndex) instanceof TrapReveal) {

                                    //update attributes of potion object
                                    game.getPotion(tempIndex).isBombed();

                                    //update map of the sea
                                    int[][] potionCoordinate = game.getPotion(tempIndex).getCoordinate();
                                    char potionSymbol = game.getPotion(tempIndex).getSymbol();
                                    game.getSea().updateMap(potionCoordinate, potionSymbol);

                                    //apply trap effect
                                    int randomTrapIndex = game.potionEffect(game.getTrapReveal(tempIndex));

                                    if (randomTrapIndex != -1) {

                                        //update attributes of trap object after potion effect is used
                                        game.getTrap(randomTrapIndex).isBombed();

                                        //update game attributes for trap object
                                        game.increaseTotalTrapsFound();

                                        //update map of the sea
                                        int[][] trapCoordinate = game.getTrap(randomTrapIndex).getCoordinate();
                                        char trapSymbol = game.getTrap(randomTrapIndex).getSymbol();
                                        game.getSea().updateMap(trapCoordinate, trapSymbol);

                                        game.getPlayer().reduceLives();
                                    }

                                } else if (game.getPotion(tempIndex) instanceof ShipReveal) {

                                    //update attributes of potion object
                                    game.getPotion(tempIndex).isBombed();

                                    //update map of the sea
                                    int[][] potionCoordinate = game.getPotion(tempIndex).getCoordinate();
                                    char potionSymbol = game.getPotion(tempIndex).getSymbol();
                                    game.getSea().updateMap(potionCoordinate, potionSymbol);

                                    //apply potion effect
                                    int randomShipIndex = game.potionEffect(game.getShipReveal(tempIndex));

                                    //update ship object
                                    game.getShip(randomShipIndex).isBombed();
                                    game.increaseTotalShipsBombed();

                                    //update map of the sea
                                    int[][] shipCoordinates = game.getShip(randomShipIndex).getCoordinates();
                                    char shipSymbol = game.getShip(randomShipIndex).getSymbol();
                                    game.getSea().updateMap(shipCoordinates, shipSymbol);

                                    game.getPlayer().reduceLives();
                                }

                            } else {
                                System.out.println
                                (
                                    "\n+------------------------------------------+" +
                                    "\n|   This potion have been found before!!   |" +
                                    "\n+------------------------------------------+\n"
                                );
                            }
                        }

                    } else {
                        System.out.println
                        (
                            "\n+------------------------------------------+" +
                            "\n|               You missed!!               |" +
                            "\n+------------------------------------------+\n"
                        );

                        game.getPlayer().reduceLives();
                        game.getSea().updateMap(game.getPlayer().getCoordinate(), 'X');
                    }
                } else {
                    System.out.println
                    (
                        "\n+------------------------------------------+" +
                        "\n|   Your coordinates are out of range!!    |" +
                        "\n+------------------------------------------+\n"
                    );
                }
            }
        }

        System.out.println
        (
            "\n================ Game Over =================\n"
        );
    }
}