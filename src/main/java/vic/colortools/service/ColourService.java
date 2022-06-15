package vic.colortools.service;

import vic.colortools.dao.ColourDAO;
import vic.colortools.model.Colour;

import java.util.ArrayList;

public class ColourService {
    public ColourDAO ColourDAO = new ColourDAO();

    public void saveColor(Colour Colour){
        ColourDAO.saveColor(Colour);
    }

    public Colour getColorById(int id){
        return ColourDAO.getColorById(id);
    }

    public ArrayList<Colour> getColorsByIdState(int id){
        return ColourDAO.getColorsByIdState(id);
    }
}
