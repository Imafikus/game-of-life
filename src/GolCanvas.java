import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;


public class GolCanvas extends Application {

private static int width = 300;
private static int height = 300;
private static int cellSize = 10;

private static Color deadCellColor = Color.BURLYWOOD;
private static Color aliveCellColor = Color.BLUE;

private static int numberOfRandomCells = 50;

private static boolean[][] cellInfo = new boolean[width / cellSize][height / cellSize];

private static boolean ALIVE = true;
private static boolean DEAD = false;

private final Rectangle[][] cells = new Rectangle[width / cellSize][height / cellSize];

    @Override
    public void start(Stage stage) {

        stage.setTitle("Game of Life");

        // Init Canvas stuff
        Pane root = new Pane();
        Canvas canvas = new Canvas(width, height);

        // GOL init
        initCellInfoMatrix(cellInfo);
        initCells();
        // setRandomCellsToAlive(numberOfRandomCells);
        testCaseSingleCell();
        updateAllCells();
        root.getChildren().addAll(createCellGroup());

        Scene scene = new Scene(root, width, height, Color.WHITE);
        stage.setScene(scene);
        stage.show();
    }
    private Group createCellGroup() {
        Group cellGroup = new Group();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cellGroup.getChildren().add(cells[i][j]);
            }
        }
        return cellGroup;
    }

    private void initCells() {
        for (int i = 0; i < width / cellSize; i++) {
            for (int j = 0; j < height / cellSize; j++) {
                cells[i][j] = new Rectangle(i * cellSize, j * cellSize, cellSize, cellSize);
                cells[i][j].setFill(deadCellColor);
            }
        }
    }

    private void updateAllCells() {
        for (int i = 0; i < cellInfo.length; i++) {
            for (int j = 0; j < cellInfo.length; j++) {
                updateCell(i, j);
                fillCell(i, j);
            }
        }
    }

    private void fillCell(int x, int y) {

        if(cellInfo[x][y] == DEAD) {
            cells[x][y].setFill(deadCellColor);
        } else {
            cells[x][y].setFill(aliveCellColor);
        }
    }

    private void setRandomCellsToAlive(int numberOfCells) {
        for(int i = 0; i < numberOfCells; i++) {

            int[] coords = getRandomCoordinates();
            int x = coords[0] / cellSize;
            int y = coords[1] / cellSize;

            System.out.println("Coordinates are: " + x + " " + y);

            cellInfo[x][y] = ALIVE;
            fillCell(x, y);
        }
    }

    private int[] getRandomCoordinates() {
        int x = (int)(Math.random() * (width / cellSize)) * cellSize;
        int y = (int)(Math.random() * (height / cellSize)) * cellSize;

        int[] toReturn = {x, y};
        return  toReturn;
    }

    private void initCellInfoMatrix(boolean[][] cellInfo) {
        for (int i = 0; i < width / cellSize ; i++) {
            for(int j = 0; j < height / cellSize; j++) {
                cellInfo[i][j] = DEAD;
            }
        }
    }

    private void updateCell (int x, int y) { //FIXME
        int cellNeighbours = getNumberOfAliveNeighbours(x, y);

        // check solitude
        if(cellNeighbours <= 1) cellInfo[x][y] = DEAD;

        // check overpopulation
        else if(cellNeighbours >= 4) cellInfo[x][y] = DEAD;

        // check new life
        else if(cellInfo[x][y] == DEAD && cellNeighbours == 3) cellInfo[x][y] = ALIVE;

        // if we stay alive

    }

    private int getNumberOfAliveNeighbours(int x, int y) {

        int scaledWidth = width / cellSize;
        int scaledHeight = height / cellSize;

        System.out.println("Current x, y: " + x + " " + y);
        int alive = 0;

        //upper left
        if(x - 1 >= 0 && y - 1 >= 0 && cellInfo[x - 1][y - 1] == ALIVE) alive++;

        //up
        if(y - 1 >= 0 && cellInfo[x][y - 1] == ALIVE) alive++;

        //upper right
        if(x + 1 < scaledWidth && cellInfo[x + 1][y] == ALIVE) alive++;

        //left
        if(x - 1 >= 0 && cellInfo[x - 1][y] == ALIVE) alive++;

        //right
        if(x + 1 < scaledWidth && cellInfo[x + 1][y] == ALIVE) alive++;

        // bottom left
        if(x - 1 >= 0 && y + 1 < scaledHeight  && cellInfo[x - 1][y + 1] == ALIVE) alive++;

        //  bottom
        if(y + 1 < scaledHeight  && cellInfo[x][y + 1] == ALIVE) alive++;

        //  bottom right
        if(x + 1 < scaledWidth && y + 1 < scaledHeight  && cellInfo[x + 1][y + 1] == ALIVE) alive++;

        return alive;
    }

    private void printCellInfoMatrix(){
        for(int i = 0; i < cellInfo.length; i++) {
            for(int j = 0; j < cellInfo.length; j++) {
                System.out.print(cellInfo[i][j] + " ");
            }
        }
        System.out.println(System.lineSeparator());
    }

    private void testCaseSingleCell() {
        cellInfo[15][15] = ALIVE;
        fillCell(15, 15);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
