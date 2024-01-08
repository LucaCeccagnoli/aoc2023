

using System.Collections;
using AOC2023.Day3;

public class Program
{
    private static readonly Dictionary<int, Action> days = new()
    {
        { 3, Day3.Run }, {4, Day4.Run}
    };

    public static void Main(string[] args)
    {
        foreach(string day in args)
        {
            try{
                days[int.Parse(day)]();
            }catch(KeyNotFoundException){
                Console.WriteLine("day " + day  + " does not exist lmao");
            }
        }
    }
} 