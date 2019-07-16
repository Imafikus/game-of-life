import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


public class GolCanvas extends Application {

    static int width = 300;
    static int height = 300;
    static int cellSize = 10;

    static Color deadCellColor = Color.WHITE;
    static Color aliveCellColor = Color.BLUE;

    static int numberOfRandomCells = 50;

    static boolean[][] cellInfo = new boolean[width / cellSize][height / cellSize];

    static boolean ALIVE = true;
    static boolean DEAD = false;

    @Override
    public void start(Stage stage) {
        init(stage);
    }

    private void init(Stage stage) {

        // Init Canvas stuff
        Pane root = new Pane();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();


        // GOL init
        initCellInfoMatrix(cellInfo);
        setRandomCellsToAlive(gc, numberOfRandomCells);


        //GOL loop
        int n = 2;
        while(n > 0) {
            for (int i = 0; i < cellInfo.length; i++) {
                for (int j = 0; j < cellInfo.length; j++) {
                    updateCell(i, j);
                    drawRectangle(gc, i, j, cellInfo[i][j]);
                }
            }
            drawGrid(gc);
            root.getChildren().addAll(canvas);
            Scene scene = new Scene(root, width, height, Color.WHITE);
            stage.setTitle("Game of Life");
            stage.setScene(scene);
            stage.show();
            n--;
        }

        // display logic
//        drawGrid(gc);
//        root.getChildren().addAll(canvas);
//        Scene scene = new Scene(root, width, height, Color.WHITE);
//        stage.setTitle("Game of Life");
//        stage.setScene(scene);
        //stage.show();
    }

    private void drawGrid(GraphicsContext gc) {
        gc.setLineWidth(1.0);

        for(int x = 0; x < width; x += cellSize) {
            gc.moveTo(x, 0);
            gc.lineTo(x, height);
            gc.stroke();
        }

        for(int y = 0; y < width; y += cellSize) {
            gc.moveTo(0, y);
            gc.lineTo(width, y);
            gc.stroke();
        }

    }

    private void drawRectangle(GraphicsContext gc, int x, int y, boolean cellType) {

        if(cellType == DEAD) {
            gc.setFill(deadCellColor);
        } else {
            gc.setFill(aliveCellColor);
        }


        gc.fillRect(x, y, cellSize, cellSize);

    }

    private void setRandomCellsToAlive(GraphicsContext gc, int numberOfCells) {
        for(int i = 0; i < numberOfCells; i++) {

            int[] coords = getRandomCoordinates();
            int x = coords[0];
            int y = coords[1];

            drawRectangle(gc, x, y, ALIVE);

            cellInfo[x / cellSize][y / cellSize] = ALIVE;
        }
    }

    private int[] getRandomCoordinates() {
        int x = (int)(Math.random() * 30) * cellSize;
        int y = (int)(Math.random() * 30) * cellSize;

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


    private void updateCell (int x, int y) {
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
        int alive = 0;

        //upper left
        if(x - 1 >= 0 && y - 1 >= 0 && cellInfo[x - 1][y - 1] == ALIVE) alive++;

        //up
        if(y - 1 >= 0 && cellInfo[x][y - 1] == ALIVE) alive++;

        //upper right
        if(x + 1 <= width && cellInfo[x + 1][y] == ALIVE) alive++;

        //left
        if(x - 1 >= 0 && cellInfo[x - 1][y] == ALIVE) alive++;

        //right
        if(x + 1 <= width && cellInfo[x + 1][y] == ALIVE) alive++;

        // bottom left
        if(x - 1 >= 0 && y + 1 <= height  && cellInfo[x - 1][y + 1] == ALIVE) alive++;

        //  bottom
        if(y + 1 <= height  && cellInfo[x][y + 1] == ALIVE) alive++;

        //  bottom right
        if(x + 1 <= width && y + 1 <= height  && cellInfo[x + 1][y + 1] == ALIVE) alive++;

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

    public static void main(String[] args) {
        launch(args);
    }
}
