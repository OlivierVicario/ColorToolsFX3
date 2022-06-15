package vic.colortools.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ViewerState extends ToolState{


    @Override
    public void create() {

    }

    @Override
    public ToolState read() {
        return null;
    }

    @Override
    public ToolState duplicate() {
        return null;
    }

    @Override
    public void delete() {

    }

    public ViewerState(String tool, ArrayList<Colour> colours, Timestamp timeStamp) {
        super();
        this.tool = tool;
        ArrayList<String> hexs = new ArrayList<String>();
        for(Colour colour:colours){
            hexs.add(colour.hex);
        }
        this.hexs = hexs;
        this.timestamp = timeStamp;
    }
}
