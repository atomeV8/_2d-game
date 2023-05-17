package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class OBJ_Door extends SuperObject{
    public OBJ_Door(GamePanel gp){
        this.gp = gp;
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
