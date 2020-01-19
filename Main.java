import java.awt.BorderLayout;

class Main{
    static int xSize = 10;
    static int ySize = 10;
    
    public static void main(String[] args) {
        Minesweeper game = new Minesweeper(xSize, ySize);
        GameFrame gameFrame = new GameFrame(game);
        TopFrame topFrame = new TopFrame(game);
        Grid grid = new Grid(xSize, ySize, game, topFrame);
        gameFrame.add(topFrame, BorderLayout.PAGE_START);
        gameFrame.setGrid(grid);
        gameFrame.add(grid, BorderLayout.CENTER);
        gameFrame.setSize(ySize *30, 30 *xSize + 90);
        //gameFrame.pack();        
    }
}