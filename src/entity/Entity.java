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
    public int nbSpritesForAnimation;

    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle hitbox = new Rectangle(0, 0, 48, 48);
    public int hitboxDefaultX, hitboxDefaultY;
    public boolean collisionOn = false;
    boolean moving = false;
    public int actionLockTime = 0, actionInterval = 120;


    String dialogues[] = new String[20];
    int dialogueIndex = 0;

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

    public void setAction(){

    }

    public void update(){
        setAction();

        collisionOn = false;
        gp.CC.checkTile(this);
        gp.CC.checkObject(this, false);
        gp.CC.checkPlayer(this);

        if(!collisionOn){
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
            spriteCounter++;
        }
    }

    public void draw(Graphics2D graphics){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY  + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
            BufferedImage image = null;

            while(image == null){
                switch (direction) {
                    case "up" -> {
                        if(indexImage >= leftAnimation.length){
                            indexImage = 0;
                        }
                        if(!collisionOn){
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
                        if(!collisionOn){
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
                        if(!collisionOn){
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
                        if (!collisionOn) {
                            image = rightAnimation[indexImage];
                        } else {
                            image = idle;
                        }
                    }
                    default -> {
                        image = idle;
                    }
                }
                if(image == null){
                    indexImage = 0;
                }
            }
            graphics.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            if(spriteCounter > 10){
                indexImage++;
                spriteCounter = 0;
            }
        }
    }

    public void getNumberOfSprites(){
        for (BufferedImage sprite: downAnimation //all directions have to have the same amount of sprites, or it will look weird
             ) {
            if(sprite != null)
                nbSpritesForAnimation++;
        }
    }

    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.dialogueText = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
            case "up" -> this.direction = "down";
            case "down" -> this.direction = "up";
            case "left" -> this.direction = "right";
            case "right" -> this.direction = "left";
        }
    }
}
