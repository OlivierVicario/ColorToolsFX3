package vic.colortools.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vic.colortools.config.DataBaseConfig;
import vic.colortools.constants.DBConstants;
import vic.colortools.model.Parameter;
import vic.colortools.model.Tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ToolDAO {
    private static final Logger logger = LogManager.getLogger("ToolDAO");

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();

    public Tool getToolById(int i) {
        Connection con = null;
        Tool Tool=null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.GET_TOOL_BY_ID);
            ps.setInt(1, i);//1 est le premier indice des variables
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Tool = new Tool(rs.getInt(1),rs.getString(2));
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception ex){
            logger.error("Error getting Tool by id",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return Tool;
    }

    public boolean saveTool(Tool tool){
        Connection con = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.SAVE_TOOL);
            ps.setString(1,tool.getName());
            return ps.execute();
        }catch (Exception ex){
            logger.error("Error saving tool",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            return false;
        }
    }

    public Tool getToolByName(String name) {
        Connection con = null;
        Tool Tool=null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.GET_TOOL_BY_NAME);
            ps.setString(1, name);//1 est le premier indice des variables
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Tool = new Tool(rs.getInt(1),rs.getString(2));
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception ex){
            logger.error("Error getting Tool by name",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return Tool;
    }

    public ArrayList<Tool> getTools() {
        Connection con = null;
        Tool tool=null;
        ArrayList<Tool> tools=null;

        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.GET_TOOLS);
            //ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            tools = new ArrayList<Tool>();
            if(rs.next()){
                tool = new Tool(rs.getInt(1),rs.getString(2));
                tools.add(tool);
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception ex){
            logger.error("Error getting tools",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return tools;
    }
}