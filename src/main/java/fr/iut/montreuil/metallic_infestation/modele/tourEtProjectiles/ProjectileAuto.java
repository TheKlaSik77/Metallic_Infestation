package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;


import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.effets.DegatIndividuel;
import fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles.effets.Effet;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Point;
import fr.iut.montreuil.metallic_infestation.modele.ennemis.Ennemi;

public class ProjectileAuto extends Projectile {
    public ProjectileAuto(Point coordonnesDepart, Ennemi ennemiVise) {
        super(new DegatIndividuel(10), coordonnesDepart, 1, ennemiVise);
    }
}

