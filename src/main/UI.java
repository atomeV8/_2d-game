package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI{
    GamePanel gp;
    Graphics2D g2;
    Font arial40, arial80B;
    public boolean showMessage = false;
    public String message = "";
    int messageLifespan = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat df = new DecimalFormat("#0.00");
    public UI(GamePanel gp){
        this.gp = gp;
        arial40 = new Font("Arial", Font.PLAIN, 40);
        arial80B = new Font("Arial", Font.BOLD, 80);
<<<<<<< Updated upstream
        OBJ_Key key = new OBJ_Key();
        image = key.image;
=======
>>>>>>> Stashed changes
    }

    public void showMessage(String text){
        message = text;
        showMessage = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial40);
        g2.setColor(Color.white);

        switch(gp.gameState){
            case PLAY -> {

            }
            case PAUSE -> {
                drawPauseScreen();
            }
            default -> {

            }
        }
    }

    public void drawPauseScreen(){
        //g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F)); pour changer la taille du texte
        String text = "GAME PAUSED";
        int x = getCenterXPositionForText(text), y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public int getCenterXPositionForText(String text){
        return gp.screenWidth / 2 - (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth() / 2;
    }
}
