package battleship;

/**
 * This class is the driver test program for Player class
 * to test if there is any logic error in the program
 * before finalising the final program.
 */

public class PlayerTest {
    public static void main(String[] args) {

        Player playerObject = new Player();

        System.out.println("Lives: " + playerObject.getLivesLeft());

        playerObject.enterCoordinate();
        int[][] store = playerObject.getCoordinate();

        System.out.println(store[0][0] + ", " + store[0][1]);
    }
}