package day1;
import java.util.List;
import java.util.Map;
import utils.Trie;


public class Day1{
    public static int part1(List<String> inputs){
        // empty maps means only numerical digits will ever be returned
        Map<String, String> digitMap = Map.of(
                "1", "1"
                , "2", "2"
                , "3", "3"
                , "4", "4"
                , "5", "5"
                , "6", "6"
                , "7", "7"
                , "8", "8"
                , "9", "9"
        );
        return run(inputs, digitMap);
    }

    public static int part2(List<String> inputs){
        Map<String, String> digitMap = Map.of(
                "one", "1"
                , "two", "2"
                , "three", "3"
                , "four", "4"
                , "five", "5"
                , "six", "6"
                , "seven", "7"
                , "eight", "8"
                , "nine", "9"
        );
        return run(inputs, digitMap);
    }

    private static int run(List<String> inputs, Map<String, String> digitMap){
        Trie digitTrie = new Trie();
        digitMap.keySet().forEach(digitTrie::insert);
        digitMap.values().forEach(digitTrie::insert);

        // for each line of input
        return inputs.stream().reduce(
            0,
            (sum, calibration) -> {
                // sum first and last digit found in a word
                return sum + Integer.parseInt(
                        getFromDigitMap(digitTrie.findAnyFirst(calibration), digitMap) +
                        getFromDigitMap(digitTrie.findAnyFirstReverse(calibration), digitMap)
                );
            },
            Integer::sum);
    }

    private static String getFromDigitMap(String digit, Map<String, String> digitMap){
        return digitMap.getOrDefault(digit, digit);
    }
}

