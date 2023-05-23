package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    protected GamePanel gp;
    public int worldX, worldY;
    public int screenX, screenY;
    public int speed;
    //IMAGES
    BufferedImage[] rightAnimation = new BufferedImage[7];
    BufferedImage[] leftAnimation = new BufferedImage[7];
    BufferedImage[] upAnimation = new BufferedImage[7];
    BufferedImage[] downAnimation = new BufferedImage[7];
    public BufferedImage idle;
    public int indexImage = 0;

<<<<<<< Updated upstream
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
=======
>>>>>>> Stashed changes
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle hitbox = new Rectangle(0, 0, 48, 48);
    public int hitboxDefaultX, hitboxDefaultY;
    public boolean collisionOn = false;
    boolean moving = false;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public BufferedImage setup(String imageName, String entityType){
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;
        try{
            scaledImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/" + entityType + "/" + imageName + ".png")));
            scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);
            return scaledImage;
        }catch (IOException e){
            e.printStackTrace();
        }
        return scaledImage;
    }

    public void draw(Graphics2D graphics){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY  + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
            BufferedImage image = null;
            indexImage++;

            switch (direction) {
                case "up" -> {
                    if(indexImage >= leftAnimation.length){
                        indexImage = 0;
                    }
                    if(moving && !collisionOn){
                        image = upAnimation[indexImage];
                    }
                    else{
                        image = idle;
                    }
                }
                case "down" -> {
                    if(indexImage >= leftAnimation.length){
                        indexImage = 0;
                    }
                    if(moving && !collisionOn){
                        image = downAnimation[indexImage];
                    }
                    else{
                        image = idle;
                    }
                }
                case "left" -> {
                    if(indexImage >= leftAnimation.length){
                        indexImage = 0;
                    }
                    if(moving && !collisionOn){
                        image = leftAnimation[indexImage];
                    }
                    else{
                        image = idle;
                    }
                }
                case "right" -> {
                    if (indexImage >= rightAnimation.length) {
                        indexImage = 0;
                    }
                    if (moving && !collisionOn) {
                        image = rightAnimation[indexImage];
                    } else {
                        image = idle;
                    }
                }
            }
            graphics.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }

    }
}
