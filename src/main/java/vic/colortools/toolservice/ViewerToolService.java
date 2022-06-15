package vic.colortools.toolservice;

import vic.colortools.dao.ColourDAO;
import vic.colortools.dao.StateDAO;
import vic.colortools.dao.ToolDAO;
import vic.colortools.model.*;

import java.util.ArrayList;

//recuperer dans une liste viewerStates la bd pour le viewer
public class ViewerToolService {

    ToolDAO toolDao = new ToolDAO();
    StateDAO stateDao = new StateDAO();
    ColourDAO colourDao = new ColourDAO();

    public ArrayList<ViewerState> createAllToolsStates() {
        ArrayList<ViewerState> viewerStates = new ArrayList<ViewerState>();

        ArrayList<Tool> tools = toolDao.getTools();
        for (Tool tool : tools) {

            ArrayList<State> states = stateDao.getStatesByIdTool(tool.getIdtool());
            for (State state : states) {
                ArrayList<Colour> colours = colourDao.getColorsByIdState(state.getIdState());
                ViewerState viewerState = new ViewerState(tool.getName(),colours,state.getTimestamp());
                viewerStates.add(viewerState);
            }
        }
        return viewerStates;
    }
}


