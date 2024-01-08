public class StringMonad {
    private string value;

    public StringMonad(string value){
        this.value = value;
    }
    
    // takes a string, splits it according to a separator, and can run actions on the individual substrings,
    // specified by their index
    public StringMonad[] splitAndRun(string separator, List< (int, Action<string>) > indexActions){
        string[] substrings = value.Split(separator);

        indexActions.ForEach(a => a.Item2(substrings[a.Item1]));

        return substrings.Select(s => new StringMonad(s)).ToArray();
    }
}