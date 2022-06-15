package vic.colortools.service;

import vic.colortools.dao.ParameterDAO;
import vic.colortools.model.Parameter;

import java.util.ArrayList;

public class ParameterService {
    public ParameterDAO parameterDAO = new ParameterDAO();

    public void saveParameter(Parameter parameter){
        parameterDAO.saveParameter(parameter);
    }

    public Parameter getParameterById(int id){
        return parameterDAO.getParameterById(id);
    }

    public ArrayList<Parameter> getParametersByIdState(int id){
        return parameterDAO.getParametersByIdState(id);
    }
}
