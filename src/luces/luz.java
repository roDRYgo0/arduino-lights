package luces;

import java.util.logging.Level;
import java.util.logging.Logger;
import panamahitek.Arduino.PanamaHitek_Arduino;

public class luz {

    private String stateOn;
    private String stateOff;
    private boolean state;
    private PanamaHitek_Arduino arduino;

    public PanamaHitek_Arduino getArduino() {
        return arduino;
    }

    public void setArduino(PanamaHitek_Arduino arduino) {
        this.arduino = arduino;
    }

    public String getStateOn() {
        return stateOn;
    }

    public void setStateOn(String stateOn) {
        this.stateOn = stateOn;
    }

    public String getStateOff() {
        return stateOff;
    }

    public void setStateOff(String stateOff) {
        this.stateOff = stateOff;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public luz(String stateOn, String stateOff, boolean state, PanamaHitek_Arduino arduino) {
        this.stateOn = stateOn;
        this.stateOff = stateOff;
        this.state = state;
        this.arduino = arduino;
        lightTurnOff();
    }

    public void lightTurnOff() {
        try {
            arduino.sendData(stateOff);
            System.out.println(stateOff);
            state = false;
        } catch (Exception ex) {
            Logger.getLogger(luz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lightTurnOn() {
        try {
            arduino.sendData(stateOn);
            System.out.println(stateOn);
            state = false;
        } catch (Exception ex) {
            Logger.getLogger(luz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lightTurn() {
        try {
            if (state) {
                arduino.sendData(stateOn);
                state = !state;
            } else {
                arduino.sendData(stateOff);
                state = !state;
            }
        } catch (Exception ex) {
            Logger.getLogger(luz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
