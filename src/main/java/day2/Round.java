package day2;

import java.util.Map;

public class Round{
    private final Map<CubeColor, Integer> maxRevealedCubes;

    public Round(Map<CubeColor, Integer> maxRevealedCubes){
        this.maxRevealedCubes = maxRevealedCubes;
    }

    public void reveal(CubeColor ballColor, int count){
        if(maxRevealedCubes.containsKey(ballColor)){
            this.maxRevealedCubes.put(ballColor,
                    Math.max(count, maxRevealedCubes.get(ballColor)));
        } else {
            this.maxRevealedCubes.put(ballColor, count);
        }
    }

    public Map<CubeColor, Integer> getMaxRevealedCubes(){
        return this.maxRevealedCubes;
    }

    /**
     * if more balls have been revealed during this round than are contained in the input bag
     * @param bag bag of balls used for extraction
     * @return
     */
    public boolean hasExtractedMoreBallsThanBag(Map<CubeColor, Integer> bag){
        return maxRevealedCubes.keySet().stream()
                .anyMatch( color -> {
                    return maxRevealedCubes.get(color) > bag.get(color);
                });
    }
}