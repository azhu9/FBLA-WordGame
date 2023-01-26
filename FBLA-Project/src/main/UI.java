package src.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
    String message;
    int messageCounter = 0;
    public boolean messageOn = false;
    public boolean gameFinished = false;

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);

    }

    public void showMessage(String text){
        message = text;

    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        // g2.drawString("Key = 0", 25, 50);
        if(gp.gameState == gp.playState){
           
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        if(gp.gameState == gp.endState){
            drawEndScreen();
        }
    }
    
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,60F));
        String text1 = "PAUSED";
        String text2 = "Press ESCAPE or P to Resume";

        int x1 = getXforCenteredText(text1);
        int x2 = getXforCenteredText(text2);
        int y = gp.screenHeight/2; 

        g2.drawString(text1, x1, y);
        g2.drawString(text2, x2, y+70);
    }

    public void drawEndScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
        g2.setColor(Color.yellow);
        String text1 = "Congratulations You Won!";
        
        int x = getXforCenteredText(text1);
        int y = gp.screenHeight/2;

        g2.drawString(text1, x, y);
    }

    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

}

