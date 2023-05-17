package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI{
    GamePanel gp;
    Font arial40, arial80B;
    BufferedImage image;
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
        OBJ_Key key = new OBJ_Key(gp);
        image = key.image;
    }

    public void showMessage(String text){
        message = text;
        showMessage = true;
    }

    public void draw(Graphics2D g2){
        if(!gameFinished){
            g2.setFont(arial40);
            g2.setColor(Color.white);
            g2.drawImage(image, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.nbKeys, 74, 65);

            //TIME
            playTime += (double)1/60;
            g2.drawString("Time: " + df.format(playTime), gp.tileSize * 11, 65);

            //MESSAGE
            if(showMessage){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
                messageLifespan++;
                if(messageLifespan > 120){
                    messageLifespan = 0;
                    showMessage = false;
                }
            }
        }else{
            g2.setFont(arial40);
            g2.setColor(Color.white);
            String text;
            int textLength;
            int x;
            int y;
            text = "YEEPEE YOU DID IT!!! You finished the game";
            textLength =  (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenWidth / 2 - gp.tileSize * 3;
            g2.drawString(text, x, y);
            text = "Time = " + df.format(playTime);
            textLength =  (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenWidth / 2 + gp.tileSize;
            g2.drawString(text, x, y);

            g2.setFont(arial80B);
            g2.setColor(Color.yellow);
            text = "CONGRATS";
            textLength =  (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenWidth / 2 + 10;
            g2.drawString(text, x, y);

            gp.gameThread = null;
        }

    }
}
