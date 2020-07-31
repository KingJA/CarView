package sample;

import com.jfoenix.controls.JFXButton;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImageController implements Initializable {

    private FileChooser fileChooser;
    private File srcFile;

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize");
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("图片文件", "*.png", "*.jpg", "*.bmp", "*" +
                ".gif"));
    }

    @FXML
    private JFXButton btn_left;

    @FXML
    private JFXButton btn_choose;

    @FXML
    private JFXButton btn_right;

    @FXML
    private ImageView iv_src;

    private Stage stage;

    private Stage getStage() {
        if (stage == null) {
            stage = (Stage) btn_choose.getScene().getWindow();
        }
        return stage;
    }

    private String srcPath = "g:/temp/1.jpg";
    private String tempDirPath = "g:/temp/";
    private String tempFilePath = "";
    private String tempPath2 = "g:/temp/2.jpg";
    private File tempFile;

    @FXML
    void chooseImage(ActionEvent event) {
        System.out.println("chooseImage");
        srcFile = fileChooser.showOpenDialog(getStage());
        if (srcFile != null) {
            iv_src.setImage(new Image(srcFile.toURI().toString()));
            srcPath = srcFile.getAbsolutePath();
            // 1.复制原文件
            System.out.println("原文件:" + srcPath);
            String fileName = srcFile.getName();
            System.out.println("文件名:" + fileName);
            tempFilePath = tempDirPath + fileName;
            tempFile =new File(tempFilePath);
            FileUtil.copyFile(srcPath, tempFilePath);
            System.out.println("备份文件路径:" + tempFilePath);
        }
    }


    @FXML
    void onRight(ActionEvent event) {
        try {
            Thumbnails.of(tempFile)
                    .scale(1.0f)
                    .rotate(90)
                    .toFile(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        iv_src.setImage(new Image(tempFile.toURI().toString()));
//        iv_src.setImage(new Image(new File(tempFilePath).toURI().toString()));
    }

    @FXML
    void onLeft(ActionEvent event) {
        try {
            Thumbnails.of(srcPath)
                    .size(1280, 1024)
                    .rotate(-90)
                    .toFile(tempPath2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void onCompress(ActionEvent event) {

        try {
            Thumbnails.of(srcFile.getAbsolutePath())
                    .scale(0.25f)
                    .toFile(srcPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void onOpen(ActionEvent event) {
        System.out.println("onOpen");
        try {
            Runtime.getRuntime().exec("cmd /c start explorer G:\\temp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
