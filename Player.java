package battleship;

import java.util.*;

public class Player {

    private int coordinate[][];
    private int lives;

    public Player() {
        this(15);
    }

    public Player(int lives) {
        coordinate = new int[1][2];
        this.lives = lives;
    }

    public void enterCoordinate() {
        
        boolean loop = true;
        while (loop) {

            try {
                Scanner input = new Scanner(System.in);
                System.out.print("\nEnter location (row column): ");
                int row = input.nextInt();
                int column = input.nextInt();

                coordinate[0][0] = row - 1;
                coordinate[0][1] = column - 1;

                loop = false;

            } catch (Exception ex) {
                System.out.println("Invalid input!!");
            }
        }
    }

    public int[][] getCoordinate() {
        return coordinate;
    }

    public void reduceLivesBy(int factor) {
        lives = lives - factor;
    }

    public void reduceLives() {
        lives = lives - 1;
    }

    public void increaseLivesBy(int factor) {
        lives = lives + factor;
    }

    public int getLivesLeft() {
        return lives;
    }

}