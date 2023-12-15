package day1;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import utils.InputLoader;

public class Day1Test {
    @Test
    public void day1Part1(){
        List<String> inputs = InputLoader.readFromFile("inputs/day1/input.txt");
        assertEquals(54916, Day1.part1(inputs));
        assertEquals(54728, Day1.part2(inputs));
    }
}
