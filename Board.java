import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Board {
    public int LENGTH;
    public int HEIGHT;
    public int[] coinTrack;
    public int[] pointTrack;
    public boolean[] rubySpaces;
    public int lastSpaceIndex;
    public int turnIndicator;
    
    public Board(int l, int h, int[] coinSet, int[] pointSet, boolean[] rubySet) {
        LENGTH = l;
        HEIGHT = h;
        coinTrack = java.util.Arrays.copyOf(coinSet, l);
        pointTrack = java.util.Arrays.copyOf(pointSet, l);
        rubySpaces = java.util.Arrays.copyOf(rubySet, l);
        lastSpaceIndex = l - 1;
        turnIndicator = 0;
    }
    
    public int getCoins(int position) {
        validatePosition(position);
        return coinTrack[position % LENGTH];
    }
    
    public int getPoints(int position) {
        validatePosition(position);
        return pointTrack[position % LENGTH];
    }
    
    public boolean hasRuby(int position) {
        validatePosition(position);
        return rubySpaces[position % LENGTH];
    }
    
    public void validatePosition(int position) {
        if (position < 0 || position >= LENGTH) {
            throw new IndexOutOfBoundsException("Position " + position + " out of bounds (0-" + (LENGTH-1) + ")");
        }
    }
    
    public int getLastSpaceIndex() {
        return lastSpaceIndex;
    }
    
    public int getPointsAtLastSpace() {
        return pointTrack[lastSpaceIndex % LENGTH];
    }
    
    public int getCoinsAtLastSpace() {
        return coinTrack[lastSpaceIndex % LENGTH];
    }
    
    public boolean isLastSpace(int position) {
        validatePosition(position);
        return position == lastSpaceIndex;
    }
    
    public void setTurnIndicator(int position) {
        validatePosition(position);
        turnIndicator = position;
    }
    
    public int getTurnIndicator() {
        return turnIndicator;
    }
    
    public void advanceTurnIndicator() {
        turnIndicator = (turnIndicator + 1) % LENGTH;
    }
    
    public void resetTurnIndicator() {
        turnIndicator = 0;
    }
    
    public int[] getCoinTrack() {
        return java.util.Arrays.copyOf(coinTrack, LENGTH);
    }
    
    public int[] getPointTrack() {
        return java.util.Arrays.copyOf(pointTrack, LENGTH);
    }
    
    public boolean[] getRubySpaces() {
        return java.util.Arrays.copyOf(rubySpaces, LENGTH);
    }
    
    public int getScoringSpace(int position) {
        validatePosition(position);
        int score = position + 1;
        return score;
    }
    
    public int getScoringSpaceFromPoints(int points) {
        for (int i = 0; i < LENGTH; i++) {
            if (pointTrack[i] >= points) {
                return i;
            }
        }
        return LENGTH - 1;
    }
}