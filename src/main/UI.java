package main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI{
    GamePanel gp;
    Graphics2D g2;
    Font rainyhearts;
    public boolean showMessage = false;
    public String message = "";
    //public boolean gameFinished = false;
    public String dialogueText;
    public UI(GamePanel gp){
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/res/fonts/rainyhearts.ttf");
            assert is != null;
            rainyhearts = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String text){
        message = text;
        showMessage = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(rainyhearts);
        g2.setColor(Color.white);

        switch(gp.gameState){
            case PLAY -> {

            }
            case PAUSE -> {
                drawPauseScreen();
            }
            case DIALOGUE -> {
                drawDialogueScreen();
            }
            default -> {

            }
        }
    }

    private void drawDialogueScreen() {
        int x = gp.tileSize * 2, y = gp.tileSize / 2, width = gp.screenWidth - (gp.tileSize * 4), height = gp.tileSize * 4;
        drawWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize - 10;
        y += gp.tileSize;

        for (String line : dialogueText.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawWindow(int x, int y, int width, int height){
        g2.setColor(new Color(0,0,0, 200));
        g2.fillRoundRect(x, y, width, height, 35, 35);

        g2.setColor(new Color(255,255,255));
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x, y, width, height, 35, 35);
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
