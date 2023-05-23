package main;

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
    KeyHanlder keyHanlder = new KeyHanlder();
    Thread gameThread;
    public Player player = new Player(this, keyHanlder);
    public SuperObject objs[] = new SuperObject[10];
    Sound SFXController = new Sound();
    Sound musicController = new Sound();
    public UI ui = new UI(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHanlder);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObjects();

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
        player.update();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        //TILES
        tileManager.draw(graphics2D);
        //OBJECTS
        for (SuperObject obj : objs) {
            if(obj != null)
                obj.draw(graphics2D, this);
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
