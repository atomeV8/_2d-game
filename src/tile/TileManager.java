package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;

    public TileManager(GamePanel gp){
        this.gp = gp;

        tiles = new Tile[10];
        getTileImage();
    }

    public void getTileImage(){
        try{
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read((getClass().getResourceAsStream("/tiles/grass.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
