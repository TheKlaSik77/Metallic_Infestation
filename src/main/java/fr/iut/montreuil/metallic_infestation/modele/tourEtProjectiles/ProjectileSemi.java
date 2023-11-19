package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;
import fr.iut.montreuil.metallic_infestation.modele.effets.DegatIndividuel;
import fr.iut.montreuil.metallic_infestation.modele.effets.Effet;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Point;

public class ProjectileSemi extends Projectile {

    public ProjectileSemi(Point positionDepart, Ennemi ennemiVise){
        super(new DegatIndividuel(5), positionDepart,2,ennemiVise);
    }


}
