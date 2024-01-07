public class InputReader
{
    public static List<string> readInput(string path)
    {
        List<string> inputs = new();
        try
        {
            using StreamReader reader = new StreamReader(path);
            string? line;
            while ((line = reader.ReadLine()) != null)
            {
                inputs.Add(line);
            }
        }
        catch (FileNotFoundException)
        {
            Console.WriteLine("File not found");
        }
        catch (IOException ex)
        {
            Console.WriteLine($"An error occurred while reading the file: {ex.Message}");
        }
        return inputs;
    }
}