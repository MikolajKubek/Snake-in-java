import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake implements KeyListener{
    private Point head;
    private Point bounds;
    private Point apple;
    private int direction = 4;
    private List<Point> body = new ArrayList<>();
    private boolean end = false;

    public Snake(int width, int height)
    {
        head = new Point((int)width/2, (int)height/2);
        bounds = new Point(width, height);
        apple = new Point(10,10);


    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                if(direction != 2)
                    direction = 0;
            break;

            case KeyEvent.VK_RIGHT:
                if(direction != 3)
                    direction = 1;
            break;

            case KeyEvent.VK_DOWN:
                if(direction != 0)
                    direction = 2;
            break;

            case KeyEvent.VK_LEFT:
                if(direction != 1)
                    direction = 3;
            break;

            default: break;
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
            body.get(0).setLocation(head.getLocation());
        }

        switch (direction){

           case 0:
            if(head.getY() > 0)
                head.setLocation(head.getX(), head.getY() - 1);
            else
                head.setLocation(head.getX(), bounds.getY() - 1);
            break;

           case 1:
               if(head.getX() < bounds.getX() - 1)
                   head.setLocation(head.getX() + 1, head.getY());
               else
                   head.setLocation(0, head.getY());
           break;

           case 2:
               if(head.getY() < bounds.getY() - 1)
                   head.setLocation(head.getX(), head.getY() + 1);
               else
                   head.setLocation(head.getX(), 0);
           break;

           case 3:
               if(head.getX() > 0)
                   head.setLocation(head.getX() - 1, head.getY());
               else
                   head.setLocation(bounds.getX() - 1, head.getY());
           break;
        }

        if(head.equals(apple))
        {
            Random random = new Random();
            score();
            apple.setLocation(random.nextInt((int)bounds.getX()), random.nextInt((int)bounds.getY()));
        }

        for(Point point: body)
        {
            if(point.equals(head)) {
                System.out.println("ups");
                end = true;
            }
        }

    }

    public void score()
    {
        body.add(new Point((int)bounds.getX()+1, (int)bounds.getY()+1));
    }

    public int find(int i, int j) {
        if(head.getX() == i && head.getY() == j)
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
}