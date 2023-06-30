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

    public int commandNumber = 0;
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
            case TITLE_SCREEN -> {
                drawTitleScreen();
            }
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

    private void drawTitleScreen() {
        //BACKGROUND
        g2.setColor(new Color(80,70,120));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        //TEXT SETUP
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "That bullcrap game";
        int x = getCenterXPositionForText(text);
        int y = gp.tileSize * 3;

        //GAME TITLE SHADOW
        g2.setColor(new Color(0, 0, 0, 60));
        g2.drawString(text, x + 5, y + 5);

        //GAME TITLE
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //IMAGE DISPLAY
        x = gp.screenWidth / 2 - gp.tileSize;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.idle, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        //BUTTONS
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        text = "NEW GAME";
        x = getCenterXPositionForText(text);
        y += gp.tileSize * 3.5;
        g2.drawString(text, x, y);
        if(commandNumber == 0){
            g2.drawString(">", x - gp.tileSize + 10, y);
        }

        text = "LOAD GAME";
        x = getCenterXPositionForText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNumber == 1){
            g2.drawString(">", x - gp.tileSize + 10, y);
        }

        text = "QUIT GAME";
        x = getCenterXPositionForText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNumber == 2){
            g2.drawString(">", x - gp.tileSize + 10, y);
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
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F)); //pour changer la taille du texte
        String text = "GAME PAUSED";
        int x = getCenterXPositionForText(text), y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public int getCenterXPositionForText(String text){
        return gp.screenWidth / 2 - (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth() / 2;
    }
}
