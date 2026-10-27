public class AI {
    private double risk;
    private Random rng;
    public AI(double risk) {}
    public boolean shouldStop(Player p) {}
    public boolean takeCoins(Player p, Board board, int round) {}
    public Ingredient chooseBuy(Player p, List<Ingredient> shop) {}
    public boolean wantFlask(Player p) {}
    public boolean wantDrop(Player p) {}
}