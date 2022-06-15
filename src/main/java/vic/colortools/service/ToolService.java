package vic.colortools.service;

import vic.colortools.dao.ToolDAO;
import vic.colortools.model.Tool;

import java.util.ArrayList;

public class ToolService {
    public ToolDAO toolDAO = new ToolDAO();

    public void saveTool(String name){
        Tool tool = new Tool(name);
        toolDAO.saveTool(tool);
    }

    public Tool getToolByID(int i){
        Tool tool = toolDAO.getToolById(i);
        return tool;
    }

    public Tool getToolByName(String name){
        Tool tool = toolDAO.getToolByName(name);
        return tool;
    }

    public ArrayList<Tool> getTools(){
        return toolDAO.getTools();
    }
}
