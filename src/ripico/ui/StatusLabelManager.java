package ripico.ui;

import javafx.scene.control.Label;

public class StatusLabelManager {
    private Label labelStatus;

    public StatusLabelManager(Label labelStatus) {
        this.labelStatus = labelStatus;
    }

    public void setSuccessMessage(String msg) {
        labelStatus.setText(msg);
        labelStatus.setVisible(true);
        labelStatus.getStyleClass().add("successMessage");

    }

    public void setNeutralMessage(String msg) {
        labelStatus.setText(msg);
        labelStatus.setVisible(true);
        labelStatus.getStyleClass().add("neutralMessage");

    }

    public void setFailureMessage(String msg) {
        labelStatus.setText(msg);
        labelStatus.setVisible(true);
        labelStatus.getStyleClass().add("errorMessage");
    }

    public void clearStatus(){
        labelStatus.setText("");
        labelStatus.setVisible(false);
        labelStatus.getStyleClass().removeAll("successMessage", "neutralMessage", "errorMessage");
    }






}
