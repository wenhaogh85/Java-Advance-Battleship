package battleship;

import java.util.*;

public class PotionTest {
    public static void main(String[] args) {

        //generate ship coodinates
        ArrayList<SeaObject> seaObjectList = new ArrayList<SeaObject>();

        for (int shipNumber = 1; shipNumber <= 30; shipNumber++) {

            Ship ship = new Ship();
            ship.setCoordinate(20, 20);

            boolean duplicate = false;

            if (checkOutOfRange(ship.getCoordinates()) != true) {

                int[][] temp1 = ship.getCoordinates();
                if (shipNumber > 1) {

                    for (int index = 0; index < seaObjectList.size(); index++) {

                        int[][] temp2 = ((Ship) seaObjectList.get(index)).getCoordinates();

                        if (checkDuplicate(temp1, temp2) == true) {
                            duplicate = true;
                            shipNumber--;
                            break;
                        }
                    }//end 2nd for loop

                    if (duplicate != true) {
                        seaObjectList.add(ship);
                    }

                } else {
                    seaObjectList.add(ship);
                }

            } else {
                shipNumber--;
                System.out.println("\n****** Coordinates are out of range ******\n");
            }
        }//end 1st for loop

        System.out.println("----------------------------------------------------");
        System.out.println("Ship coordinates");
        int number = 1;
        for(SeaObject eachSeaObject: seaObjectList) {
            System.out.println("----------------------------------------------------");
            System.out.println("shipNumber : " + number);
            number++;

            if (eachSeaObject instanceof Ship) {
                ((Ship) eachSeaObject).printCoordinates();
            }
        }

        System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Start generating trap coordinates");

        //generate trap coodinates
        Random random = new Random();
        for (int trapNumber = 1; trapNumber <= 30; trapNumber++) {

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            Trap trap;
            boolean lowDangerTrap = random.nextBoolean();

            if (lowDangerTrap == true) {
                trap = new LowDanger();
            } else {
                trap = new HighDanger();
            }

            System.out.println("trapNumber  : " + trapNumber);
            trap.setCoordinate(20, 60);
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
                        duplicate = true;
                        trapNumber--;
                        break;
                    }
                }
            }

            if (duplicate != true) {
                System.out.println("\nAdd new trap to trapList: " + trapNumber + "\n");
                seaObjectList.add(trap);
            }
        }

        System.out.println("\n\n****************************************************");
        System.out.println("Start generating potion coordinates");

        for (int potionNumber = 1; potionNumber <= 18; potionNumber++) {

            System.out.println("****************************************************");

            Potion potion;
            int potionType = 1 + random.nextInt((3 - 1) + 1);

            if (potionType == 1) {
                potion = new LifeSaver();

            } else if (potionType == 2) {
                potion = new TrapReveal();

            } else {
                potion = new ShipReveal();
            }

            System.out.println("potionNumber : " + potionNumber);
            potion.setCoordinate(20, 60);
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
                        duplicate = true;
                        potionNumber--;
                        break;
                    }
                }
            }

            if (duplicate != true) {
                System.out.println("\nAdd new potion to potionList: " + potionNumber + "\n");
                seaObjectList.add(potion);
            }
        }

    }
    
    public static boolean checkOutOfRange(int[][] coordinates) {

        boolean outOfBound = false;
        for (int row = 0; row < coordinates.length; row++) {
            if (coordinates[row][0] > 19 || coordinates[row][1] > 59) {
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