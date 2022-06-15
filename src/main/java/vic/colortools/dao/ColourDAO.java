package vic.colortools.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vic.colortools.config.DataBaseConfig;
import vic.colortools.constants.DBConstants;
import vic.colortools.model.Colour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ColourDAO {
    private static final Logger logger = LogManager.getLogger("ColorDAO");

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();

    public Colour getColorById(int i) {
        Connection con = null;
        Colour colour =null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.GET_COLOUR_BY_ID);
            ps.setInt(1, i);//1 est le premier indice des variables
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                colour = new Colour(rs.getInt(1),rs.getString(2),rs.getInt(3));
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception ex){
            logger.error("Error getting Color by id",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return colour;
    }

    public boolean saveColor(Colour Colour){
        Connection con = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.SAVE_COLOUR);
            ps.setString(1, Colour.getHex());
            ps.setInt(2, Colour.getIdstate());
            return ps.execute();
        }catch (Exception ex){
            logger.error("Error saving Colour",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
            return false;
        }
    }

    public ArrayList<Colour> getColorsByIdState(int i) {
        Connection con = null;
        Colour colour =null;
        ArrayList<Colour> colours =null;

        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.GET_COLOURS_BY_IDSTATE);
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            colours = new ArrayList<Colour>();
            while(rs.next()){
                colour = new Colour(rs.getInt(1),rs.getString(2),rs.getInt(3));
                colours.add(colour);
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        }catch (Exception ex){
            logger.error("Error getting Colours by idTool",ex);
        }finally {
            dataBaseConfig.closeConnection(con);
        }
        return colours;
    }
}

