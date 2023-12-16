package day2;

import org.junit.Test;
import utils.InputLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Day2Test {
    @Test
    public void Day2Part1(){
        List<String> inputs = InputLoader.readFromFile("inputs/day2.txt");
        Map<CubeColor, Integer> bag = new HashMap<>();
        bag.put(CubeColor.RED, 12);
        bag.put(CubeColor.GREEN, 13);
        bag.put(CubeColor.BLUE, 14);

        assertEquals(2913, Day2.part1(bag, inputs));
    }

    @Test
    public void Day2Part2(){
        List<String> inputs = InputLoader.readFromFile("inputs/day2.txt");
        assertEquals(55593, Day2.part2(inputs));
    }
}
