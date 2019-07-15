import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class GolCanvas extends Application {

    static Integer width = 300;
    static Integer height = 300;

    @Override
    public void start(Stage stage) {
        init(stage);
    }

    private void init(Stage stage) {
        Pane root = new Pane();

        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawRectangels(gc);

        gc.setFill(Color.BLUE);
        gc.fillRect(0, 50, 0, 50);

        root.getChildren().addAll(canvas);

        Scene scene = new Scene(root, width, height, Color.WHITESMOKE);

        stage.setTitle("Game of Life");
        stage.setScene(scene);
        stage.show();
    }

    private void drawRectangels(GraphicsContext gc) {

    }

    public static void main(String[] args) {
        System.out.println("Test ");
        launch(args);
    }
}
