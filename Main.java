import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main{

    public JFrame frame;
    public BufferedImage image;
    public JLabel label;
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setBounds(50,50, 1000, 1000);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        BufferedImage image = new BufferedImage(50,50, BufferedImage.TYPE_INT_ARGB);
        for(int i = 0; i < 50; i++)
            for(int j = 0; j < 50; j++)
                image.setRGB(i,j,Color.black.getRGB());

        JLabel label = new JLabel();
        frame.add(label);
        label.setBounds(0,0,frame.getWidth(), frame.getHeight());
        label.setIcon(new ImageIcon(image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH)));
        label.setVisible(true);


        Snake snake = new Snake( 50, 50);

        frame.addKeyListener(snake);


        while(!snake.getEnd())
        {
            snake.move();
            for(int i = 0; i < 50; i++)
                for(int j = 0; j < 50; j++)
                {
                    if(snake.find(i,j) == 1) {
                        image.setRGB(i, j, Color.green.getRGB());
                    }
                    else if(snake.find(i, j) == 2)
                        image.setRGB(i,j,Color.red.getRGB());
                    else if(snake.find(i, j) == 0)
                        image.setRGB(i, j, Color.black.getRGB());
                }
                label.setIcon(new ImageIcon(image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH)));
            try{Thread.sleep(50);}
            catch(InterruptedException e){}
        }

        snake = null;

    }
}