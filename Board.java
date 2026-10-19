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
}