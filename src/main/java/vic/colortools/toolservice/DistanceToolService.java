package vic.colortools.toolservice;

import vic.colortools.colorspace.RGB;
import vic.colortools.model.Colour;
import vic.colortools.model.Parameter;
import vic.colortools.model.State;
import vic.colortools.model.Tool;
import vic.colortools.service.ColourService;
import vic.colortools.service.ParameterService;
import vic.colortools.service.StateService;
import vic.colortools.service.ToolService;

import java.util.ArrayList;

import static vic.colortools.colorspace.JzAzBz.jabToRgb;
import static vic.colortools.colorspace.JzAzBz.rgbToJab;

public class DistanceToolService {

ToolService toolService = new ToolService();
	StateService stateService = new StateService();
	ParameterService parameterService = new ParameterService();
	ColourService colourService = new ColourService();

	// x 10 000
	double minJ = 0;
	double maxJ = 175.80214189734804;
	double mina = -162.48471330964597;
	double maxa = 172.17445922948366;
	double minb = -249.5000648070522;
	double maxb = 208.0003033803981;
	final double minc = 0.0, maxc = 249.76987673046168;
	final double minh = 0.0, maxh = 360.0;

	public ArrayList<String> findHexs(double distanceRef, int[] rgb, double step, double gap) {
		//double gap = 10;// a exprimer en E
		ArrayList<String> hexs = new ArrayList<String>();
		double[] jabColor = rgbToJab(rgb);

		for (double j = minJ; j < maxJ; j += step) {
			for (double a = mina; a < maxa; a += step) {
				for (double b = minb; b < maxb; b += step) {
					double d = Math.sqrt(Math.pow(j - jabColor[0] * 10000.0, 2) + Math.pow(a - jabColor[1] * 10000.0, 2)
							+ Math.pow(b - jabColor[2] * 10000.0, 2));
					if ((d < (distanceRef + gap)) && (d > (distanceRef - gap))) {						
						double[] jab = { j / 10000.0, a / 10000.0, b / 10000.0 };
						rgb = jabToRgb(jab);
						if (rgb != null) {
							if(rgb[0]+ rgb[1]+ rgb[2]>0) hexs.add(RGB.rgbToHex(rgb));
						}
					}
				}
			}
		}
		return hexs;
	}

	public void saveState(double distanceRef, int[] rgb, double step, double gap) {

		Tool tool = toolService.getToolByName("distance");
		int idtool = tool.getIdtool();
		//System.out.println(tool.toString());
		State state = stateService.saveState(idtool);
		//System.out.println(state.toString());
		parameterService.saveParameter(new Parameter("distanceRef", String.valueOf(distanceRef), state.getIdState()));
		parameterService.saveParameter(new Parameter("hex", RGB.rgbToHex(rgb), state.getIdState()));
		parameterService.saveParameter(new Parameter("step", String.valueOf(step), state.getIdState()));
		parameterService.saveParameter(new Parameter("gap", String.valueOf(gap), state.getIdState()));

		ArrayList<String> hexs = findHexs(distanceRef,rgb,step,gap);
		for (String hex : hexs) {
			Colour colour = new Colour(hex, state.getIdState());
			colourService.saveColor(colour);
		}
	}

	public void updateCurrentToolState(){}
}
