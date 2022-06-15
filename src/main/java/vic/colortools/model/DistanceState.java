package vic.colortools.model;

import vic.colortools.toolservice.DistanceToolService;

import java.util.ArrayList;

import static vic.colortools.colorspace.RGB.hexToRgb;

public class DistanceState extends ToolState {
    DistanceToolService service;

    public DistanceState() {
        super();
        this.tool = "distance";
        this.hexs = new ArrayList<String>();
        this.parameters = new String[4][2];
        service = new DistanceToolService();
    }


    @Override
    public void create() {
        service.saveState(Double.valueOf(parameters[0][1]), hexToRgb(parameters[1][1]),
                Double.valueOf(parameters[2][1]), Double.valueOf(parameters[3][1]));
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
}
