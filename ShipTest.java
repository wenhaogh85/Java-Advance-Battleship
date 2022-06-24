package battleship;

import java.util.ArrayList;

public class ShipTest {
    public static void main(String[] args) {

        ArrayList<SeaObject> seaObjectList = new ArrayList<SeaObject>();

        for (int shipNumber = 1; shipNumber <= 10; shipNumber++) {
            System.out.println("-----------------------------------------------------");

            Ship shipObject = new Ship();
            shipObject.setCoordinate(20, 40);
            System.out.println("shipNumber : " + shipNumber);
            shipObject.printCoordinates();

            boolean duplicate = false;

            if (checkOutOfRange(shipObject.getCoordinates()) != true) {

                int[][] temp1 = shipObject.getCoordinates();
                if (shipNumber > 1) {

                    for (int index = 0; index < seaObjectList.size(); index++) {

                        int[][] temp2 = ((Ship) seaObjectList.get(index)).getCoordinates();

                        if (checkDuplicate(temp1, temp2) == true) {
                            System.out.println("Duplicate match with shipNumber: " + (index + 1) + "\n");
                            duplicate = true;
                            shipNumber--;
                            break;
                        }
                    }//end 2nd for loop

                    if (duplicate != true) {
                        System.out.println("Add new ship to shipList: " + shipNumber + "\n");
                        seaObjectList.add(shipObject);
                    }

                } else {
                    System.out.println("Add new ship to shipList: " + shipNumber + "\n");
                    seaObjectList.add(shipObject);
                }

            } else {
                System.out.println("****** Ship coordinate is out of range!! ******\n");
                shipNumber--;
            }
        }//end 1st for loop

        //final result
        System.out.println("Final result");
        for (SeaObject eachSeaObject: seaObjectList) {
            System.out.println("-----------------------------------------------------");
            if (eachSeaObject instanceof Ship) {
                ((Ship) eachSeaObject).printCoordinates();
            }
        }
    }

    public static boolean checkOutOfRange(int[][] coordinates) {

        boolean outOfBound = false;
        for (int row = 0; row < coordinates.length; row++) {
            if (coordinates[row][0] > (10 - 1) || coordinates[row][1] > (30 - 1) ) {
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
                    System.out.println("^^^^^^ Duplicate detected!! ^^^^^^");
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