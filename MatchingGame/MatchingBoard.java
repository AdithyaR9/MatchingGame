
package MatchingGame;

public class MatchingBoard {

    public static final int NUM_ROWS = 4;
    public static final int NUM_COLUMNS = 13;
    public static final int NUM_SUITECARDS = 4;

    public MatchingCard[][] cardArray = new MatchingCard[NUM_ROWS][NUM_COLUMNS];

    public MatchingBoard() {

        createBoard();
        setValues();

    }

    public void createBoard() {

        for (int x = 0; x < NUM_ROWS; x++) {
            for (int y = 0; y < NUM_COLUMNS; y++) {
                cardArray[x][y] = new MatchingCard();
            }
        }
    }

    public void setValues() {

        //A
        for (int x = 0; x < NUM_SUITECARDS; x++) {

            int row = (int) (Math.random() * NUM_ROWS);
            int col = (int) (Math.random() * NUM_COLUMNS);


            while (cardArray[row][col].getCardSuite() != -1) {

                row = (int) (Math.random() * NUM_ROWS);
                col = (int) (Math.random() * NUM_COLUMNS);

            }

            cardArray[row][col].setCardSuite(1);
        }

        //2
        for (int x = 0; x < NUM_SUITECARDS; x++) {

            int row = (int) (Math.random() * NUM_ROWS);
            int col = (int) (Math.random() * NUM_COLUMNS);

            while (cardArray[row][col].getCardSuite() != -1) {

                row = (int) (Math.random() * NUM_ROWS);
                col = (int) (Math.random() * NUM_COLUMNS);

            }

            cardArray[row][col].setCardSuite(2);
        }

        //3
        for (int x = 0; x < NUM_SUITECARDS; x++) {

            int row = (int) (Math.random() * NUM_ROWS);
            int col = (int) (Math.random() * NUM_COLUMNS);

            while (cardArray[row][col].getCardSuite() != -1) {

                row = (int) (Math.random() * NUM_ROWS);
                col = (int) (Math.random() * NUM_COLUMNS);

            }

            cardArray[row][col].setCardSuite(3);
        }

        //4
        for (int x = 0; x < NUM_SUITECARDS; x++) {

            int row = (int) (Math.random() * NUM_ROWS);
            int col = (int) (Math.random() * NUM_COLUMNS);

            while (cardArray[row][col].getCardSuite() != -1) {

                row = (int) (Math.random() * NUM_ROWS);
                col = (int) (Math.random() * NUM_COLUMNS);

            }

            cardArray[row][col].setCardSuite(4);
        }

        //5
        for (int x = 0; x < NUM_SUITECARDS; x++) {

            int row = (int) (Math.random() * NUM_ROWS);
            int col = (int) (Math.random() * NUM_COLUMNS);

            while (cardArray[row][col].getCardSuite() != -1) {

                row = (int) (Math.random() * NUM_ROWS);
                col = (int) (Math.random() * NUM_COLUMNS);

            }

            cardArray[row][col].setCardSuite(5);
        }

        //6
        for (int x = 0; x < NUM_SUITECARDS; x++) {

            int row = (int) (Math.random() * NUM_ROWS);
            int col = (int) (Math.random() * NUM_COLUMNS);

            while (cardArray[row][col].getCardSuite() != -1) {

                row = (int) (Math.random() * NUM_ROWS);
                col = (int) (Math.random() * NUM_COLUMNS);

            }

            cardArray[row][col].setCardSuite(6);
        }

        //7
        for (int x = 0; x < NUM_SUITECARDS; x++) {

            int row = (int) (Math.random() * NUM_ROWS);
            int col = (int) (Math.random() * NUM_COLUMNS);

            while (cardArray[row][col].getCardSuite() != -1) {

                row = (int) (Math.random() * NUM_ROWS);
                col = (int) (Math.random() * NUM_COLUMNS);

            }

            cardArray[row][col].setCardSuite(7);
        }

        //8
        for (int x = 0; x < NUM_SUITECARDS; x++) {

            int row = (int) (Math.random() * NUM_ROWS);
            int col = (int) (Math.random() * NUM_COLUMNS);

            while (cardArray[row][col].getCardSuite() != -1) {

                row = (int) (Math.random() * NUM_ROWS);
                col = (int) (Math.random() * NUM_COLUMNS);

            }

            cardArray[row][col].setCardSuite(8);
        }

        //9
        for (int x = 0; x < NUM_SUITECARDS; x++) {

            int row = (int) (Math.random() * NUM_ROWS);
            int col = (int) (Math.random() * NUM_COLUMNS);

            while (cardArray[row][col].getCardSuite() != -1) {

                row = (int) (Math.random() * NUM_ROWS);
                col = (int) (Math.random() * NUM_COLUMNS);

            }

            cardArray[row][col].setCardSuite(9);
        }

        //10
        for (int x = 0; x < NUM_SUITECARDS; x++) {

            int row = (int) (Math.random() * NUM_ROWS);
            int col = (int) (Math.random() * NUM_COLUMNS);

            while (cardArray[row][col].getCardSuite() != -1) {

                row = (int) (Math.random() * NUM_ROWS);
                col = (int) (Math.random() * NUM_COLUMNS);

            }

            cardArray[row][col].setCardSuite(10);
        }

        //J
        for (int x = 0; x < NUM_SUITECARDS; x++) {

            int row = (int) (Math.random() * NUM_ROWS);
            int col = (int) (Math.random() * NUM_COLUMNS);

            while (cardArray[row][col].getCardSuite() != -1) {

                row = (int) (Math.random() * NUM_ROWS);
                col = (int) (Math.random() * NUM_COLUMNS);

            }

            cardArray[row][col].setCardSuite(11);
        }

        //Q
        for (int x = 0; x < NUM_SUITECARDS; x++) {

            int row = (int) (Math.random() * NUM_ROWS);
            int col = (int) (Math.random() * NUM_COLUMNS);

            while (cardArray[row][col].getCardSuite() != -1) {

                row = (int) (Math.random() * NUM_ROWS);
                col = (int) (Math.random() * NUM_COLUMNS);

            }
            cardArray[row][col].setCardSuite(12);
        }

        //K
        for (int x = 0; x < NUM_SUITECARDS; x++) {

            int row = (int) (Math.random() * NUM_ROWS);
            int col = (int) (Math.random() * NUM_COLUMNS);

            while (cardArray[row][col].getCardSuite() != -1) {

                row = (int) (Math.random() * NUM_ROWS);
                col = (int) (Math.random() * NUM_COLUMNS);


            }
            cardArray[row][col].setCardSuite(13);
        }

    }

    public String toString() {

        String toString = "";

        for (int y = 0; y < NUM_ROWS; y++) {
            for (int x = 0; x < NUM_COLUMNS; x++) {
                toString += cardArray[y][x].toString() + " ";
            }
            toString += "\n";
        }
        return toString + "\n";

    }

}
