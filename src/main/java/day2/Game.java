package day2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Game{
    public int id;
    private final List<Round> rounds;

    public Game(int id, List<Round> rounds){
        this.id = id;
        this.rounds = rounds;
    }

    /**
     * returns the ID of the game if it is a possible game, otherwise 0
     * @return ID of the game
     */
    public int returnIdIfGameIsPossible(Map<CubeColor, Integer> bag){
        return rounds.stream()
            .anyMatch(r -> r.hasExtractedMoreBallsThanBag(bag))
                ? 0
                : this.id;
    }

    /**
     * get the power of the minimum set of cubes required to play this game
     * @return
     */
    public int getMinimumPower(){
        return getMinimumBag().values().stream().reduce(1, (product, cubeCount) -> product * cubeCount);
    }

    /**
     * Returns a bag with the minimum number of cubes required to play this game
     * @return
     */
    private Map<CubeColor, Integer> getMinimumBag(){
        Map<CubeColor, Integer> minGameCubes = new HashMap<>();
        for(Round r : rounds){
            for(CubeColor color : CubeColor.values()){
                // max between the highest number of cubes required up to this point
                // and the ones required for the next round
                minGameCubes.put(color, Math.max(
                        Optional.ofNullable(r.getMaxRevealedCubes().get(color)).orElse(0),
                        Optional.ofNullable(minGameCubes.get(color)).orElse(0)
                ));
            }
        }
        return minGameCubes;
    }
}