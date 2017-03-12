package Entity;

/**
 * Created by toprak on 3/12/2017.
 */
public class Bag {
    public int redBalls;
    public int yellowBalls;
    public int blueBalls;
    public int totalBalls;



    public Bag(int redBalls, int yellowBalls, int blueBalls) {
        this.redBalls = redBalls;
        this.yellowBalls = yellowBalls;
        this.blueBalls = blueBalls;
        totalBalls = redBalls + yellowBalls + blueBalls;
    }
}
