package main;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    final public int tileSize = originalTileSize * scale;
    public final int maxScreenColumns = 16;
    public final int maxScreenRows = 12;
    public final int screenWidth = tileSize * maxScreenColumns;
    public final int screenHeight = tileSize * maxScreenRows;

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    int FPS = 60;
    //SYSTEM
    public CollisionController CC = new CollisionController(this);
    public AssetSetter aSetter = new AssetSetter(this);
    TileManager tileManager = new TileManager(this);
    public KeyHanlder keyHanlder = new KeyHanlder(this);
    Thread gameThread;
    //ENTITIES AND OBJECTS
    public Player player = new Player(this, keyHanlder);
    public SuperObject objs[] = new SuperObject[10];
    public Entity npcs[] = new Entity[10];
    //OOO
    Sound SFXController = new Sound();
    Sound musicController = new Sound();
    public UI ui = new UI(this);

    //GAME SETTINGS
    public enum GameStates{PLAY, PAUSE, DIALOGUE};
    public GameStates gameState;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHanlder);
        this.setFocusable(true);
    }

    public void setupGame(){
        gameState = GameStates.PLAY;
        aSetter.setObjects();
        aSetter.setNpc();
        //playMusic(0);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime -lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                //System.out.println(("FPS: " + drawCount));
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update(){
        if(gameState == GameStates.PLAY){
            player.update();
            for (Entity npc: npcs
                 ) {
                if(npc != null){
                    npc.update();
                }
            }
        }else if(gameState == GameStates.PAUSE){
            //TODO implement pause state code
        }
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        //TILES
        tileManager.draw(graphics2D);
        //OBJECTS
        for (SuperObject obj : objs) {
            if(obj != null)
                obj.draw(graphics2D, this);
        }
        //NPC
        for (Entity npc : npcs) {
            if (npc != null) {
                npc.draw(graphics2D);
            }
        }
        //PLAYER
        player.draw(graphics2D);
        ui.draw(graphics2D);
        graphics2D.dispose();
    }

    //SOUND
    public void playMusic(int i){
        musicController.setFile(i);
        musicController.play();
        musicController.loop();
    }

    public void stopMusic(){
        musicController.stop();
    }

    public void playSFX(int i){
        SFXController.setFile(i);
        SFXController.play();
    }
}
