public class Game{
    public int id;
    private List<Round> rounds;
    private final Map<Balls, int> maxBalls;

    public Game(int id, Map<Balls, int> maxBalls){
        this.id = id;
        this.maxBalls = maxBalls;
        this.rounds = new ArrayList<>();
    }

    public void addRound(Round round){
        this.rounds.Add(round);
    }

    public int processRounds(){
        return rounds.stream()
            .filter(r -> !r.hasMoreBallsThan(maxBalls))
            .reduce(0, (idSum, id) -> { return idSum + id });
    }
}