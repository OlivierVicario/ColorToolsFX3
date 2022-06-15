package vic.colortools.model;

public class Colour {
    int idcolour;
    String hex;
    int idstate;

    public int getIdcolour() {
        return idcolour;
    }

    public void setIdcolour(int idcolour) {
        this.idcolour = idcolour;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public int getIdstate() {
        return idstate;
    }

    public void setIdstate(int idstate) {
        this.idstate = idstate;
    }

    public Colour(int idcolour, String hex, int idstate) {
        this.idcolour = idcolour;
        this.hex = hex;
        this.idstate = idstate;
    }

    public Colour(String hex, int idstate) {
        this.hex = hex;
        this.idstate = idstate;
    }

    @Override
    public String toString() {
        return "Color{" +
                "idcolor=" + idcolour +
                ", hex='" + hex + '\'' +
                ", idstate=" + idstate +
                '}';
    }
}
