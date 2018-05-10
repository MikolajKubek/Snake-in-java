import javax.swing.*;

public class Main{

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setBounds(50,50, 700, 1000);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);

        JPanel panel = new JPanel();
        panel.setSize(500,500);
        panel.setLocation(frame.getWidth()/2 - 250, frame.getHeight()/3);
        panel.setVisible(true);
        frame.add(panel);

        Counter counter = new Counter();
        counter.setSize(200, 100);
        counter.setLocation(frame.getWidth()/2 - 100, frame.getHeight()/9);
        frame.add(counter);


        Snake snake = new Snake( 50, 50, counter);
        Screen screen = new Screen(0, 0, 500, 500, snake);
        panel.add(screen);
        frame.addKeyListener(snake);


        while(snake.getEnd() != true)
            screen.run();



    }
}