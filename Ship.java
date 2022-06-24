package battleship;

import java.util.Random;

public class Ship implements SeaObject{

    private boolean bombed;
    private boolean horizontal;
    private int length;
    private int[][] coordinates;
    private char symbol;

    public Ship() {
        setBombed();
        setOrientation();
        setLength();
        coordinates = new int[length][2];
        setSymbol('O');
    }

    public void setBombed() {
        bombed = false;
    }

    public boolean setOrientation() {

        //initialises random variable
        Random random = new Random();

        horizontal = random.nextBoolean();

        return horizontal;
    }

    public void setLength() {

        //initialises random variable
        Random random = new Random();

        int minLength = 3;
        int maxLength = 5;
        //sets the ship's length range between 3 to 5
        length = minLength + random.nextInt((maxLength - minLength) + 1);
    }

    public int getLength() {
        return length;
    }

     @Override
    public void setCoordinate(int seaRows, int seaColumns) {

        //initialises random variable
        Random random = new Random();

        /*generates random ship row and ship column as starting point 
        to generate the ship's coordinate*/
        int minRow = 0;
        int maxRow = seaRows - 1;
        //sets the ship's row range between 0 to maximum sea rows
        int shipRow = minRow + random.nextInt((maxRow - minRow) + 1);

        int minColumn = 0;
        int maxColumn = seaColumns - 1;
        //sets the ship's column range between 0 to maximum sea columns
        int shipColumn = minColumn + random.nextInt((maxColumn - minColumn) + 1);

        /*starts generating the ship's coordinates 
        by using shipRow and shipColumn as starting point*/
        int increment = 0;
        for (int row = 0; row < length; row++) {

            //if horizontal position is true, the ship column value is changed only
            if (horizontal == true) {
                coordinates[row][0] = shipRow;
                coordinates[row][1] = shipColumn + increment;

            //if horizontal position is false, the ship row value is changed only
            } else {
                coordinates[row][0] = shipRow + increment;
                coordinates[row][1] = shipColumn;
            }

            increment++;
        }//end of for loop
    }

    public int[][] getCoordinates() {
        return coordinates;
    }

    public void isBombed() {
        bombed = true;
    }

    public boolean getIsBombed() {
        return bombed;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void printCoordinates() {
        
        System.out.println("Bombed     : " + bombed);

        System.out.print("Orientation: ");
        if (horizontal == false) {
            System.out.println("Vertical");
        } else {
            System.out.println("Horizontal");
        }

        System.out.println("Length     : " + length);
        System.out.print("Coordinates: ");

        for (int row = 0; row < coordinates.length; row++) {

            System.out.print("(");

            for (int column = 0; column < coordinates[row].length; column++) {

                System.out.print(coordinates[row][column]);

                if (column == 0) {
                    System.out.print(",");
                }
            }//end of 2nd for loop
            System.out.print(")");

        }//end of 1st for loop
        System.out.println();
    }

    public void printCoordinates(int test) {

        System.out.print("Name: Ship | ");
        System.out.print("Coordinates: ");

        for (int row = 0; row < coordinates.length; row++) {

            System.out.print("(");

            for (int column = 0; column < coordinates[row].length; column++) {

                System.out.print(coordinates[row][column]);

                if (column == 0) {
                    System.out.print(",");
                }
            }//end of 2nd for loop
            System.out.print(")");

        }//end of 1st for loop
        System.out.println();
    }
}