package vic.colortools.model;

public class Parameter {
    int idparameter;
    String name;
    String value;
    int idState;

    public int getIdparameter() {
        return idparameter;
    }

    public void setIdparameter(int idparameter) {
        this.idparameter = idparameter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

    public Parameter(int idparameter, String name, String value, int idState) {
        this.idparameter = idparameter;
        this.name = name;
        this.value = value;
        this.idState = idState;
    }

    public Parameter(String name, String value, int idState) {
        this.name = name;
        this.value = value;
        this.idState = idState;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "idparameter=" + idparameter +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", idState=" + idState +
                '}';
    }
}
