public class Board {
    private final int LENGTH;
    private final int HEIGHT;
    private int[] coinTrack;
    private int[] pointTrack;
    private boolean[] rubySpaces;
    public Board(int l, int h, int[] coinSet, int[] pointSet, boolean[] rubySet) {
        LENGTH = l;
        HEIGHT = h;
        coinTrack = coinSet;
        pointTrack = pointSet;
        rubySpaces = rubySet;
    }
    public int getCoins(int position) {
        return coinTrack[position];
    }
    public int getPoints(int position) { 
        return pointTrack[position];
    }
    public boolean hasRuby(int position) {
        if(rubySpaces[position]) {
            return true;
        }
        return false;
    }
    public int getHeight() {
        return HEIGHT;
    }
    public int getLength() {
        return LENGTH;
    }
    public void setCoins(int position, int coins) {
        coinTrack[position] = coins;
    }
    public void setPoints(int position, int points) {
        pointTrack[position] = points;
    }
    public void setRuby(int position, boolean hasRuby) {
        rubySpaces[position] = hasRuby;
    }
    public int[] getCoinTrack() {
        return coinTrack;
    }
    public int[] getPointTrack() {
        return pointTrack;
    }
    public boolean[] getRubySpaces() {
        return rubySpaces;
    }
    public void setCoinTrack(int[] coinTrack) {
        this.coinTrack = coinTrack;
    }
    public void setPointTrack(int[] pointTrack) {
        this.pointTrack = pointTrack;
    }
    public void setRubySpaces(boolean[] rubySpaces) {
        this.rubySpaces = rubySpaces;
    }
    public void reset() {
        for(int i = 0; i < LENGTH; i++) {
            coinTrack[i] = 0;
            pointTrack[i] = 0;
            rubySpaces[i] = false;
        }
    }
    public void resetCoins() {
        for(int i = 0; i < LENGTH; i++) {
            coinTrack[i] = 0;
        }
    }
    public void resetPoints() {
        for(int i = 0; i < LENGTH; i++) {
            pointTrack[i] = 0;
        }
    }
    public void resetRubies() {
        for(int i = 0; i < LENGTH; i++) {
            rubySpaces[i] = false;
        }
    }
    public void setBoard(int[] coinSet, int[] pointSet, boolean[] rubySet) {
        coinTrack = coinSet;
        pointTrack = pointSet;
        rubySpaces = rubySet;
    }
    public void setBoard(Board b) {
        coinTrack = b.getCoinTrack();
        pointTrack = b.getPointTrack();
        rubySpaces = b.getRubySpaces();
    }
    public void setBoard(int[] coinSet, int[] pointSet) {
        coinTrack = coinSet;
        pointTrack = pointSet;
    }
    public void setBoard(int[] coinSet, boolean[] rubySet) {
        coinTrack = coinSet;
        rubySpaces = rubySet;
    }
    public void setBoard(boolean[] rubySet, int[] pointSet) {
        rubySpaces = rubySet;
        pointTrack = pointSet;
    }
    public void setBoard(boolean[] rubySet) {
        rubySpaces = rubySet;
    }
    public void setBoard(Board b, boolean reset) {
        if(reset) {
            reset();
        }
    }
    public String toString() {
        String sb = "";
        for(int i = 0; i < LENGTH; i++) {
            sb += "Position " + i + ": " + coinTrack[i] + " coins, " + pointTrack[i] + " points, Ruby: " + rubySpaces[i] + "\n";
        }
        return sb;
    }
}