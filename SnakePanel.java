/** Beckham Wilson 
 * SnakePanel.java Support Panel Class 3/6/22
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Arrays;

import java.util.ArrayList;
 /** Holds some snakey components */
public class SnakePanel extends JPanel{
        private static int width = 400;
        private static int height = 400;
        private static int blockSize = 20;
        private static int snakex = 0;
        private static int snakey = 0;
        private static char currentDirection;
        private static int length = 5;
        private static int[] appleCord;
        private static int score,highScore;
        private static boolean appleChange = true, gameOn;
        private static ArrayList<int[]> snakeOrder = new ArrayList<int[]>();
        
        

        public SnakePanel(){
            setPreferredSize(new Dimension(width,height));
            Timer time = new Timer(1000/20, new MyTimerListener());
            time.start();
            for (int i = 0; i<length; i++){
                int[] tempArr = {(width/blockSize)/2+i,height/2/blockSize};
                snakeOrder.add(tempArr);
            }
            
        }
    
        public class MyTimerListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                for (int[] eachInt : snakeOrder){
                    System.out.println(Arrays.toString(eachInt));
                }
                System.out.println("\n");
                ArrayList<int[]> tempArrayList = snakeOrder;
                if (currentDirection == 'U'){
                     int[] firstStep = {snakeOrder.get(snakeOrder.size()-1)[0],snakeOrder.get(snakeOrder.size()-1)[1]-1};
                    for (int i = 0; i< snakeOrder.size()-1; i++){
                        int[] tempArr = {tempArrayList.get(i+1)[0],tempArrayList.get(i+1)[1]};
                        snakeOrder.set(i, tempArr);
                    }
                    snakeOrder.set(snakeOrder.size()-1,firstStep);
                }else if (currentDirection == 'R'){
                    int[] firstStep = {snakeOrder.get(snakeOrder.size()-1)[0]+1,snakeOrder.get(snakeOrder.size()-1)[1]};
                    for (int i = 0; i< snakeOrder.size()-1; i++){
                        int[] tempArr = {tempArrayList.get(i+1)[0],tempArrayList.get(i+1)[1]};
                        snakeOrder.set(i, tempArr);
                    }
                    snakeOrder.set(snakeOrder.size()-1,firstStep);
                }else if (currentDirection == 'L'){
                    int[] firstStep = {snakeOrder.get(snakeOrder.size()-1)[0]-1,snakeOrder.get(snakeOrder.size()-1)[1]};
                    
                    for (int i = 0; i< snakeOrder.size()-1; i++){
                        int[] tempArr = {tempArrayList.get(i+1)[0],tempArrayList.get(i+1)[1]};
                        snakeOrder.set(i, tempArr);
                    }
                    snakeOrder.set(snakeOrder.size()-1,firstStep);
                }else if (currentDirection == 'D'){
                    int[] firstStep = {snakeOrder.get(snakeOrder.size()-1)[0],snakeOrder.get(snakeOrder.size()-1)[1]+1};
                    
                    for (int i = 0; i< snakeOrder.size()-1; i++){
                        int[] tempArr = {tempArrayList.get(i+1)[0],tempArrayList.get(i+1)[1]};
                        snakeOrder.set(i, tempArr);
                    }
                    snakeOrder.set(snakeOrder.size()-1,firstStep);
                }

                
                repaint();
            }
        }
        public void paintComponent(Graphics g){
            drawGrid(g);
            drawApple(g);
            drawSnake(g);
            checkGameOver(g);
            
    
        }
        public static void checkGameOver(Graphics g){
            for (int[] eachSnakePart : snakeOrder){
                boolean checkBoundary = ((eachSnakePart[0]>=0 && eachSnakePart[0]<=width/blockSize)&&(eachSnakePart[1]>=0 && eachSnakePart[1]<=height/blockSize));
                boolean checkSelfCollide= true;
                    for(int[] eachSnakeBit:snakeOrder){
                         if (eachSnakeBit.equals(eachSnakePart)&& (snakeOrder.indexOf(eachSnakeBit)!=snakeOrder.indexOf(eachSnakePart))){
                            checkSelfCollide=false;
                        }
                    }
                    gameOn=checkBoundary&&checkSelfCollide;
                    if (!gameOn){
                        break;
                    }
                }
                
                if (!gameOn ){
                    g.setColor(Color.white);
                    g.fillRect(0,0,width,height);
                    g.setColor(Color.black);
                    Font font = new Font("Arial", Font.BOLD,10);
                    String text = "Press Arrow Keys to Restart";
                    FontMetrics metrics = g.getFontMetrics(font);
                    // Determine the X coordinate for the text
                    int x =  (width - metrics.stringWidth(text)) / 2;
                    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
                    int y =((height - metrics.getHeight()) / 2) ;
                    // Set the font
                    g.setFont(font);
                    // Draw the String
                    g.drawString(text, x, y);
                    y+=20;
                    g.drawString("Score:" + score,x,y);
                    if (score>highScore){
                        highScore=score;
                    }
                    y+=20;
                    g.drawString("High Score:"+ highScore,x,y);
                } 
            
            }   
        

        
        public static void move(char direction){
            if (direction == 'U' && currentDirection != 'D'){
                currentDirection='U';
            }else if (direction == 'L' && currentDirection != 'R'){
                currentDirection='L';
            }else if (direction == 'R' && currentDirection != 'L'){
                currentDirection='R';
            } else if (direction == 'D' && currentDirection != 'U'){
                currentDirection='D';
            }
            if (!gameOn){
                gameOn=true;
                length=5;
                score=0;
                snakeOrder.clear();
                for (int i = 0; i<length; i++){
                    int[] tempArr = {(width/blockSize)/2+i,height/2/blockSize};
                    snakeOrder.add(tempArr);
                }
                currentDirection = ' ';
            }


            
        }
        public static void drawGrid(Graphics g){
            g.setColor(Color.white);
            g.fillRect(0,0,width,height);
            g.setColor(Color.black);
            for (int i = 0; i< height/blockSize;i++){
                for (int j = 0; j< width/blockSize; j++){
                    g.drawRect(i*blockSize,j*blockSize, blockSize,blockSize);
                }
            }
        }

        public static void drawSnake(Graphics g){
            g.setColor(Color.green);
            for (int i = 0; i<snakeOrder.size();i++){
                g.fillRect(snakeOrder.get(i)[0]*blockSize, snakeOrder.get(i)[1]*blockSize, blockSize, blockSize);
            
                    
                
            }
           
        }

        public static void drawApple(Graphics g){
            Random rand = new Random();
            int[] tempArr = new int[2];
            
              
             while(appleChange){
                int xcord = rand.nextInt(width/blockSize);
                int ycord = rand.nextInt(height/blockSize);
                tempArr[0] = xcord;
                tempArr[1] = ycord;
                if (!snakeOrder.contains(tempArr)) appleChange = false;
                appleCord = tempArr;
                
              }
            
              if ((appleCord[0]==snakeOrder.get(snakeOrder.size()-1)[0])&&(appleCord[1]==snakeOrder.get(snakeOrder.size()-1)[1])){
                appleChange = true;
                score += 1;
                length +=1 ;  
                if (currentDirection == 'U'){
                    int[] firstStep = {snakeOrder.get(snakeOrder.size()-1)[0],snakeOrder.get(snakeOrder.size()-1)[1]-1};

                   snakeOrder.add(firstStep);
               }else if (currentDirection == 'R'){
                   int[] firstStep = {snakeOrder.get(snakeOrder.size()-1)[0]+1,snakeOrder.get(snakeOrder.size()-1)[1]};
                   
                   snakeOrder.add(firstStep);
               }else if (currentDirection == 'L'){
                   int[] firstStep = {snakeOrder.get(snakeOrder.size()-1)[0]-1,snakeOrder.get(snakeOrder.size()-1)[1]};
                   
                   snakeOrder.add(firstStep);
               }else if (currentDirection == 'D'){
                   int[] firstStep = {snakeOrder.get(snakeOrder.size()-1)[0],snakeOrder.get(snakeOrder.size()-1)[1]+1};
                   
                   snakeOrder.add(firstStep);
               }   
            }
       
            g.setColor(Color.red);
            g.fillRect(appleCord[0]*blockSize, appleCord[1]*blockSize, blockSize, blockSize);






        }
}
