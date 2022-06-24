package battleship;

import java.util.*;

public class Potion implements SeaObject {

    private boolean bombed;
    private String name;
    private int[][] coordinate = new int[1][2];
    private char symbol;

    public Potion() {
        setBombed();
        setName("Potion");
        setSymbol('P');
    }

    public Potion(String name, char symbol) {
        setBombed();
        setName(name);
        setSymbol(symbol);
    }

    public void setBombed() {
        bombed = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setCoordinate(int seaRows, int seaColumns) {

        //initialises random variable
        Random random = new Random();

        /*generates random ship row and ship column as starting point 
        to generate the potion's coordinate*/
        int minRow = 0;
        int maxRow = seaRows - 1;
        //sets the potion's row range between 0 to maximum sea rows
        int potionRow = minRow + random.nextInt((maxRow - minRow) + 1);

        int minColumn = 0;
        int maxColumn = seaColumns - 1;
        //sets the potion's column range between 0 to maximum sea columns
        int potionColumn = minColumn + random.nextInt((maxColumn - minColumn) + 1);

        coordinate[0][0] = potionRow;
        coordinate[0][1] = potionColumn;
    }

    public int[][] getCoordinate() {
        return coordinate;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void isBombed() {
        bombed = true;
    }

    public boolean getIsBombed() {
        return bombed;
    }

    public void printCoordinate() {

        System.out.println("Bombed      : " + bombed);
        System.out.println("Name        : " + name);
        System.out.println("Symbol      : " + symbol);

        System.out.print("Coordinate  : ");
        System.out.println("(" + coordinate[0][0] + "," + coordinate[0][1] + ")");
    }

    public void printCoordinate(int test) {

        System.out.print("Name: " + name + " | ");
        System.out.print("Symbol: " + symbol + " | ");

        System.out.print("Coordinate: ");
        System.out.println("(" + coordinate[0][0] + "," + coordinate[0][1] + ")");
    }
}