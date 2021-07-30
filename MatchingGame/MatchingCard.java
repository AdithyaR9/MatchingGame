
package MatchingGame;

public class MatchingCard {

    private boolean flipped;
    private double xPos;
    private double yPos;
    private int cardSuite;

    public MatchingCard() {

        cardSuite=-1;
        flipped = false;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public int getCardSuite() {
        return cardSuite;
    }

    public void setCardSuite(int cardSuite) {
        this.cardSuite = cardSuite;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public String toString() {

        if (cardSuite == 1)
            return "A";
        if (cardSuite == 2)
            return "2";
        if (cardSuite == 3)
            return "3";
        if (cardSuite == 4)
            return "4";
        if (cardSuite == 5)
            return "5";
        if (cardSuite == 6)
            return "6";
        if (cardSuite == 7)
            return "7";
        if (cardSuite == 8)
            return "8";
        if (cardSuite == 9)
            return "9";
        if (cardSuite == 10)
            return "10";
        if (cardSuite == 11)
            return "J";
        if (cardSuite == 12)
            return "Q";
        if (cardSuite == 13)
            return "K";
        else return "E";
    }

}
