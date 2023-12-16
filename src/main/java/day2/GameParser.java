package day2;

import com.google.common.base.CharMatcher;
import org.apache.commons.lang3.EnumUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Creates a game from a line of input
 */
public class GameParser {
    public static Game parseGame(String input){
        String[] parts = input.split(":");
        String gameString = parts[0];
        String roundsString = parts[1];

        int id = parseGameId(gameString);
        List<Round> rounds = Arrays.stream(roundsString.split(";"))
                .map(GameParser::parseRound)
                .collect(Collectors.toList());

        return new Game(id, rounds);
    }

    private static int parseGameId(String gameString){
        return Integer.parseInt(CharMatcher.inRange('0', '9').retainFrom(gameString));
    }

    private static Round parseRound(String roundString){
        String[] extractionStrings = roundString.split(",");
        Map<CubeColor, Integer> ballsRevealedInRound = new HashMap<>();
        for(String extraction : extractionStrings){
            // extract all digits from the string
            int extractionCount = Integer.parseInt(CharMatcher.inRange('0', '9').retainFrom(extraction));

            // extract standard text from the string and map it to a BallColor enum
            CubeColor extractionColor = EnumUtils.getEnumIgnoreCase(CubeColor.class,
                    CharMatcher.inRange('a', 'z').retainFrom(extraction));

            ballsRevealedInRound.put(extractionColor, extractionCount);
        }
        return new Round(ballsRevealedInRound);
    }
}
