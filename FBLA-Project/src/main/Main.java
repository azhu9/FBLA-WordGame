package src.main;
import javax.swing.JFrame;

class Main {
  public static void main(String args[]){
    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setTitle("Keyboard Warrior");

    GamePanel gamePanel = new GamePanel();
    window.add(gamePanel);

    window.pack();
    
    window.setLocationRelativeTo(null);
    window.setVisible(true);

    gamePanel.startGameThread(); //start game loop
    System.out.println("Game Ran Sucessfully");
}
}