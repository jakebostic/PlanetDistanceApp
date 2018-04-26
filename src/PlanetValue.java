
public enum PlanetValue {
    Sun(0.00), Mercury(0.39), Venus(0.72), Earth(1.00), Mars(1.52),
    Jupiter(5.20), Saturn(9.58), Uranus(19.20), Neptune(30.05), Pluto(39.48);

    private double numVal;

    PlanetValue(double numVal) {
        this.numVal = numVal;
    }

    public double getNumVal() {
        return numVal;
    }

}
