package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;

public enum TypeTourelle {

    TourelleSemi {
        @Override
        public Tourelle creerTourelle(Case c, Environnement environnement) {
            return new TourelleSemi(c, environnement);
        }
    },
    TourelleAuto {
        @Override
        public Tourelle creerTourelle(Case c, Environnement environnement) {
            return new TourelleAuto(c, environnement);
        }
    },
    TourelleMissiles {
        @Override
        public Tourelle creerTourelle(Case c, Environnement environnement) {
            return new TourelleMissiles(c, environnement);
        }
    };

    public abstract Tourelle creerTourelle(Case c, Environnement environnement);
}
