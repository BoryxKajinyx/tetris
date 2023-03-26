package tetris;

import java.awt.Color;

public class Settings {
    public Settings() {
        Colors=defaultColors;
        Level=true;
    }
    private Color[] Colors;
    private Color[] defaultColors={
        new Color(0, 0, 0), new Color(204, 102, 102),
        new Color(102, 204, 102), new Color(0, 255, 255),
        new Color(204, 204, 102), new Color(204, 102, 204),
        new Color(102, 204, 204), new Color(218, 170, 0)
    };
    private boolean Level;
    public Color[] getColors() {
        return Colors;
    }
    public void setColors(Color[] colors) {
        Colors = colors;
    }
    public boolean isLevel() {
        return Level;
    }
    public void setLevel(boolean level) {
        Level = level;
    }
    
}
