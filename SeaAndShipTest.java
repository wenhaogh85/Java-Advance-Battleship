package battleship;

import java.util.ArrayList;

public class SeaAndShipTest {
    public static void main(String[] args) {

        ArrayList<SeaObject> seaObjectList = new ArrayList<SeaObject>();
        Sea sea = new Sea(20, 20, '.');

        for (int shipNumber = 1; shipNumber <= 30; shipNumber++) {

            Ship ship = new Ship();
            ship.setCoordinate(sea.getRows(), sea.getColumns());

            System.out.println("----------------------------------------------------");
            System.out.println("\nshipNumber : " + shipNumber);
            ship.printCoordinates();

            boolean duplicate = false;

            if (checkOutOfRange(ship.getCoordinates(), sea.getRows(), sea.getColumns()) != true) {

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
                        System.out.println("\nAdd new ship to seaObjectList: " + shipNumber + "\n");
                        seaObjectList.add(ship);
                        sea.updateMap(ship.getCoordinates(), 'O');
                        sea.printMap();
                    }

                } else {
                    System.out.println("\nAdd new ship to seaObjectList: " + shipNumber + "\n");
                    seaObjectList.add(ship);
                    sea.updateMap(ship.getCoordinates(), 'O');
                    sea.printMap();
                }

            } else {
                System.out.println("\n****** Ship coordinate is out of range!! ******\n");
                sea.printMap();
                shipNumber--;
            }
        }//end 1st for loop

        System.out.println("----------------------------------------------------");
        System.out.println("\nTotal number of ships: " + seaObjectList.size() + "\n");
        sea.printMap();
    }

    public static boolean checkOutOfRange(int[][] coordinates, int maxRow, int maxColumn) {

        maxRow = maxRow - 1;
        maxColumn = maxColumn - 1;

        boolean outOfBound = false;
        for (int row = 0; row < coordinates.length; row++) {
            if (coordinates[row][0] > maxRow || coordinates[row][1] > maxColumn) {
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
                    System.out.println("\n^^^^^^ Duplicate coordinate detected!! ^^^^^^");
                    System.out.println("Duplicate value: (" + array1[row_a][0] + ", " + array1[row_a][1] + ")");
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