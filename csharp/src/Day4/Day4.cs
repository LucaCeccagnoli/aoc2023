using System.Collections;
using AOC2023.Day3;
using AOC2023.Day4;
using AOC2023.Utils;

public class Day4 : Day
{
    public static void Run(){
        List<string> inputs = InputUtils.readInput("inputs/day4.txt");
        List<ScratchCard> cards = inputs.Select( i => new ScratchCard(i)).ToList();
        part1(cards);
        part2(cards);
    }

    public static void part1(List<ScratchCard> cards){
        int cardSum = cards.Aggregate(0, (sum, card) => sum + card.Points );
        Console.WriteLine("sum of card values: " + cardSum + ", expected: " + 21959);
    }

    public static void part2(List<ScratchCard> cards){
        
    }
}