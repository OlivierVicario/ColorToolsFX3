package vic.colortools.model;

import java.sql.Timestamp;
public class State {
    int idState;
    Timestamp timestamp;
    int idTool;

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getIdTool() {
        return idTool;
    }

    public void setIdTool(int idTool) {
        this.idTool = idTool;
    }

    public State(int idState, Timestamp timestamp, int idTool) {
        this.idState = idState;
        this.timestamp = timestamp;
        this.idTool = idTool;
    }

    public State(Timestamp timestamp, int idTool) {
        this.timestamp = timestamp;
        this.idTool = idTool;
    }

    @Override
    public String toString() {
        return "State{" +
                "idState=" + idState +
                ", timestamp=" + timestamp +
                ", idTool=" + idTool +
                '}';
    }


}
