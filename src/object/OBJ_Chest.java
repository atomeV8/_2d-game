package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{
    public OBJ_Chest(GamePanel gp){
        this.gp = gp;
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/chest.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
