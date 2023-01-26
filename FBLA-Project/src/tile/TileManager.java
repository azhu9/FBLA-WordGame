package src.tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import src.main.GamePanel;

import java.awt.Graphics2D;

public class TileManager{
  GamePanel gp;
  public Tile[] tile;
  public int mapTileNum[][];

  public TileManager(GamePanel gp){
    this.gp = gp;
    tile = new Tile[20]; //number of different tiles
    mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

    getTileImage();
    loadMap("../../res/maps/lvl1r.txt");
  }
  public void getTileImage(){
    try{
      tile[0] = new Tile();
      tile[0].image = ImageIO.read(getClass().getResourceAsStream("../../res/tiles/grass.png"));//one tile
      
      tile[1] = new Tile();
      tile[1].image = ImageIO.read(getClass().getResourceAsStream("../../res/tiles/water.png"));
      tile[1].collision = true;

      tile[2] = new Tile();
      tile[2].image = ImageIO.read(getClass().getResourceAsStream("../../res/tiles/wall.jpg"));
      tile[2].collision = true;

      tile[3] = new Tile();
      tile[3].image = ImageIO.read(getClass().getResourceAsStream("../../res/tiles/sand.png"));
      
      tile[4] = new Tile();
      tile[4].image = ImageIO.read(getClass().getResourceAsStream("../../res/tiles/treegrass.png"));
      tile[4].collision = true;

      tile[5] = new Tile();
      tile[5].image = ImageIO.read(getClass().getResourceAsStream("../../res/tiles/flowers.png"));

      tile[6] = new Tile();
      tile[6].image = ImageIO.read(getClass().getResourceAsStream("../../res/tiles/rock1.png"));

      tile[7] = new Tile();
      tile[7].image = ImageIO.read(getClass().getResourceAsStream("../../res/tiles/rock2.png"));
      
      tile[8] = new Tile();
      tile[8].image = ImageIO.read(getClass().getResourceAsStream("../../res/tiles/dirt.png"));
    
      tile[9] = new Tile();
      tile[9].image = ImageIO.read(getClass().getResourceAsStream("../../res/tiles/bush.png"));

      tile[10] = new Tile();
      tile[10].image = ImageIO.read(getClass().getResourceAsStream("../../res/mobs/boss1.png"));

      tile[11] = new Tile();
      tile[11].image = ImageIO.read(getClass().getResourceAsStream("../../res/mobs/boss2.png"));

      tile[12] = new Tile();
      tile[12].image = ImageIO.read(getClass().getResourceAsStream("../../res/mobs/boss3.png"));

      tile[13] = new Tile();
      tile[13].image = ImageIO.read(getClass().getResourceAsStream("../../res/mobs/boss4.png"));

    }catch(IOException e){
      e.printStackTrace();
    }

  }

  public void loadMap(String mapPath){
    try{
      InputStream is = getClass().getResourceAsStream(mapPath);
      BufferedReader br = new BufferedReader(new InputStreamReader(is));

      int col = 0;
      int row = 0;

      while(col < gp.maxWorldCol && row < gp.maxWorldRow){
        String line =  br.readLine(); //reads a single line of map.txt

        while(col < gp.maxWorldCol){
          String numbers[] = line.split(" ");

          int num = Integer.parseInt(numbers[col]);

          mapTileNum[col][row] = num;
          col++;
        }
        if(col == gp.maxWorldCol){
          col = 0;
          row++;
        }
      }
      br.close();

    }catch(Exception e){
      e.printStackTrace();
    }
  } 
  public void draw(Graphics2D g2){
    // g2.drawImage(tile[0].image,0,0,gp.tileSize, gp.tileSize, null); draws tile[0] at 0,0
    int worldCol = 0;
    int worldRow = 0;


    while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

      int tileNum = mapTileNum[worldCol][worldRow];

      int worldX = worldCol * gp.tileSize;
      int worldY = worldRow * gp.tileSize;
      int screenX = worldX - gp.player.worldX + gp.player.screenX;
      int screenY = worldY - gp.player.worldY + gp.player.screenY;

      if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize< gp.player.worldY + gp.player.screenY){
        g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
      }
      worldCol++;

      if(worldCol == gp.maxWorldCol){
        worldCol = 0;
        worldRow++;
      }
    }
  }
}