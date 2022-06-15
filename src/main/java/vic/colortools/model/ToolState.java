package vic.colortools.model;

import java.util.ArrayList;
import java.sql.*;

public abstract class ToolState {
    public String tool;
    public String[][] parameters;
    public ArrayList<String> hexs;
    public Timestamp timestamp;

    public abstract void create();
    public abstract ToolState read();
    public abstract ToolState duplicate();
    public abstract void delete();
}
