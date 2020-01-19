import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

class GameFrame extends JFrame implements ActionListener{
    /**
     *
     */
    private static final long serialVersionUID = -4943031890787800670L;
    private Minesweeper game;
    private Grid grid;

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public GameFrame(Minesweeper game) {
        super("Minesweeper");
        this.game = game;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLayout(new BorderLayout());
        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(this);
        add(restartButton, BorderLayout.PAGE_END);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.restart();
        grid.updataCells();
    }

}