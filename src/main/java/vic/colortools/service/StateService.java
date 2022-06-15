package vic.colortools.service;

import vic.colortools.dao.StateDAO;
import vic.colortools.model.State;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class StateService {
    public StateDAO stateDAO = new StateDAO();

    public State saveState(int idTool){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        State state = new State(timestamp,idTool);
        stateDAO.saveState(state);
        return state;//y a t il l'id ?

    }

    public State getStateById(int id){
        return stateDAO.getStateById(id);
    }
    public State getStateByTimeStamp(Timestamp timestamp){
        return stateDAO.getStateByTimeStamp(timestamp);
    }
    public ArrayList<State> getStatesByIdTool(int id){
       return stateDAO.getStatesByIdTool(id);
    }
}
