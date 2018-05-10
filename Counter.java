import javax.swing.*;

public class Counter extends JLabel {

    public int score = 0;

    public Counter()
    {
        setText("<html><div style='text-align: center;'>" + 0 + "</div></html>");
        setFont(this.getFont().deriveFont(40.f));
        setVisible(true);
    }

    public void addScore()
    {
        score++;
        setText("<html><div style='text-align: center;'>" + score + "</div></html>");
    }
}
