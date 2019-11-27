package luces;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import panamahitek.Arduino.PanamaHitek_Arduino;

public class FXMLDocumentController implements Initializable {

    boolean reflector;

    luz left;
    luz right;
    luz focus;
    luz pot;
    luz grotto;
    luz extra1;
    luz extra2;
    luz extra3;
    luz extra4;

    String toggleOff = "-fx-background-color: rgb(255, 61, 0);";
    String toggleOn = "-fx-background-color: rgb(139, 195, 74);";
    String fontOff = "-fx-color: rgb(255, 61, 0);";
    String fontOn = "-fx-color: rgb(139, 195, 74);";
    PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();

    //<editor-fold defaultstate="collapsed" desc="FXML var">
    @FXML
    private Label conection;
    @FXML
    private Pane panel1;
    @FXML
    private Pane panel2;
    @FXML
    private Pane panel3;
    @FXML
    private Pane panel4;
    @FXML
    private Pane panel5;
    @FXML
    private Pane panel6;
    @FXML
    private Pane panel7;
    @FXML
    private Pane panel8;
    @FXML
    private Pane panel9;
    @FXML
    private JFXButton btnReflector;
    @FXML
    private JFXButton btnOnAll;
    @FXML
    private JFXButton btnOffAll;
    @FXML
    private JFXButton btnConect;
    @FXML
    private JFXComboBox cmbCom;
    @FXML
    private JFXToggleButton tggCorte1;
    @FXML
    private JFXToggleButton tggCorte2;
    @FXML
    private JFXToggleButton tggCorte3;
    @FXML
    private JFXToggleButton tggCorte4;
    @FXML
    private JFXToggleButton tggCorte5;
    @FXML
    private JFXToggleButton tggCorte6;
    @FXML
    private JFXToggleButton tggCorte7;
    @FXML
    private JFXToggleButton tggCorte8;
    @FXML
    private JFXToggleButton tggCorte9;
    @FXML
    private JFXToggleButton tggLightOne;
    @FXML
    private JFXToggleButton tggLightTwo;
    @FXML
    private JFXToggleButton tggLightFocus;
    @FXML
    private JFXToggleButton tggLightPot;
    @FXML
    private JFXToggleButton tggLightGrotto;
    @FXML
    private JFXToggleButton tggLightExtra1;
    @FXML
    private JFXToggleButton tggLightExtra2;
    @FXML
    private JFXToggleButton tggLightExtra3;
    @FXML
    private JFXToggleButton tggLightExtra4;
    @FXML
    private JFXToggleButton tggTurn;
    @FXML
    private JFXToggleButton tggCorte;
    @FXML
    private ImageView imgLightOne;
    @FXML
    private ImageView imgLightTwo;
    @FXML
    private ImageView imgLightFocus;
    @FXML
    private ImageView imgLightPot;
    @FXML
    private ImageView imgLightGrotto;
    @FXML
    private ImageView imgLightExtra1;
    @FXML
    private ImageView imgLightExtra2;
    @FXML
    private ImageView imgLightExtra3;
    @FXML
    private ImageView imgLightExtra4;

//</editor-fold>
    SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {

                if (arduino.printMessage() != null) {
                    arduino.sendData("z");
                    Platform.runLater(() -> {
                        conection.setText("Conectado");
                        conection.setTextFill(Color.web("#8bc34a"));
                        enable();
                        initializeLight();
                    });

                }
            } catch (Exception ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    //<editor-fold defaultstate="collapsed" desc="MouseEvent">
    @FXML
    private void handlelightOnePn(MouseEvent event) {
        tggLightOne.setSelected(!tggLightOne.isSelected());
        handlelightOne(null);
    }

    @FXML
    private void handlelightTwoPn(MouseEvent event) {
        tggLightTwo.setSelected(!tggLightTwo.isSelected());
        handlelightTwo(null);
    }

    @FXML
    private void handlelightFocusPn(MouseEvent event) {
        tggLightFocus.setSelected(!tggLightFocus.isSelected());
        handlelightFocus(null);
    }

    @FXML
    private void handlelightPotPn(MouseEvent event) {
        tggLightPot.setSelected(!tggLightPot.isSelected());
        handlelightPot(null);
    }

    @FXML
    private void handlelightGrottoPn(MouseEvent event) {
        tggLightGrotto.setSelected(!tggLightGrotto.isSelected());
        handlelightGrotto(null);
    }
    
    @FXML
    private void handlelightExtra1Pn(MouseEvent event) {
        tggLightExtra1.setSelected(!tggLightExtra1.isSelected());
        handlelightExtra1(null);
    }
    
    @FXML
    private void handlelightExtra2Pn(MouseEvent event) {
        tggLightExtra2.setSelected(!tggLightExtra2.isSelected());
        handlelightExtra2(null);
    }
    
    @FXML
    private void handlelightExtra3Pn(MouseEvent event) {
        tggLightExtra3.setSelected(!tggLightExtra3.isSelected());
        handlelightExtra3(null);
    }
    
    @FXML
    private void handlelightExtra4Pn(MouseEvent event) {
        tggLightExtra4.setSelected(!tggLightExtra4.isSelected());
        handlelightExtra4(null);
    }
//</editor-fold>

    @FXML
    private void handlelightOne(ActionEvent event) {
        if (tggLightOne.isSelected()) {
            imgLightOne.setOpacity(1);
            left.lightTurnOn();
            if (tggLightOne.isSelected() && tggLightTwo.isSelected()) {
                reflector = false;
                handleReflector(null);
            }

        } else {
            imgLightOne.setOpacity(0.2);
            left.lightTurnOff();
            if (!tggLightOne.isSelected() && !tggLightTwo.isSelected()) {
                reflector = true;
                handleReflector(null);
            }
        }

    }

    @FXML
    private void handlelightTwo(ActionEvent event) {
        if (tggLightTwo.isSelected()) {
            imgLightTwo.setOpacity(1);
            right.lightTurnOn();
            if (tggLightOne.isSelected() && tggLightTwo.isSelected()) {
                reflector = false;
                handleReflector(null);
            }

        } else {
            imgLightTwo.setOpacity(0.2);
            right.lightTurnOff();
            if (!tggLightOne.isSelected() && !tggLightTwo.isSelected()) {
                reflector = true;
                handleReflector(null);
            }
        }

    }

    @FXML
    private void handlelightFocus(ActionEvent event) {
        if (tggLightFocus.isSelected()) {
            imgLightFocus.setOpacity(1);
            focus.lightTurnOn();
        } else {
            imgLightFocus.setOpacity(0.2);
            focus.lightTurnOff();
        }

    }

    @FXML
    private void handlelightPot(ActionEvent event) {
        if (tggLightPot.isSelected()) {
            imgLightPot.setOpacity(1);
            pot.lightTurnOn();

        } else {
            imgLightPot.setOpacity(0.2);
            pot.lightTurnOff();
        }

    }

    @FXML
    private void handlelightGrotto(ActionEvent event) {
        if (tggLightGrotto.isSelected()) {
            imgLightGrotto.setOpacity(1);
            grotto.lightTurnOn();
        } else {
            imgLightGrotto.setOpacity(0.2);
            grotto.lightTurnOff();
        }

    }

    @FXML
    private void handlelightExtra1(ActionEvent event) {
        if (tggLightExtra1.isSelected()) {
            imgLightExtra1.setOpacity(1);
            extra1.lightTurnOn();
        } else {
            imgLightExtra1.setOpacity(0.2);
            extra1.lightTurnOff();
        }
    }

    @FXML
    private void handlelightExtra2(ActionEvent event) {
        if (tggLightExtra2.isSelected()) {
            imgLightExtra2.setOpacity(1);
            extra2.lightTurnOn();
        } else {
            imgLightExtra2.setOpacity(0.2);
            extra2.lightTurnOff();
        }
    }

    @FXML
    private void handlelightExtra3(ActionEvent event) {
        if (tggLightExtra3.isSelected()) {
            imgLightExtra3.setOpacity(1);
            extra3.lightTurnOn();
        } else {
            imgLightExtra3.setOpacity(0.2);
            extra3.lightTurnOff();
        }
    }

    @FXML
    private void handlelightExtra4(ActionEvent event) {
        if (tggLightExtra4.isSelected()) {
            imgLightExtra4.setOpacity(1);
            extra4.lightTurnOn();
        } else {
            imgLightExtra4.setOpacity(0.2);
            extra4.lightTurnOff();
        }
    }

    @FXML
    private void handleReflector(ActionEvent event) {
        if (reflector) {
            btnReflector.setStyle(toggleOff);
            btnReflector.setText("Encender");
            tggLightOne.setSelected(false);
            tggLightTwo.setSelected(false);
            imgLightOne.setOpacity(0.2);
            imgLightTwo.setOpacity(0.2);
            left.lightTurnOff();
            right.lightTurnOff();
            reflector = !reflector;
        } else {
            btnReflector.setStyle(toggleOn);
            btnReflector.setText("Apagar");
            tggLightOne.setSelected(true);
            tggLightTwo.setSelected(true);
            imgLightOne.setOpacity(1);
            imgLightTwo.setOpacity(1);
            left.lightTurnOn();
            right.lightTurnOn();
            reflector = !reflector;
        }
    }

    @FXML
    private void handleConect(ActionEvent event) {
        System.out.println(cmbCom.getValue());
        try {
            if (arduino.getSerialPorts().isEmpty()) {
                arduino.killArduinoConnection();
            }
            arduino.arduinoRXTX((String) cmbCom.getValue(), 9600, listener);
            offAll();

        } catch (Exception ex) {

        }
    }

    @FXML
    public void offAll() {
        imgLightFocus.setOpacity(0.2);
        imgLightPot.setOpacity(0.2);
        imgLightGrotto.setOpacity(0.2);
        imgLightExtra1.setOpacity(0.2);
        imgLightExtra2.setOpacity(0.2);
        imgLightExtra3.setOpacity(0.2);
        imgLightExtra4.setOpacity(0.2);
        reflector = true;
        tggLightFocus.setSelected(false);
        tggLightPot.setSelected(false);
        tggLightGrotto.setSelected(false);
        tggLightExtra1.setSelected(false);
        tggLightExtra2.setSelected(false);
        tggLightExtra3.setSelected(false);
        tggLightExtra4.setSelected(false);
        handleReflector(null);
        try {
            arduino.sendData("5");
            arduino.sendData("7");
            arduino.sendData("9");
            arduino.sendData("b");
            arduino.sendData("d");
            arduino.sendData("f");
            arduino.sendData("h");
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void onAll() {
        imgLightFocus.setOpacity(1);
        imgLightPot.setOpacity(1);
        imgLightGrotto.setOpacity(1);
        imgLightExtra1.setOpacity(1);
        imgLightExtra2.setOpacity(1);
        imgLightExtra3.setOpacity(1);
        imgLightExtra4.setOpacity(1);
        reflector = false;
        tggLightFocus.setSelected(true);
        tggLightPot.setSelected(true);
        tggLightGrotto.setSelected(true);
        tggLightExtra1.setSelected(true);
        tggLightExtra2.setSelected(true);
        tggLightExtra3.setSelected(true);
        tggLightExtra4.setSelected(true);
        handleReflector(null);
        try {
            arduino.sendData("4");
            arduino.sendData("6");
            arduino.sendData("8");
            arduino.sendData("a");
            arduino.sendData("c");
            arduino.sendData("e");
            arduino.sendData("g");
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void enable() {
        panel1.setDisable(false);
        panel2.setDisable(false);
        panel3.setDisable(false);
        panel4.setDisable(false);
        panel5.setDisable(false);
        panel6.setDisable(false);
        panel7.setDisable(false);
        panel8.setDisable(false);
        panel9.setDisable(false);
        tggCorte1.setDisable(false);
        tggCorte2.setDisable(false);
        tggCorte3.setDisable(false);
        tggCorte4.setDisable(false);
        tggCorte5.setDisable(false);
        tggCorte6.setDisable(false);
        tggCorte7.setDisable(false);
        tggCorte8.setDisable(false);
        tggCorte9.setDisable(false);
        btnReflector.setDisable(false);
        btnOnAll.setDisable(false);
        btnOffAll.setDisable(false);
        btnConect.setDisable(true);
        cmbCom.setDisable(true);
        tggTurn.setDisable(false);
        tggCorte.setDisable(false);
    }

    @FXML
    private void handleTurn(ActionEvent event) {
        if(tggTurn.isSelected())
            onAll();
        else
            offAll();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnReflector.setStyle(toggleOff);
        conection.setTextFill(Color.web("#fb3d00"));
        btnReflector.setText("Encender");
        tggLightOne.setSelected(false);
        tggLightTwo.setSelected(false);
        cmbCom.getItems().addAll("COM1", "COM2", "COM3", "COM4", "COM5", "COM6");
    }

    void initializeLight() {
        left = new luz("0", "1", false, arduino);
        right = new luz("2", "3", false, arduino);
        focus = new luz("4", "5", false, arduino);
        pot = new luz("6", "7", false, arduino);
        grotto = new luz("8", "9", false, arduino);
        extra1 = new luz("a", "b", false, arduino);
        extra2 = new luz("c", "d", false, arduino);
        extra3 = new luz("e", "f", false, arduino);
        extra4 = new luz("g", "h", false, arduino);
    }
}
