import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;

class Grid extends JPanel implements MouseListener  {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int xSize, ySize;
    private Minesweeper game;
    private Cell[][] cellButtons;
    private TopFrame topFrame;
    private Clock clock;

    public Grid(int xSize, int ySize, Minesweeper game, TopFrame topFrame) {
        this.game = game;
        this.xSize = xSize;
        this.ySize = ySize;
        this.topFrame = topFrame;
        cellButtons = new Cell[xSize][ySize];
        setLayout(new GridLayout(xSize, ySize));
        clock = topFrame.getClock();

        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Cell cell = new Cell();
                cellButtons[i][j] = cell;
                cell.setActionCommand(i + " " + j);
                cell.addMouseListener(this);
                add(cell);
            }
        }
        setSize(30 * xSize, 30 * ySize);
        
        updataCells();
    }

    public void updataCells() {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                cellButtons[i][j].openCell(game.getIsOpen(i, j));
                cellButtons[i][j].flaggedCell(game.getIsFlagged(i, j));
                cellButtons[i][j].contentCell(game.getCel(i, j));
            }
        }
        topFrame.setFlaggLabel(Integer.toString(game.getNumFlaggs()));
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        String[] pos = button.getActionCommand().split(" ");
        if (e.getButton() == 3) {
            game.flagCell(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
        } else {
            Integer playing = game.pickCel(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
            if (playing == 0) {
                clock.startTimer();
                System.out.println("start");
            } else if (playing == 2) {
                clock.stopTimer();
                System.out.println("win");
            } else if (playing == -1) {
                clock.stopTimer();
                System.out.println("Game Ower");
            }
        }
        
        //topFrame.setFlaggLabel(Integer.toString(game.getNumFlaggs()));
        updataCells();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}