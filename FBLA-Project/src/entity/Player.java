package src.entity;

import java.awt.Graphics2D;
import java.io.IOException;
// import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import src.main.GamePanel;
import src.main.KeyHandler;


public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 8;
        speed = 6;
        direction = "forward";
    }
    public void getPlayerImage(){
      try{
        up1 = ImageIO.read(getClass().getResourceAsStream("../../res/player/rfup1.png"));
        up2 = ImageIO.read(getClass().getResourceAsStream("../../res/player/rfup2.png"));

        down1 = ImageIO.read(getClass().getResourceAsStream("../../res/player/rfdown1.png"));
        down2 = ImageIO.read(getClass().getResourceAsStream("../../res/player/rfdown2.png"));

        left1 = ImageIO.read(getClass().getResourceAsStream("../../res/player/rfleft1.png"));
        left2 = ImageIO.read(getClass().getResourceAsStream("../../res/player/rfleft2.png"));

        right1 = ImageIO.read(getClass().getResourceAsStream("../../res/player/rfright1.png"));
        right2 = ImageIO.read(getClass().getResourceAsStream("../../res/player/rfright2.png"));

      }catch(IOException e){
        e.printStackTrace();
      }
    }

    public void update(){
      if(keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed){
            if(keyH.downPressed == true){
              direction = "down";
              worldY += speed;
            }
            if(keyH.upPressed == true){
              direction = "up";
              worldY -= speed;
            }
            if(keyH.rightPressed == true){
              direction = "right";
              worldX += speed;
            }
            if(keyH.leftPressed == true){
              direction = "left";
              worldX -= speed;
            }
          spriteCounter++;
          if(spriteCounter > 10){
            if(spriteNum == 1){
              spriteNum = 2;
            }
            else if(spriteNum == 2){
              spriteNum = 1;
            }
            spriteCounter = 0;
          }
      }
    }

    public void draw(Graphics2D g2){
        // g2.setColor(Color.white); // sets color to white

        // g2.fillRect(x, y, gp.tileSize, gp.tileSize); //make a rectangle of tileSize x tileSize and (100,100)
        BufferedImage image = null;

        switch(direction){
          case "up":
          if(spriteNum == 1){
            image = up1;
          }
          if(spriteNum == 2){
            image = up2;
          }
            break;
          case "down":
          if(spriteNum == 1){
            image = down1;
          }
          if(spriteNum == 2){
            image = down2;
          }
            break;
          case "left":
          if(spriteNum == 1){
            image = left1;
          }
          if(spriteNum == 2){
            image = left2;
          }
            break;
          case "right":
          if(spriteNum == 1){
            image = right1;
          }
          if(spriteNum == 2){
            image = right2;
          }
            break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
    
}
