import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;

public class pieChart extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

//        create pie chart data

        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("cricket" + " 90%",90),
                new PieChart.Data("basketball" + " 40%",40),
                new PieChart.Data("tennis" + " 10%",10)
        );

//        create pie chart and assign the data
        PieChart pChart = new PieChart(pieData);

        pChart.setTitle("Most Playable Sports ");

//        animation parameters

        Group root = new Group(pChart);
        Scene scene = new Scene(root,1000,1000);
        primaryStage.setTitle("demo Piechart");
        primaryStage.setScene(scene);
        primaryStage.show();

//        store PiChart as image to below location

        saveAsPng(scene,"/Users/sahilkairon/Desktop/images.png");




    }

    public void saveAsPng(Scene scene, String path) {
        WritableImage image = scene.snapshot(null);
        File file = new File(path);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
