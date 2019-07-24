import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AnimationTimerTest extends Application {

    Random random = new Random();

    private final Rectangle[] nodes = new Rectangle[30 * 30];
    private static long startTime = System.nanoTime();

    private boolean sceneExist = false;

    @Override
    public void start(final Stage primaryStage) {
        initScene(primaryStage);

        new AnimationTimer() {
            @Override
            public void handle(long now) {

                int randomIndex = random.nextInt(nodes.length - 1);
                System.out.println("Random index is: " + randomIndex);
                nodes[randomIndex].setFill(Color.RED);
                Scene newScene = new Scene(new Group(nodes), 300, 300, Color.WHITE);

                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                primaryStage.setScene(newScene);
            }
        }.start();
    }

    private void initScene(Stage primaryStage) {
        if(!sceneExist) {
            int n = 0;
            for (int i = 0; i < 300; i += 10) {
                for (int j = 0; j < 300; j += 10) {
                    nodes[n] = new Rectangle(i, j, 10, 10);
                    nodes[n].setFill(Color.WHITE);
                    n++;
                }
            }
            System.out.println("n is: " + n);

            Pane root = new Pane();
            root.getChildren().addAll(new Group(nodes));

            Scene scene = new Scene(root, 300, 300, Color.WHITE);
            primaryStage.setScene(scene);
            primaryStage.show();
            sceneExist = true;
        }
    }


}