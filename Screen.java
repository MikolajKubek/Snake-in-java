import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Screen extends JLabel implements Runnable {

    private BufferedImage image;
    private Snake snake;

    public Screen(int x, int y, int width, int height, Snake snake)
    {
        setBounds(x, y, width, height);
        this.snake = snake;

        image = new BufferedImage(50,50, BufferedImage.TYPE_INT_ARGB);
        for(int i = 0; i < 50; i++)
            for(int j = 0; j < 50; j++)
                image.setRGB(i,j, Color.black.getRGB());

        setIcon(new ImageIcon(image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));
        setVisible(true);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++)
            for (int j = 0; j < 50; j++) {
                if (snake.find(i, j) == 1) {
                    image.setRGB(i, j, Color.green.getRGB());
                } else if (snake.find(i, j) == 2)
                    image.setRGB(i, j, Color.red.getRGB());
                else if (snake.find(i, j) == 0)
                    image.setRGB(i, j, Color.black.getRGB());
            }
        this.setIcon(new ImageIcon(image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));
        if (snake.getDirection() != 4) {
            snake.move();
        }

        try{Thread.sleep(20);}
        catch(InterruptedException e){System.out.println(e);}
    }
}
