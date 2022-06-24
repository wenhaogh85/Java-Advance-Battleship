package battleship;

import java.util.*;

public class Game {

    private Random random = new Random();
    private ArrayList<SeaObject> List = new ArrayList<SeaObject>();
    private Sea sea = new Sea(20, 20, '.');
    private Player player = new Player();
    private int totalShipsBombed = 0;
    private int totalTrapsFound = 0;
    private int totalTrapsNotFound = 0;

    public Game() {

    }

    public void createShips(int totalShips) {

        for (int shipNumber = 1; shipNumber <= totalShips; shipNumber++) {

            Ship ship = new Ship();
            ship.setCoordinate(sea.getRows(), sea.getColumns());

            boolean duplicate = false;

            if (checkOutOfRange(ship.getCoordinates()) != true) {

                int[][] temp1 = ship.getCoordinates();
                if (shipNumber > 1) {

                    for (int index = 0; index < List.size(); index++) {

                        int[][] temp2 = getShip(index).getCoordinates();

                        if (checkDuplicate(temp1, temp2) == true) {
                            duplicate = true;
                            shipNumber--;
                            break;
                        }
                    }//end 2nd for loop

                    if (duplicate != true) {
                        List.add(ship);
                    }

                } else {
                    List.add(ship);
                }

            } else {
                shipNumber--;
            }
        }//end 1st for loop
    }

    public void createTraps(int totalTraps) {

        this.totalTrapsNotFound = totalTraps;

        for (int trapNumber = 1; trapNumber <= totalTraps; trapNumber++) {

            Trap trap;
            boolean lowDangerTrap = random.nextBoolean();

            if (lowDangerTrap == true) {
                trap = new LowDanger();
            } else {
                trap = new HighDanger();
            }

            trap.setCoordinate(sea.getRows(), sea.getColumns());

            boolean duplicate = false;

            int[][] temp1 = trap.getCoordinate();
            if (trapNumber > 1) {

                int[][] temp2 = new int[0][2];

                for (int index = 0; index < List.size(); index++) {

                    if (List.get(index) instanceof Trap) {
                        temp2 = getTrap(index).getCoordinate();

                    } else if (List.get(index) instanceof Ship) {
                        temp2 = getShip(index).getCoordinates();
                    }

                    //checks for duplicate coordinate
                    if (checkDuplicate(temp1, temp2) == true) {
                        duplicate = true;
                        trapNumber--;
                        break;
                    }
                }

            } else {

                for (int index = 0; index < List.size(); index++) {

                    int[][] temp3 = getShip(index).getCoordinates();

                    if (checkDuplicate(temp1, temp3) == true) {
                        duplicate = true;
                        trapNumber--;
                        break;
                    }
                }
            }

            if (duplicate != true) {
                List.add(trap);
            }
        }
    }

    public void createPotions(int totalPotions) {

        for (int potionNumber = 1; potionNumber <= totalPotions; potionNumber++) {

            Potion potion;
            int potionType = 1 + random.nextInt((3 - 1) + 1);

            if (potionType == 1) {
                potion = new LifeSaver();

            } else if (potionType == 2) {
                potion = new TrapReveal();

            } else {
                potion = new ShipReveal();
            }

            potion.setCoordinate(sea.getRows(), sea.getColumns());

            boolean duplicate = false;

            int[][] temp1 = potion.getCoordinate();
            if (potionNumber > 1) {

                int[][] temp2 = new int[0][2];

                for (int index = 0; index < List.size(); index++) {

                    if (List.get(index) instanceof Potion) {
                        temp2 = getPotion(index).getCoordinate();

                    } else if (List.get(index) instanceof Trap) {
                        temp2 = getTrap(index).getCoordinate();

                    } else if (List.get(index) instanceof Ship) {
                        temp2 = getShip(index).getCoordinates();
                    }

                    //checks for duplicate coordinate
                    if (checkDuplicate(temp1, temp2) == true) {
                        duplicate = true;
                        potionNumber--;
                        break;
                    }
                }

            } else {

                int[][] temp3 = new int[0][2];

                for (int index = 0; index < List.size(); index++) {

                    if (List.get(index) instanceof Trap) {
                        temp3 = getTrap(index).getCoordinate();

                    } else if (List.get(index) instanceof Ship) {
                        temp3 = getShip(index).getCoordinates();
                    }

                    if (checkDuplicate(temp1, temp3) == true) {
                        duplicate = true;
                        potionNumber--;
                        break;
                    }
                }
            }

            if (duplicate != true) {
                List.add(potion);
            }
        }
    }

    public boolean checkOutOfRange(int[][] coordinates) {

        int maxRow = sea.getRows() - 1;
        int maxColumn = sea.getColumns() - 1;

        boolean outOfBound = false;
        for (int row = 0; row < coordinates.length; row++) {
            if (coordinates[row][0] > maxRow || coordinates[row][1] > maxColumn) {
                outOfBound = true;
                break;
            }
        }

        return outOfBound;
    }

    public boolean checkDuplicate(int[][] array1, int[][] array2) {

        boolean duplicate = false;
        for (int row_a = 0; row_a < array1.length; row_a++) {
            for (int row_b = 0; row_b < array2.length; row_b++) {

                if ((array1[row_a][0] == array2[row_b][0]) && (array1[row_a][1] == array2[row_b][1])) {
                    duplicate = true;
                    break;
                }
            }//end 2nd for loop

            if (duplicate == true) {
                break;
            }
        }//end 1st for loop

        return duplicate;
    }

    public ArrayList<SeaObject> getList() {
        return List;
    }

    public Player getPlayer() {
        return player;
    }

    public void increaseTotalTrapsFound() {
        totalTrapsFound = totalTrapsFound + 1;
    }

    public void increaseTotalShipsBombed() {
        totalShipsBombed = totalShipsBombed + 1;
    }

    public int getTotalShipsBombed() {
        return totalShipsBombed;
    }

    public Sea getSea() {
        return sea;
    }

    public SeaObject getSeaObject(int index) {
        return List.get(index);
    }

    public Ship getShip(int index) {
        return (Ship) List.get(index);
    }

    public Trap getTrap(int index) {
        return (Trap) List.get(index);
    }

    public Potion getPotion(int index) {
        return (Potion) List.get(index);
    }

    public LifeSaver getLifeSaver(int index) {
        return (LifeSaver) ((Potion) List.get(index));
    }

    public TrapReveal getTrapReveal(int index) {
        return (TrapReveal) ((Potion) List.get(index));
    }

    public ShipReveal getShipReveal(int index) {
        return (ShipReveal) ((Potion) List.get(index));
    }

    public void trapEffect(Trap trap) {

        if (trap instanceof LowDanger) {

            System.out.println
            (
                "\n+------------------------------------------+" +
                "\n| You have found a low danger trap (L) :'( |" +
                "\n|      Your life is reduced by 1.          |" +
                "\n+------------------------------------------+\n"
            );

            player.reduceLivesBy(((LowDanger) trap).getLifeValue());

        } else {

            System.out.println
            (
                "\n+------------------------------------------+" +
                "\n|You have found a high danger trap (H) :'( |" +
                "\n|       Your life is reduced by 2.         |" +
                "\n+------------------------------------------+\n"
            );

            player.reduceLivesBy(((HighDanger) trap).getLifeValue());
        }
    }

    public void potionEffect(LifeSaver lifeSaver) {

        System.out.println
        (
            "\n+------------------------------------------+" +
            "\n|You have found a life saver potion ($) :) |" +
            "\n|      Your life is increased by 1.        |" +
            "\n+------------------------------------------+\n"
        );

        player.increaseLivesBy(lifeSaver.getLifeValue());
    }

    public int potionEffect(TrapReveal trapReveal) {

        System.out.println
        (
            "\n+------------------------------------------+" +
            "\n|You have found a trap reveal potion (#) :)|"
        );

        int randomTrapIndex = 0;

        if (totalTrapsNotFound == totalTrapsFound) {
            randomTrapIndex = -1;

            System.out.println
            (
                "|         All traps have been found!!      |\n" +
                "|         No more traps to reveal :(       |\n" +
                "+------------------------------------------+\n"
            );

        } else {

            System.out.println
            (
                "|            A trap is revealed!!          |\n" +
                "+------------------------------------------+\n"
            );

            boolean keepFinding = true;
            while (keepFinding) {

                for (int index = 0; index < List.size(); index++) {

                    if (List.get(index) instanceof Trap) {
                        if (getTrap(index).getIsBombed() == false) {
                            randomTrapIndex = index;
                            keepFinding = false;
                            break;
                        }
                    }
                }//end of for loop
            }//end of while loop
        }

        return randomTrapIndex;
    }

    public int potionEffect(ShipReveal shipReveal) {

        System.out.println
        (
            "\n+------------------------------------------+" +
            "\n|You have found a ship reveal potion (@) :)|" +
            "\n|          A ship is revealed!!            |" +
            "\n+------------------------------------------+\n"
        );

        int randomShipIndex = 0;

        boolean keepFinding = true;
        while (keepFinding) {

            for (int index = 0; index < List.size(); index++) {
                if (List.get(index) instanceof Ship) {
                    if (getShip(index).getIsBombed() == false) {
                        randomShipIndex = index;
                        keepFinding = false;
                        break;
                    }
                }
            }//end of for loop
        }//end of while loop

        return randomShipIndex;
    }

    public boolean checkInputOutOfRange(int[][] coordinate) {

        int maxRow = sea.getRows() - 1;
        int maxColumn = sea.getColumns() - 1;

        boolean InputOutOfRange = false;
        for (int row = 0; row < coordinate.length; row++) {
            if ((coordinate[row][0] > maxRow || coordinate[row][1] > maxColumn) 
                    || (coordinate[row][0] < 0 || coordinate[row][1] < 0)) {

                InputOutOfRange = true;
                break;
            }
        }

        return InputOutOfRange;
    }

}