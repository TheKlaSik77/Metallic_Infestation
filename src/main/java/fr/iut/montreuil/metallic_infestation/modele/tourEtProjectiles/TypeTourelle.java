package fr.iut.montreuil.metallic_infestation.modele.tourEtProjectiles;

import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Case;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Environnement;
import fr.iut.montreuil.metallic_infestation.modele.utilitaire.Terrain;

public enum TypeTourelle {

    TourelleSemi {
        @Override
        public Tourelle creerTourelle(Case c) {
            return new TourelleSemi(c);
        }
    },
    TourelleAuto {
        @Override
        public Tourelle creerTourelle(Case c) {
            return new TourelleAuto(c);
        }
    },
    TourelleMissiles {
        @Override
        public Tourelle creerTourelle(Case c) {
            return new TourelleMissiles(c);
        }
    };

    public abstract Tourelle creerTourelle(Case c);
}
