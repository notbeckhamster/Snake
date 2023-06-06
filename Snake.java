/** Beckham Wilson 
 * Snake.java App Class 3/6/22
 */

import java.awt.event.*;
import javax.swing.*;

 /**Snake Application Class */
 public class Snake{
    public static void main(String[] args){
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new SnakePanel());
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(new MyKeyListener());
    }
    private static class MyKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 40){
                SnakePanel.move('D');
            }else if (e.getKeyCode() == 39){
                SnakePanel.move('R');
            }else if (e.getKeyCode() == 38){
                SnakePanel.move('U');
            }else if (e.getKeyCode() == 37){
                SnakePanel.move('L');
            }
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }
    }
 }