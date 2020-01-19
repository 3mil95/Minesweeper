import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Minesweeper {
    private int[][] mineGrid;
    private boolean[][] seedGrid;
    private boolean[][] flagdGrid;
    private int mines = 20;
    private Random random = new Random();
    private int xSize, ySize;
    private int flaggedCells;
    private int playing;

    public Minesweeper(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        init();
    }

    public void restart() {
        init();
    }

    private void init() {
        mineGrid = new int[xSize][ySize];
        seedGrid = new boolean[xSize][ySize];
        flagdGrid = new boolean[xSize][ySize];
        flaggedCells = 0;
        playing = 0;
        initGrid();
    }

    private void initGrid() {
        for (int i = 0; i < mines; i++) {
            while(true) {
                int x = random.nextInt(mineGrid.length);
                int y = random.nextInt(mineGrid[0].length);
                if (mineGrid[x][y] != -1)  {
                    mineGrid[x][y] = -1;
                    updataNeighborsCels(x, y);
                    break;
                }
            }
        }
    }

    private void checkForWin() {
        int cellsClosed = 0;
        for (int i = 0; i < mineGrid.length; i ++) {
            for (int j = 0; j < mineGrid[0].length; j ++) {
                if (!seedGrid[i][j]) {
                    cellsClosed++;
                }
            }
        }
        if (cellsClosed == mines) {
            System.out.println("win");
            showAll();
            playing = 2;
        }
    }

    private void updataNeighborsCels(int x, int y) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (x+i >= 0 && x+i < xSize && y+j >= 0 && y+j < ySize){
                    if (mineGrid[x+i][y+j] != -1)  {
                        mineGrid[x+i][y+j]++;
                    }   
                }
            } 
        } 
    }

    public void showAll(){
        for (int i = 0; i < mineGrid.length; i ++) {
            for (int j = 0; j < mineGrid[0].length; j ++) {
                seedGrid[i][j] = true;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mineGrid.length; i ++) {
            for (int j = 0; j < mineGrid[0].length; j ++) {
                sb.append(getCel(i, j) + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getCel(int x, int y) {
        if (seedGrid[x][y] && mineGrid[x][y] != 0) {
            return Integer.toString(mineGrid[x][y]);
        }
        return "";
    }

    public boolean getIsFlagged(int x, int y) {
        return flagdGrid[x][y];
    }

    public boolean getIsOpen(int x, int y) {
        return seedGrid[x][y];
    }

    public void flagCell(int x, int y) {
        if (seedGrid[x][y]) {
            return;
        }
        flagdGrid[x][y] = !flagdGrid[x][y];
        if (flagdGrid[x][y]) {
            flaggedCells++;
        } else {
            flaggedCells--;
        }
    }

    public int getNumFlaggs() {
        return flaggedCells;
    }

    public Integer pickCel(int x, int y) {
        if (flagdGrid[x][y]) {
            return null;
        }
        seedGrid[x][y] = true;
        if (mineGrid[x][y] == -1) {
            gamover();
        } else if (mineGrid[x][y] == 0) {
            zeroPick(x, y);
        }
        checkForWin();
        int play = playing;
        System.out.println(play);
        playing = 1;
        return play;
    }

    private void zeroPick(int x, int y) {
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{x, y});
        while(!queue.isEmpty()) {
            int[] curentCell = queue.poll();
            x = curentCell[0];
            y = curentCell[1];
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (x+i < 0 || x+i >= xSize || y+j < 0 || y+j >= ySize){
                        continue;
                    }
                    if (seedGrid[x+i][y+j] == false)  {
                        seedGrid[x+i][y+j] = true;
                        if (mineGrid[x+i][y+j] == 0) {
                            queue.add(new int[]{x+i, y+j});
                        }
                    }
                } 
            } 
        }
    }

    private void gamover() {
        showAll();
        playing = -1;
    }
}