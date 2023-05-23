package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHanlder implements KeyListener {

    public GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
<<<<<<< Updated upstream
=======
    //DEBUG
    boolean checkDrawTime = false;

    public KeyHanlder(GamePanel gp){
        this.gp = gp;
    }
>>>>>>> Stashed changes
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            if(gp.gameState == GamePanel.GameStates.PLAY)
                gp.gameState = GamePanel.GameStates.PAUSE;
            else if(gp.gameState == GamePanel.GameStates.PAUSE)
                gp.gameState = GamePanel.GameStates.PLAY;
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
