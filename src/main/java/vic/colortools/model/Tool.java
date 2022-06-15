package vic.colortools.model;

public class Tool {
    int idtool;
    String name;

    public int getIdtool() {
        return idtool;
    }

    public void setIdtool(int idtool) {
        this.idtool = idtool;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tool(String name) {
        this.name = name;
    }

    public Tool(int idtool, String name) {
        this.idtool = idtool;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "idtool=" + idtool +
                ", name='" + name + '\'' +
                '}';
    }
}
