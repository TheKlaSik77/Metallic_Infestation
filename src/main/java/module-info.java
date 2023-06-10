module com.application.metallic_infastation {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens fr.iut.montreuil.metallic_infestation to javafx.fxml;
    exports fr.iut.montreuil.metallic_infestation;
    opens fr.iut.montreuil.metallic_infestation.controleur to javafx.fxml;
    exports fr.iut.montreuil.metallic_infestation.controleur;
    exports fr.iut.montreuil.metallic_infestation.modele;
    opens fr.iut.montreuil.metallic_infestation.modele to javafx.fxml;
}