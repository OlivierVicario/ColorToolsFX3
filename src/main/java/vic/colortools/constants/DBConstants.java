package vic.colortools.constants;

public class DBConstants {
    public static final String GET_TOOL_BY_ID = "select * from tool where IDTOOL = ?";
    public static final String UPDATE_TOOL_BY_ID = "update tool set NAME=? where IDTOOL = ?";
    public static final String SAVE_TOOL = "insert into tool(NAME) values(?)";
    public static final String GET_TOOL_BY_NAME = "select * from tool where NAME = ?";
    public static final String GET_TOOLS = "select * from tool";

    public static final String GET_STATE_BY_ID = "select * from state where IDSTATE = ?";
    public static final String UPDATE_STATE_BY_ID = "update state set DATETIME=?, IDTOOL=? where IDSTATE = ?";
    public static final String SAVE_STATE = "insert into state(DATETIME,IDTOOL) values(?,?)";
    public static final String GET_STATES_BY_IDTOOL = "select * from state where IDTOOL = ?";
    public static final String GET_STATE_BY_TIMESTAMP = "select * from state where TIMESTAMP = ?";

    public static final String GET_PARAMETER_BY_ID = "select * from parameter where IDPARAMETER = ?";
    public static final String UPDATE_PARAMETER_BY_ID = "update parameter set DATETIME=?, IDSTATE=? where IDPARAMETER = ?";
    public static final String SAVE_PARAMETER = "insert into parameter(NAME,VALUE,IDSTATE) values(?,?,?)";
    public static final String GET_PARAMETERS_BY_IDSTATE = "select * from parameter where IDSTATE = ?";

    public static final String GET_COLOUR_BY_ID = "select * from COLOR where IDCOLOR = ?";
    public static final String UPDATE_COLOUR_BY_ID = "update COLOR set HEX=?, IDSTATE=? where IDCOLOR = ?";
    public static final String SAVE_COLOUR = "insert into COLOR(HEX,IDSTATE) values(?,?)";
    public static final String GET_COLOURS_BY_IDSTATE = "select * from COLOR where IDSTATE = ?";
}
