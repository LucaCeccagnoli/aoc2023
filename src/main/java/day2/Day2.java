package day2;

import java.util.List;
import java.util.Map;

public class Day2{
    public static int part1(Map<CubeColor, Integer> bag, List<String> inputs){
        return inputs.stream()
                .map(GameParser::parseGame)
                .reduce( 0,
                        (idSum, game) -> idSum += game.returnIdIfGameIsPossible(bag),
                        Integer::sum
                );
    }

    public static int part2(List<String> inputs){
        return inputs.stream()
                .map(GameParser::parseGame)
                .reduce( 0,
                        (totalPower, game) -> totalPower += game.getMinimumPower(),
                        Integer::sum
                );
    }
}