using System.Text.RegularExpressions;

namespace AOC2023.Day3;

public class SchematicsParser{
    private static string excludedSymbolsRegex = "[0-9.]";
    private static string gearRegex = "\\*";
    private static HashSet<Tuple<int, int>> adjacencySet = new (){
        new (-1, -1), new (-1, 0), new (-1, 1),
        new (0, -1),  new (0, 1),
        new (1, -1),  new (1, 0),  new (1, 1)
    };

    public static char[][] InputToMatrix(List<string> schematicLines){
        return schematicLines.Select( l => l.ToCharArray() ).ToArray();
    }

    public static int SumSchematic(char[][] schematicMatrix){ 
        int sum = 0;
        for(int line = 0; line < schematicMatrix.Length; line++){
            for(int column = 0; column < schematicMatrix[line].Length ; column++){
                // find a number
                char current = schematicMatrix[line][column];
                if(char.IsNumber(current)){
                    int numberLength = getNumberAsString(column, schematicMatrix[line]);
                    sum += getSchematicNumberAtIndex(line, column, numberLength, schematicMatrix, excludedSymbolsRegex);
                    
                    // skip characters analyzed by the subroutine
                    column += 
                        column + numberLength < schematicMatrix[line].Length
                        ? numberLength
                        : schematicMatrix[line].Length - column;
                }
            }
        }
        return sum;
    }

    private static int getSchematicNumberAtIndex(int row, int startingColumn, int numberLength,  char[][] schematic, string excludedSymbolsRegex){ 
        // sum goes from startingColumn to endColumn - 1
        bool symbolFound = false;
        int currentSumIndex = startingColumn;
        while(!symbolFound && currentSumIndex < startingColumn + numberLength){
            foreach(var adjacent in adjacencySet){
                try{
                    if(IsSymbol(schematic[row + adjacent.Item1][currentSumIndex + adjacent.Item2], excludedSymbolsRegex)){
                        symbolFound = true;
                        break;
                    }
                }catch(IndexOutOfRangeException){
                    // if a symbol out of range is checked, just keep going
                    continue;
                }
            } 
            currentSumIndex++;
        }

        // if a symbol was found, return the int value of the parsed number string
        return symbolFound 
            ? int.Parse( new (schematic[row]
                .Skip(startingColumn)
                .Take(numberLength)
                .ToArray()) )
            : 0;
    }

    private static int getNumberAsString(int startingColumn, char[] schematicRow){        
        return schematicRow
            .Skip(startingColumn)
            .TakeWhile(char.IsNumber)
            .Count();    
    }


    // anything not in regex is considered a symbol
    private static bool IsSymbol(char c, string nonSymbolsRegex){
        return !Regex.Match("" + c + "", nonSymbolsRegex).Success;
    }

    public static int SumGearRatios(char[][] schematicMatrix){
        int gearRatioSum = 0;
        for(int i = 0; i < schematicMatrix.Length; i++) {
            for(int j = 0; j < schematicMatrix[i].Length; j++){
                if( IsGearSymbol(schematicMatrix, i, j, gearRegex) ){
                    var gearNumberPositions = findAdjacentNumberIndexes(schematicMatrix, i, j, 2);
 
                    // sum the product of the gear numbers, if there are any, otherwise 0
                    gearRatioSum += gearNumberPositions.Count > 0
                    ? gearNumberPositions.Aggregate( 1, 
                        (a, b) => a *= GetNumberFromIndex(schematicMatrix, b.Item1, b.Item2))
                    : 0;
                }
            }
        }
        return gearRatioSum;
    }

    private static bool IsGearSymbol(char[][] schematicMatrix , int row, int column, string gearRegex){
        char c = schematicMatrix[row][column];
        return Regex.Match("" + c + "", gearRegex).Success;
    }

    static List<Tuple<int, int>> findAdjacentNumberIndexes(char[][] schematicMatrix, int row, int column, int maxAdjacentNumbers){
        List<Tuple<int, int>> adjacentNumberPositions = new();
        
        // iterate adjacent characters
        Tuple<int, int>? previousAdjacency = null; 
        foreach(var currentAdjacency in adjacencySet){
            char currentCharacter; 
            try{ 
                currentCharacter = schematicMatrix[row + currentAdjacency.Item1][column + currentAdjacency.Item2]; 
            } catch (IndexOutOfRangeException){ continue; }

            if(char.IsDigit(currentCharacter)){ 
                if(adjacentNumberPositions.Count > 0){
                    // if the new character belongs to the same number as before, skip it
                    if(
                        previousAdjacency != null 
                        && previousAdjacency.Item1 == currentAdjacency.Item1  // same row
                        && (
                            currentAdjacency.Item2 - 1 == previousAdjacency.Item2 // previous column
                            || currentAdjacency.Item2 + 1 == currentAdjacency.Item2 // next column
                        )
                    ){
                        previousAdjacency = currentAdjacency; 
                        continue; 
                    }
                } 
                adjacentNumberPositions.Add(new(row + currentAdjacency.Item1, column + currentAdjacency.Item2));
                previousAdjacency = currentAdjacency;

                // if the limit is exceeded, return null
                if(adjacentNumberPositions.Count > maxAdjacentNumbers){
                    break;
                }
            }
        } 
        // return the list of adjacent positions
        // if it contains the expected adjacent number count
        // otherwise an empty list
        return adjacentNumberPositions.Count == maxAdjacentNumbers
            ? adjacentNumberPositions
            : new();
    }

    // starting from a character that is a digit, find the entire number and return its int value.
    // return 0 if the starting character is not a digit.
    private static int GetNumberFromIndex(char[][] schematicMatrix, int row, int column){
        
        char[] numberRow = schematicMatrix[row];
        int columns = numberRow.Length;
        int numberStart = column;
        int numberEnd = column;

        if(!char.IsDigit(numberRow[column])) return 0;

        string numberString = numberRow[column].ToString();
        
        // find all digits to the right
        while( --numberStart >= 0 && char.IsDigit(numberRow[numberStart]) ){
            numberString = numberRow[numberStart] + numberString;
        }

        // find all digits to the left
        while( ++numberEnd <= columns - 1 && char.IsDigit(numberRow[numberEnd]) ){
            numberString = numberString + numberRow[numberEnd];
        }

        return int.Parse(numberString);
    }
}