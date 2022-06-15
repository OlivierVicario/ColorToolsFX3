package vic.colortools.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vic.colortools.config.DataBaseConfig;
import vic.colortools.constants.DBConstants;
import vic.colortools.model.State;

import java.sql.*;
import java.util.ArrayList;

public class StateDAO {
    private static final Logger logger = LogManager.getLogger("StateDAO");

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();

    public State getStateById(int i) {
        Connection con = null;
        State State = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.GET_STATE_BY_ID);
            ps.setInt(1, i);//1 est le premier indice des variables
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                State = new State(i, rs.getTimestamp(1), rs.getInt(2));
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        } catch (Exception ex) {
            logger.error("Error getting State by id", ex);
        } finally {
            dataBaseConfig.closeConnection(con);
        }
        return State;
    }

    public State getStateByTimeStamp(Timestamp timestamp) {
        Connection con = null;
        State State = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.GET_STATE_BY_TIMESTAMP);
            ps.setTimestamp(2, timestamp);//1 est le premier indice des variables
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                State = new State(rs.getInt(1), rs.getTimestamp(2), rs.getInt(3));
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        } catch (Exception ex) {
            logger.error("Error getting State by id", ex);
        } finally {
            dataBaseConfig.closeConnection(con);
        }
        return State;
    }

    public boolean saveState(State state) {
        Connection con = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.SAVE_STATE, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, state.getTimestamp());
            ps.setInt(2, state.getIdTool());
            ps.execute();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    state.setIdState(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating state failed, no ID obtained.");
                }
            }
        } catch (Exception ex) {
            logger.error("Error saving State", ex);
        } finally {
            dataBaseConfig.closeConnection(con);
            return false;
        }
    }

    public ArrayList<State> getStatesByIdTool(int i) {
        Connection con = null;
        State state = null;
        ArrayList<State> states = null;

        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.GET_STATES_BY_IDTOOL);
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            states = new ArrayList<State>();
            while (rs.next()) {
                state = new State(rs.getInt(1), rs.getTimestamp(2), rs.getInt(3));//***************
                states.add(state);
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        } catch (Exception ex) {
            logger.error("Error getting States by idTool", ex);
        } finally {
            dataBaseConfig.closeConnection(con);
        }
        return states;
    }
}
