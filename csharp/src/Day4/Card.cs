namespace AOC2023.Day4;

using AOC2023.Utils;

using System.Text.RegularExpressions;

public class ScratchCard {
    public int CardNumber {get; private set;}
    private HashSet<int> FoundNumbers {get; set;}
    private HashSet<int> WinningNumbers {get; set;}
    
    public int Points {
        get { return Convert.ToInt32( Math.Pow(2, MatchingNumbers - 1) ); }
    }

    public int MatchingNumbers {
        get { return FoundNumbers.Where(n => WinningNumbers.Contains(n)).Count();}
    }

    public ScratchCard(string cardString){
        FoundNumbers = new();
        WinningNumbers = new();

        Func<string, HashSet<int>> readNumbers = 
            s => s.Split(" ", StringSplitOptions.RemoveEmptyEntries)
                .Select(n => int.Parse(n))
                .ToHashSet();

        Func<string, int> getId = 
            s => int.Parse(Regex.Match(s.Split(":")[0], @"\d+").Value); 

        new StringMonad(cardString).splitAndRun(":", 
            new(){ (0 , cardString => CardNumber = getId(cardString) ) }  // get id from the part before the colon, work on the other one
        )[1].splitAndRun("|",
            new(){ 
                (0, foundNumbers => FoundNumbers = readNumbers(foundNumbers) ),
                (1, winningNumbers => WinningNumbers = readNumbers(winningNumbers) )
            } 
        );
    }
}