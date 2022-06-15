package vic.colortools.colorspace;

public class RGB {

	public static String rgbToHex(int[] rgb) {
		return String.format("#%02X%02X%02X", rgb[0], rgb[1], rgb[2]);
	}

	public static int[] hexToRgb(String hex) {
		int[] rgb = new int[3];
		rgb[0] = Integer.valueOf(hex.substring(1, 3), 16);
		rgb[1] = Integer.valueOf(hex.substring(3, 5), 16);
		rgb[2] = Integer.valueOf(hex.substring(5, 7), 16);
		return rgb;
	}

}
