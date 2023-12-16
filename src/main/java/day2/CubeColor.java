package day2;

public enum CubeColor {
    RED("red"),
    BLUE("blue"),
    GREEN("green");

    private final String lowerCase;

    CubeColor(String value){
        this.lowerCase = value;
    }

    public String getLowerCase(){return this.lowerCase; }
}