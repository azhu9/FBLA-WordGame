package src.main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

  GamePanel gp;
  public boolean upPressed, downPressed, leftPressed, rightPressed;

  public KeyHandler(GamePanel gp){
    this.gp = gp;
  }

  @Override
  public void keyTyped(KeyEvent e){ //NEED FOR KEY INPUT
    
  }
  @Override
  public void keyPressed(KeyEvent e){ //WASD pressed
    int code = e.getKeyCode();
    if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
      upPressed = true;
    }
    else if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
      downPressed = true;
    }
    else if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
      leftPressed = true;
    }
    else if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
      rightPressed = true;
    }
    else if(code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_P){
      if(gp.gameState == gp.playState){
          gp.gameState = gp.pauseState;
      }
      else if(gp.gameState == gp.pauseState){
          gp.gameState = gp.playState;
      }
    }
    else if(code == KeyEvent.VK_B){
      if(gp.gameState == gp.playState){
        gp.gameState = gp.endState;
      }
      else if(gp.gameState == gp.endState){
        gp.gameState = gp.playState;
      }
    }
  }
  @Override
  public void keyReleased(KeyEvent e){ //WASD released
    int code = e.getKeyCode();
    if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
      upPressed = false;
    }
    else if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
      downPressed = false;
    }
    else if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
      leftPressed = false;
    }
    else if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
      rightPressed = false;
    }
  }  
}
