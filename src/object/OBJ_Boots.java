package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends SuperObject{
    public OBJ_Boots(GamePanel gp){
        this.gp = gp;
        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/boots.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
