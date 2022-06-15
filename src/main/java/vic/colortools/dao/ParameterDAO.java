package vic.colortools.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vic.colortools.config.DataBaseConfig;
import vic.colortools.constants.DBConstants;
import vic.colortools.model.Parameter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ParameterDAO {
    private static final Logger logger = LogManager.getLogger("ParameterDAO");

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();

    public Parameter getParameterById(int i) {
        Connection con = null;
        Parameter parameter =null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.GET_PARAMETER_BY_ID);
            ps.setInt(1, i);//1 est le premier indice des variables
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                parameter = new Parameter(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception ex){
            logger.error("Error getting Parameter by id",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return parameter;
    }



    public boolean saveParameter(Parameter Parameter){
        Connection con = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.SAVE_PARAMETER);
            ps.setString(1,Parameter.getName());
            ps.setString(2,Parameter.getValue());
            ps.setInt(3,Parameter.getIdState());
            return ps.execute();
        }catch (Exception ex){
            logger.error("Error saving Parameter",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            return false;
        }
    }

    public ArrayList<Parameter> getParametersByIdState(int i) {
        Connection con = null;
        Parameter parameter=null;
        ArrayList<Parameter> parameters=null;

        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.GET_PARAMETERS_BY_IDSTATE);
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            parameters = new ArrayList<Parameter>();
            while(rs.next()){
                parameter = new Parameter(rs.getInt(1),rs.getString(2),rs.getString(3),i);
                parameters.add(parameter);
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception ex){
            logger.error("Error getting Parameters by idTool",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return parameters;
    }
}
