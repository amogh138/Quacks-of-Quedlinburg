import java.util.*;
import java.awt.image.BufferedImage;

public class Board {
    private final int LENGTH;
    private final int HEIGHT;
    private int[] coinTrack;
    private int[] pointTrack;
    private boolean[] rubySpaces;

    public Board(int l, int h, int[] coinSet, int[] pointSet, boolean[] rubySet) {
        LENGTH = l;
        HEIGHT = h;
        coinTrack = Arrays.copyOf(coinSet, l);
        pointTrack = Arrays.copyOf(pointSet, l);
        rubySpaces = Arrays.copyOf(rubySet, l);
    }

    public int getCoins(int position) {
        validatePosition(position);
        return coinTrack[position];
    }

    public int getPoints(int position) {
        validatePosition(position);
        return pointTrack[position];
    }

    public boolean hasRuby(int position) {
        validatePosition(position);
        return rubySpaces[position];
    }

    private void validatePosition(int position) {
        if (position < 0 || position >= LENGTH) {
            throw new IndexOutOfBoundsException("Position " + position + " out of bounds [0-" + (LENGTH-1) + "]");
        }
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getLength() {
        return LENGTH;
    }

    public void setCoins(int position, int coins) {
        validatePosition(position);
        coinTrack[position] = coins;
    }

    public void setPoints(int position, int points) {
        validatePosition(position);
        pointTrack[position] = points;
    }

    public void setRuby(int position, boolean hasRuby) {
        validatePosition(position);
        rubySpaces[position] = hasRuby;
    }

    public int[] getCoinTrack() {
        return Arrays.copyOf(coinTrack, LENGTH);
    }

    public int[] getPointTrack() {
        return Arrays.copyOf(pointTrack, LENGTH);
    }

    public boolean[] getRubySpaces() {
        return Arrays.copyOf(rubySpaces, LENGTH);
    }

    public void reset() {
        for(int i = 0; i < LENGTH; i++) {
            coinTrack[i] = 0;
            pointTrack[i] = 0;
            rubySpaces[i] = false;
        }
    }

    public void resetCoins() {
        Arrays.fill(coinTrack, 0);
    }

    public void resetPoints() {
        Arrays.fill(pointTrack, 0);
    }

    public void resetRubies() {
        Arrays.fill(rubySpaces, false);
    }

    public void setBoard(Board b) {
        coinTrack = Arrays.copyOf(b.getCoinTrack(), LENGTH);
        pointTrack = Arrays.copyOf(b.getPointTrack(), LENGTH);
        rubySpaces = Arrays.copyOf(b.getRubySpaces(), LENGTH);
    }

    public void setBoard(Board b, boolean reset) {
        if(reset) {
            reset();
        }
        coinTrack = Arrays.copyOf(b.getCoinTrack(), LENGTH);
        pointTrack = Arrays.copyOf(b.getPointTrack(), LENGTH);
        rubySpaces = Arrays.copyOf(b.getRubySpaces(), LENGTH);
    }

    public void setBoard(int[] coinSet, int[] pointSet, boolean[] rubySet) {
        coinTrack = Arrays.copyOf(coinSet, LENGTH);
        pointTrack = Arrays.copyOf(pointSet, LENGTH);
        rubySpaces = Arrays.copyOf(rubySet, LENGTH);
    }

    public void setBoard(int[] coinSet, int[] pointSet) {
        coinTrack = Arrays.copyOf(coinSet, LENGTH);
        pointTrack = Arrays.copyOf(pointSet, LENGTH);
    }

    public void setBoard(int[] coinSet, boolean[] rubySet) {
        coinTrack = Arrays.copyOf(coinSet, LENGTH);
        rubySpaces = Arrays.copyOf(rubySet, LENGTH);
    }

    public void setBoard(boolean[] rubySet, int[] pointSet) {
        rubySpaces = Arrays.copyOf(rubySet, LENGTH);
        pointTrack = Arrays.copyOf(pointSet, LENGTH);
    }

    public void setBoard(boolean[] rubySet) {
        rubySpaces = Arrays.copyOf(rubySet, LENGTH);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < LENGTH; i++) {
            sb.append("Position ").append(i).append(": ")
              .append(coinTrack[i]).append(" coins, ")
              .append(pointTrack[i]).append(" points, Ruby: ").append(rubySpaces[i]).append("\n");
        }
        return sb.toString();
    }
}