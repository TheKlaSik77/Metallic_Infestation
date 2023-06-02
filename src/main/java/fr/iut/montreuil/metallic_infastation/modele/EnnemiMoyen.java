package fr.iut.montreuil.metallic_infastation.modele;

public class EnnemiMoyen extends Ennemi{
        public EnnemiMoyen(ParcoursBFS parcoursBFS, Terrain terrain) {
            super(5, 2,3, parcoursBFS, terrain);
        }

        @Override
        public String toString() {
            return "EnnemiMoyen" + super.toString();
        }
    }
