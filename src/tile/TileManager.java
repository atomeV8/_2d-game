package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel gp;
    public Tile[] tilesTypes;
    public int mapTileNum[][];
    String mapPath = "/res/maps/world002.txt";
    int renderDistance = 1;

    public TileManager(GamePanel gp){
        this.gp = gp;

        tilesTypes = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap(mapPath);
    }

    public void loadMap(String path){
        try{
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int number = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = number;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getTileImage(){
        try{
            tilesTypes[0] = new Tile();
            tilesTypes[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png"));

            tilesTypes[1] = new Tile();
            tilesTypes[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/brick.png"));
            tilesTypes[1].collision = true;

            tilesTypes[2] = new Tile();
            tilesTypes[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png"));
            tilesTypes[2].collision = true;

            tilesTypes[3] = new Tile();
            tilesTypes[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/dirt.png"));

            tilesTypes[4] = new Tile();
            tilesTypes[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tree.png"));
            tilesTypes[4].collision = true;

            tilesTypes[5] = new Tile();
            tilesTypes[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/sand.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int worldCol = 0, worldRow = 0;
        int imagesRendered = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY  + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(tilesTypes[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                imagesRendered++;
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
        //System.out.println("Images rendered during frame : " + imagesRendered);
    }
}
