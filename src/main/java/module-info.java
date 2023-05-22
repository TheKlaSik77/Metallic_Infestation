module com.application.metallic_infastation {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens fr.iut.montreuil.metallic_infastation to javafx.fxml;
    exports fr.iut.montreuil.metallic_infastation;
    opens fr.iut.montreuil.metallic_infastation.controleur to javafx.fxml;
    exports fr.iut.montreuil.metallic_infastation.controleur;
}