import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake implements KeyListener{
    private Point bounds;
    private Point apple;
    private int direction = 4;
    private List<Point> body = new ArrayList<>();
    private boolean end = false;
    private int wasActionPerformed = 0;
    private Counter counter;

    public Snake(int width, int height, Counter counter)
    {
        body.add(new Point((int)width/2, (int)height/2));
        bounds = new Point(width, height);
        apple = new Point(10,10);

        for(int i = 0; i < 5; i++)
            body.add(new Point((int)bounds.getX()+1, (int)bounds.getY()+1));

        this.counter = counter;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(wasActionPerformed == 0) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (direction != 2)
                        direction = 0;
                    break;

                case KeyEvent.VK_RIGHT:
                    if (direction != 3)
                        direction = 1;
                    break;

                case KeyEvent.VK_DOWN:
                    if (direction != 0)
                        direction = 2;
                    break;

                case KeyEvent.VK_LEFT:
                    if (direction != 1)
                        direction = 3;
                    break;

                default:
                    break;
            }
            wasActionPerformed++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void move()
    {
        if(body.size() > 0)
        {
            for(int i = body.size()-1; i > 0; i--)
            {
                body.get(i).setLocation(body.get(i-1));
            }
        }

        switch (direction){

           case 0:
            if(body.get(0).getY() > 0)
                body.get(0).setLocation(body.get(0).getX(), body.get(0).getY() - 1);
            else
                body.get(0).setLocation(body.get(0).getX(), bounds.getY() - 1);
            break;

           case 1:
               if(body.get(0).getX() < bounds.getX() - 1)
                   body.get(0).setLocation(body.get(0).getX() + 1, body.get(0).getY());
               else
                   body.get(0).setLocation(0, body.get(0).getY());
           break;

           case 2:
               if(body.get(0).getY() < bounds.getY() - 1)
                   body.get(0).setLocation(body.get(0).getX(), body.get(0).getY() + 1);
               else
                   body.get(0).setLocation(body.get(0).getX(), 0);
           break;

           case 3:
               if(body.get(0).getX() > 0)
                   body.get(0).setLocation(body.get(0).getX() - 1, body.get(0).getY());
               else
                   body.get(0).setLocation(bounds.getX() - 1, body.get(0).getY());
           break;
        }

        if(body.get(0).equals(apple))
            score();

        for(int i = 1; i < body.size(); i++)
        {
            if(body.get(i).equals(body.get(0))) {
                System.out.println("ups");
                end = true;
            }
        }

        wasActionPerformed = 0;
    }

    public void score()
    {
        Random random = new Random();
        body.add(new Point((int)bounds.getX()+1, (int)bounds.getY()+1));
        apple.setLocation(random.nextInt((int)bounds.getX()), random.nextInt((int)bounds.getY()));
        counter.addScore();
    }

    public int find(int i, int j) {
        if(body.get(0).getX() == i && body.get(0).getY() == j)
            return 1;

        for(Point point: body)
            if(point.getY() == j && point.getX() == i)
                return 1;

        if(apple.getX() == i && apple.getY() == j)
            return 2;

        return 0;
        }

    public boolean getEnd() {
        return end;
    }

    public int getDirection()
    {
        return direction;
    }
}