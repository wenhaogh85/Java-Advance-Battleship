package battleship;

import java.util.*;

public class SeaAndPotionTest {
    public static void main(String[] args) {

        ArrayList<SeaObject> seaObjectList = new ArrayList<SeaObject>();
        Sea sea = new Sea(20, 20, '.');

        //generate ship coodinates
        for (int shipNumber = 1; shipNumber <= 30; shipNumber++) {

            Ship ship = new Ship();
            ship.setCoordinate(sea.getRows(), sea.getColumns());

            System.out.println("----------------------------------------------------");
            System.out.println("shipNumber : " + shipNumber);
            ship.printCoordinates();

            boolean duplicate = false;

            if (checkOutOfRange(ship.getCoordinates()) != true) {

                int[][] temp1 = ship.getCoordinates();
                if (shipNumber > 1) {

                    for (int index = 0; index < seaObjectList.size(); index++) {

                        int[][] temp2 = ((Ship) seaObjectList.get(index)).getCoordinates();

                        if (checkDuplicate(temp1, temp2) == true) {
                            System.out.println("Duplicate match with shipNumber: " + (index + 1) + "\n");
                            sea.printMap();
                            duplicate = true;
                            shipNumber--;
                            break;
                        }
                    }//end 2nd for loop

                    if (duplicate != true) {
                        seaObjectList.add(ship);
                        System.out.println("\nAdd new ship to seaObjectList: " + shipNumber + "\n");
                        sea.updateMap(ship.getCoordinates(), 'O');
                        sea.printMap();
                    }

                } else {
                    seaObjectList.add(ship);
                    System.out.println("\nAdd new ship to seaObjectList: " + shipNumber + "\n");
                    sea.updateMap(ship.getCoordinates(), 'O');
                    sea.printMap();
                }

            } else {
                shipNumber--;
                System.out.println("\n****** Coordinates are out of range ******\n");
                sea.printMap();
            }
        }//end 1st for loop

        System.out.println("----------------------------------------------------");
        System.out.println("\nTotal number of ships: " + seaObjectList.size() + "\n");
        sea.printMap();

        System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Start generating trap coordinates");

        //generate trap coodinates
        Random random = new Random();
        for (int trapNumber = 1; trapNumber <= 30; trapNumber++) {

            Trap trap;
            boolean lowDangerTrap = random.nextBoolean();

            if (lowDangerTrap == true) {
                trap = new LowDanger();
            } else {
                trap = new HighDanger();
            }

            trap.setCoordinate(sea.getRows(), sea.getColumns());

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("trapNumber  : " + trapNumber);
            trap.printCoordinate();

            boolean duplicate = false;

            int[][] temp1 = trap.getCoordinate();
            if (trapNumber > 1) {

                int[][] temp2 = new int[0][2];
                String type = "";

                for (int index = 0; index < seaObjectList.size(); index++) {

                    if (seaObjectList.get(index) instanceof Trap) {
                        temp2 = ((Trap) seaObjectList.get(index)).getCoordinate();
                        type = "trapNumber";

                    } else if (seaObjectList.get(index) instanceof Ship) {
                        temp2 = ((Ship) seaObjectList.get(index)).getCoordinates();
                        type = "shipNumber";
                    }

                    //checks for duplicate coordinate
                    if (checkDuplicate(temp1, temp2) == true) {
                        System.out.println("Duplicate match with " + type + ": " + (index + 1) + "\n");
                        sea.printMap();
                        duplicate = true;
                        trapNumber--;
                        break;
                    }
                }

            } else {

                for (int index = 0; index < seaObjectList.size(); index++) {

                    int[][] temp3 = ((Ship) seaObjectList.get(index)).getCoordinates();

                    if (checkDuplicate(temp1, temp3) == true) {
                        System.out.println("Duplicate match with shipNumber: " + (index + 1) + "\n");
                        sea.printMap();
                        duplicate = true;
                        trapNumber--;
                        break;
                    }
                }
            }

            if (duplicate != true) {
                System.out.println("\nAdd new trap to seaObjectList: " + trapNumber + "\n");
                seaObjectList.add(trap);
                sea.updateMap(trap.getCoordinate(), trap.getSymbol());
                sea.printMap();
            }
        }

        System.out.println("----------------------------------------------------");
        System.out.println("\nTotal number of traps: " + (seaObjectList.size() - 30) + "\n");
        sea.printMap();

        System.out.println("\n\n****************************************************");
        System.out.println("Start generating potion coordinates");

        //generate potion coordinates
        for (int potionNumber = 1; potionNumber <= 10; potionNumber++) {

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

            System.out.println("****************************************************");
            System.out.println("potionNumber : " + potionNumber);
            potion.printCoordinate();

            boolean duplicate = false;

            int[][] temp1 = potion.getCoordinate();
            if (potionNumber > 1) {

                int[][] temp2 = new int[0][2];
                String type = "";

                for (int index = 0; index < seaObjectList.size(); index++) {

                    if (seaObjectList.get(index) instanceof Potion) {
                        temp2 = ((Potion) seaObjectList.get(index)).getCoordinate();
                        type = "potionNumber";

                    } else if (seaObjectList.get(index) instanceof Trap) {
                        temp2 = ((Trap) seaObjectList.get(index)).getCoordinate();
                        type = "trapNumber";

                    } else if (seaObjectList.get(index) instanceof Ship) {
                        temp2 = ((Ship) seaObjectList.get(index)).getCoordinates();
                        type = "shipNumber";
                    }

                    //checks for duplicate coordinate
                    if (checkDuplicate(temp1, temp2) == true) {
                        System.out.println("Duplicate match with " + type + ": " + (index + 1) + "\n");
                        sea.printMap();
                        duplicate = true;
                        potionNumber--;
                        break;
                    }
                }

            } else {

                int[][] temp3 = new int[0][2];
                String type = "";

                for (int index = 0; index < seaObjectList.size(); index++) {

                    if (seaObjectList.get(index) instanceof Trap) {
                        temp3 = ((Trap) seaObjectList.get(index)).getCoordinate();
                        type = "trapNumber";

                    } else if (seaObjectList.get(index) instanceof Ship) {
                        temp3 = ((Ship) seaObjectList.get(index)).getCoordinates();
                        type = "shipNumber";
                    }

                    if (checkDuplicate(temp1, temp3) == true) {
                        System.out.println("Duplicate match with " + type + ": " + (index + 1) + "\n");
                        sea.printMap();
                        duplicate = true;
                        potionNumber--;
                        break;
                    }
                }
            }

            if (duplicate != true) {
                System.out.println("\nAdd new potion to seaObjectList: " + potionNumber + "\n");
                seaObjectList.add(potion);
                sea.updateMap(potion.getCoordinate(), potion.getSymbol());
                sea.printMap();
            }
        }

        System.out.println("----------------------------------------------------");
        System.out.println("\nTotal number of potions: " + (seaObjectList.size() - 30 - 30) + "\n");
        sea.printMap();
    }
    
    public static boolean checkOutOfRange(int[][] coordinates) {

        boolean outOfBound = false;
        for (int row = 0; row < coordinates.length; row++) {
            if (coordinates[row][0] > (20 - 1) || coordinates[row][1] > (20 - 1)) {
                outOfBound = true;
                break;
            }
        }

        return outOfBound;
    }

    public static boolean checkDuplicate(int[][] array1, int[][] array2) {

        boolean duplicate = false;
        for (int row_a = 0; row_a < array1.length; row_a++) {
            for (int row_b = 0; row_b < array2.length; row_b++) {

                if ((array1[row_a][0] == array2[row_b][0]) && (array1[row_a][1] == array2[row_b][1])) {
                    System.out.println("\n^^^^^^ Duplicate detected!! ^^^^^^");
                    System.out.println("Duplicate value: (" + array1[row_a][0] + "," + array1[row_a][1] + ")");
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
}