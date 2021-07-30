package MatchingGame;

import Unit5_Set.SetClass;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MatchingApplication<a> extends Application {

    private static final int Playing = 1;
    private static final int GamerOver = 2;
    private int status;
    private boolean p1Turn;
    private int playerWon = 0;
    private double X;
    private double Y;
    private MatchingBoard board;
    private int firstRow = -1;
    private int firstCol = -1;
    private int secondRow = -1;
    private int secondCol = -1;
    private boolean flipped = false;
    private long flippedTime = 0;

    Image cardBack = new Image("File:Images/MatchingGameCardBack.png");
    Image cardFront = new Image("File:Images/MatchingGameCardFront.png");

    private SetClass<Integer> masterSet = new SetClass<>();
    private SetClass<Integer> player1Set = new SetClass<>();
    private SetClass<Integer> player2Set = new SetClass<>();


    @Override
    public void start(Stage primaryStage) throws Exception {

        board = new MatchingBoard();


        primaryStage.setTitle("Matching Game");

        Canvas canvas = new Canvas(1000, 500);

        canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                X = event.getX();
                Y = event.getY();
                int selectedRow = (int) (Y - 75) / 100;
                int selectedCol = (int) (X - 210) / 45;

                if (X < 210 || X > 800 || Y < 75) {
                    selectedCol = -1;
                    selectedRow = -1;
                }

                if (status == Playing && (firstRow == -1 && firstCol == -1) && (selectedRow != -1 && selectedCol != -1) && board.cardArray[selectedRow][selectedCol] != null) {
                    firstRow = selectedRow;
                    firstCol = selectedCol;
                    board.cardArray[firstRow][firstCol].setFlipped(true);
                } else if (status == Playing && (secondRow == -1 && secondCol == -1) && (selectedRow != -1 && selectedCol != -1) &&
                        board.cardArray[selectedRow][selectedCol] != null) {
                    if (selectedCol != firstCol || selectedRow != firstRow) {
                        secondRow = selectedRow;
                        secondCol = selectedCol;
                        board.cardArray[secondRow][secondCol].setFlipped(true);
                        flipped = true;
                        flippedTime = System.nanoTime();
                    }
                }

                if (status == GamerOver) {
                    if (event.isSecondaryButtonDown()) {
                        reset();
                    }
                }

                paint(canvas.getGraphicsContext2D());
            }
        });
        new AnimationTimer() {

            @Override
            public void handle(long currentTime) {
                setGamerOver();
                if (status == Playing && flipped && (currentTime - flippedTime) / 1000000000 > 1) {
                    if (board.cardArray[firstRow][firstCol].getCardSuite() == board.cardArray[secondRow][secondCol].getCardSuite()) {
                        if (p1Turn && !player1Set.contains(board.cardArray[firstRow][firstCol].getCardSuite())) {
                            player1Set.add(board.cardArray[firstRow][firstCol].getCardSuite());
                            board.cardArray[firstRow][firstCol] = null;
                            board.cardArray[secondRow][secondCol] = null;
                        } else if (!p1Turn && !player2Set.contains(board.cardArray[firstRow][firstCol].getCardSuite())) {
                            player2Set.add(board.cardArray[firstRow][firstCol].getCardSuite());
                            board.cardArray[firstRow][firstCol] = null;
                            board.cardArray[secondRow][secondCol] = null;
                        }
                    }
                    p1Turn = !p1Turn;
                    if (board.cardArray[firstRow][firstCol] != null && board.cardArray[secondRow][secondCol] != null) {
                        board.cardArray[firstRow][firstCol].setFlipped(false);
                        board.cardArray[secondRow][secondCol].setFlipped(false);
                    }
                    firstRow = -1;
                    firstCol = -1;
                    secondRow = -1;
                    secondCol = -1;
                    flipped = false;
                }

                paint(canvas.getGraphicsContext2D());
            }

        }.start();

        Group group = new Group();

        group.getChildren().add(canvas);

        Scene scene = new Scene(group);

        primaryStage.setScene(scene);

        reset();
        paint(canvas.getGraphicsContext2D());

        canvas.requestFocus();

        primaryStage.show();
    }

    public void reset() {

        playerWon = 0;
        status = Playing;
        p1Turn = true;
        firstRow = -1;
        firstCol = -1;
        secondRow = -1;
        secondCol = -1;
        flipped = false;
        flippedTime = 0;

        player1Set.clear();
        player2Set.clear();

        for (int x = 1; x <= 13; x++) {
            masterSet.add(x);
        }

        board.createBoard();
        board.setValues();

    }

    public void setGamerOver() {
        if (status == Playing) {
            if (player1Set.size() == masterSet.size()) {
                playerWon = 1;
                status = GamerOver;
            } else if (player2Set.size() == masterSet.size()) {
                playerWon = 2;
                status = GamerOver;
            }
        }
    }

    public void paint(GraphicsContext gc) {

        //background
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, 1000, 600);
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(200, 0, 600, 600);

        //player headers
        gc.setFont(new Font("Times New Roman", 30));
        gc.setLineWidth(5);

        //P1
        gc.strokeRect(25, 20, 150, 50);
        if (p1Turn) {
            gc.setFill(Color.YELLOW);
            gc.fillRect(25, 20, 150, 50);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("Player 1", 40, 55);

        //P2
        gc.strokeRect(825, 20, 150, 50);
        if (!p1Turn) {
            gc.setFill(Color.YELLOW);
            gc.fillRect(825, 20, 150, 50);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("Player 2", 840, 55);

        //Player Sides
        gc.setFont(new Font("Times New Roman", 30));
        //P1
        gc.setStroke(Color.BLACK);
        //A
        gc.strokeRect(40, 100, 40, 40);
        if (player1Set.contains(1)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(40, 100, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("A", 50, 130);
        //2
        gc.strokeRect(40, 150, 40, 40);
        if (player1Set.contains(2)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(40, 150, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("2", 52.5, 180);
        //3
        gc.strokeRect(40, 200, 40, 40);
        if (player1Set.contains(3)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(40, 200, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("3", 52.5, 230);
        //4
        gc.strokeRect(40, 250, 40, 40);
        if (player1Set.contains(4)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(40, 250, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("4", 52.5, 280);
        //5
        gc.strokeRect(40, 300, 40, 40);
        if (player1Set.contains(5)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(40, 300, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("5", 52.5, 330);
        //6
        gc.strokeRect(40, 350, 40, 40);
        if (player1Set.contains(6)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(40, 350, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("6", 52.5, 380);
        //7
        gc.strokeRect(100, 100, 40, 40);
        if (player1Set.contains(7)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(100, 100, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("7", 112.5, 130);
        //8
        gc.strokeRect(100, 150, 40, 40);
        if (player1Set.contains(8)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(100, 150, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("8", 112.5, 180);
        //9
        gc.strokeRect(100, 200, 40, 40);
        if (player1Set.contains(9)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(100, 200, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("9", 112.5, 230);
        //10
        gc.strokeRect(100, 250, 40, 40);
        if (player1Set.contains(10)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(100, 250, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("10", 105, 280);
        //J
        gc.strokeRect(100, 300, 40, 40);
        if (player1Set.contains(11)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(100, 300, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("J", 115, 330);
        //Q
        gc.strokeRect(100, 350, 40, 40);
        if (player1Set.contains(12)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(100, 350, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("Q", 110, 380);
        //K
        gc.strokeRect(100, 400, 40, 40);
        if (player1Set.contains(13)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(100, 400, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("K", 110, 430);

        //P2
        gc.setStroke(Color.BLACK);
        //A
        gc.strokeRect(840, 100, 40, 40);
        if (player2Set.contains(1)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(840, 100, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("A", 850, 130);
        //2
        gc.strokeRect(840, 150, 40, 40);
        if (player2Set.contains(2)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(840, 150, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("2", 852.5, 180);
        //3
        gc.strokeRect(840, 200, 40, 40);
        if (player2Set.contains(3)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(840, 200, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("3", 852.5, 230);
        //4
        gc.strokeRect(840, 250, 40, 40);
        if (player2Set.contains(4)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(840, 250, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("4", 852.5, 280);
        //5
        gc.strokeRect(840, 300, 40, 40);
        if (player2Set.contains(5)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(840, 300, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("5", 852.5, 330);
        //6
        gc.strokeRect(840, 350, 40, 40);
        if (player2Set.contains(6)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(840, 350, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("6", 852.5, 380);
        //7
        gc.strokeRect(900, 100, 40, 40);
        if (player2Set.contains(7)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(900, 100, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("7", 912.5, 130);
        //8
        gc.strokeRect(900, 150, 40, 40);
        if (player2Set.contains(8)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(900, 150, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("8", 912.5, 180);
        //9
        gc.strokeRect(900, 200, 40, 40);
        if (player2Set.contains(9)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(900, 200, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("9", 912.5, 230);
        //10
        gc.strokeRect(900, 250, 40, 40);
        if (player2Set.contains(10)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(900, 250, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("10", 905, 280);
        //J
        gc.strokeRect(900, 300, 40, 40);
        if (player2Set.contains(11)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(900, 300, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("J", 912.5, 330);
        //Q
        gc.strokeRect(900, 350, 40, 40);
        if (player2Set.contains(12)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(900, 350, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("Q", 910, 380);
        //K
        gc.strokeRect(900, 400, 40, 40);
        if (player2Set.contains(13)) {
            gc.setFill(Color.GREEN);
            gc.fillRect(900, 400, 40, 40);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("K", 910, 430);

        gc.setFont(new Font("Times New Roman", 30));

        int xcount = 0;
        int ycount = 0;
        for (int x = 210; xcount < 13; x += 45) {
            ycount = 0;

            for (int y = 75; ycount < 4; y += 100) {
                if (board.cardArray[ycount][xcount] != null && !board.cardArray[ycount][xcount].isFlipped()) {
                    int moveOver = 12;
                    if (board.cardArray[ycount][xcount].getCardSuite() == 10) {
                        moveOver = 6;
                    }
                    if (board.cardArray[ycount][xcount].getCardSuite() == 12 || board.cardArray[ycount][xcount].getCardSuite() == 13 ||
                            board.cardArray[ycount][xcount].getCardSuite() == 1) {
                        moveOver = 10;
                    }
                    gc.fillText(board.cardArray[ycount][xcount].toString() + "", x + moveOver, y + 45);
                    gc.drawImage(cardBack, x, y, 40, 70);
                } else if (board.cardArray[ycount][xcount] != null && board.cardArray[ycount][xcount].isFlipped()) {
                    int moveOver = 12;
                    if (board.cardArray[ycount][xcount].getCardSuite() == 10) {
                        moveOver = 6;
                    }
                    if (board.cardArray[ycount][xcount].getCardSuite() == 12 || board.cardArray[ycount][xcount].getCardSuite() == 13 ||
                            board.cardArray[ycount][xcount].getCardSuite() == 1) {
                        moveOver = 10;
                    }
                    gc.drawImage(cardFront, x, y, 40, 70);
                    gc.fillText(board.cardArray[ycount][xcount].toString() + "", x + moveOver, y + 45);
                }
                ycount++;
            }
            xcount++;
        }

        if (status == GamerOver && playerWon != 0) {
            if (playerWon == 1) {
                gc.fillText("Player 1 has Won the Game \n Right Click to Restart", 400, 300);
            } else if (playerWon == 2) {
                gc.fillText("Player 2 has Won the Game \n Right Click to Restart", 400, 300);
            }
        }
    }


}
