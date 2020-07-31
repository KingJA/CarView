package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class Controller implements Initializable{
    @FXML
    private JFXTextField et_password;

    @FXML
    private JFXButton btn_login;

    @FXML
    private JFXTextField et_name;

    @FXML
    void onLogin(ActionEvent event) {
        System.out.println("登录");

    }
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize");
    }
}
