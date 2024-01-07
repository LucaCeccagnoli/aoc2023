using System.Diagnostics;
using AOC2023.Day3;

public interface Day{
    abstract static void Run();
}

public class Day3 : Day
{
    public static void Run(){
        List<string> inputs = InputReader.readInput("inputs/day3.txt");
        char[][] schematic = SchematicsParser.InputToMatrix(inputs);
        part1(schematic);
        part2(schematic);
    }

    public static void part1(char[][] schematic){
        Console.WriteLine("schematics sum: " + SchematicsParser.SumSchematic(schematic)
        + ", should be: "  + 532445);
    }

    public static void part2(char[][] schematic){
        Console.WriteLine("gear ratio sum: " + SchematicsParser.SumGearRatios(schematic)
        + ", should be: 79842967");
    }
}