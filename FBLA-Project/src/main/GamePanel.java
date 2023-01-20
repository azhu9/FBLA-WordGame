package src.main;
import javax.swing.JPanel;

import src.entity.Player;
import src.tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
  public final int originalTileSize = 16; // 32x32
  private final int scale = 5;

  public final int tileSize = originalTileSize * scale; // scale up to 48x48
  private final int maxScreenCol = 16;
  private final int maxScreenRow = 9;
  public final int screenWidth = tileSize * maxScreenCol; //some pixels
  public final int screenHeight = tileSize * maxScreenRow; //some pixels

  public final int maxWorldCol = 50;
  public final int maxWorldRow = 50;
  public final int worldWidth = tileSize * maxWorldCol;
  public final int worldHeight = tileSize * maxWorldRow;

  private int fps = 60;

  TileManager tileM = new TileManager(this);
  KeyHandler keyH = new KeyHandler();
  Thread gameThread; //game clock, also it immedietly calls run method
  public Collision checker = new Collision(this);
  public Player player = new Player(this,keyH);

  //game state
  public int gameState;
  public final int playState = 1;
  public final int pauseState = 2;
  
  public GamePanel(){
    this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //set screen width and height
    this.setBackground(Color.black);
    this.setDoubleBuffered(true); //renders stuff off screen (faster)
    this.addKeyListener(keyH);
    this.setFocusable(true);
  }

  public void startGameThread(){
    gameThread = new Thread(this);
    gameThread.start();
  }

  
  @Override
  public void run(){

    double drawInterval = 1000000000/fps;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    
    while(gameThread != null){ //game loop, repeats until exit
      currentTime = System.nanoTime();

      delta += (currentTime - lastTime) / drawInterval;

      lastTime = currentTime;

      if(delta >= 1){
        update(); //update(character position)

        repaint(); //draw
        delta--;
      }
      
    }
  }

  public void update(){
    player.update();
}
  public void paintComponent(Graphics g){
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g; //cast g to graphics2d which has more methods
    
    tileM.draw(g2);

    player.draw(g2);
    
    g2.dispose(); //closes class, garbage 

    
  }
}
