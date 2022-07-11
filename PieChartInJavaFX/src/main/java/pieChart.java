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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.Base64;

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


        System.out.println( saveAsPng(scene,"/Users/sahilkairon/Desktop/images.png"));





    }

    public String saveAsPng(Scene scene, String path) {
        WritableImage image = scene.snapshot(null);
        File file = new File(path);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);

//            below code will return the base 64 encoded String of the jave FX image of PieChart

            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read= 0;
            while( (read = fis.read(buffer)) > -1){
                baos.write(buffer, 0, read);
            }
            fis.close();
            baos.close();
            byte pgnBytes [] = baos.toByteArray();
            Base64.Encoder base64_enc = Base64.getEncoder();
            String base64_image = base64_enc.encodeToString(pgnBytes);

            return base64_image;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
