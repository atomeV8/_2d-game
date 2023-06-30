package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHanlder implements KeyListener {

    public GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, interactKeyPressed;
    //DEBUG
    boolean checkDrawTime = false;

    public KeyHanlder(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(gp.gameState == GamePanel.GameStates.TITLE_SCREEN){
            switch(code){
                case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                    gp.ui.commandNumber--;
                    if(gp.ui.commandNumber < 0)
                        gp.ui.commandNumber = 2;
                }
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                    gp.ui.commandNumber++;
                    if(gp.ui.commandNumber > 2)
                        gp.ui.commandNumber = 0;
                }
                case KeyEvent.VK_ENTER -> {
                    switch(gp.ui.commandNumber){
                        case 0 -> {
                            gp.gameState = GamePanel.GameStates.PLAY;
                            gp.playMusic(0);
                        }
                        case 1 -> {
                            //TBI
                        }
                        case 2 -> {
                            System.exit(0);
                        }
                    }
                }
            }
        }
        else if(gp.gameState == GamePanel.GameStates.PLAY){
            switch(code){
                case KeyEvent.VK_W -> {
                    upPressed = true;
                }
                case KeyEvent.VK_A -> {
                    leftPressed = true;
                }
                case KeyEvent.VK_S -> {
                    downPressed = true;
                }
                case KeyEvent.VK_D -> {
                    rightPressed = true;
                }
                case KeyEvent.VK_ESCAPE -> {
                    gp.gameState = GamePanel.GameStates.PAUSE;
                }
                case KeyEvent.VK_F -> {
                    interactKeyPressed = true;
                }
            }
        }
        else if(gp.gameState == GamePanel.GameStates.PAUSE){
            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = GamePanel.GameStates.PLAY;
            }
        }
        else if(gp.gameState == GamePanel.GameStates.DIALOGUE){
            if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_F){
                gp.gameState = GamePanel.GameStates.PLAY;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
