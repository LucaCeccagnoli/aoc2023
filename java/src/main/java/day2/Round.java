public class Round{
    private Map<Balls, int> revealedBalls;

    public Round(){
        this.revealedBalls = new HashMap<>();
    }

    public void reveal(Balls ballColor, int count){
        if(revealedBalls.hasKey(ballColor)){
            revealedBalls.set(ballColor, count);
        } else {
            revealedBalls.get(ballColor) += count;
        }
    }

    public Map<Balls, int> getRevealedBalls(){
        return this.revealedBalls;
    }

    public boolean hasMoreBallsThan(Map<balls, int> balls){
        return revealedBalls.keySet().stream()
            .anyMatch( (color, revealedAmount) -> {
                return balls.get(color) < revealedAmount;
            })
            ? true
            : false
    }
}