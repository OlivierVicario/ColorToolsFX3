package vic.colortools;

import vic.colortools.model.ToolState;

public class Context {
    private final static Context instance = new Context();

    public static Context getInstance() {
        return instance;
    }

    private ToolState toolState;

    public ToolState currentToolState() {
        return toolState;
    }

    public void setToolState(ToolState toolState) {
        this.toolState = toolState;
    }
}
