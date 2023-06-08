package fr.iut.montreuil.metallic_infestation.modele;

public class TourelleAuto extends Tourelle{
    public TourelleAuto(Case position,Environnement env,Terrain terrain) {
        super(5,position,30,3,env,terrain, 0);
    }


    @Override
    public void infligerDegats() {
        if(getEnnemiVise() != null) {
            getEnnemiVise().decrementerPv(getDegats());
            System.out.println(getEnnemiVise().getPv());
        }
    }

    public Laser creerLaser() {
        if (env.destEstPresent(getEnnemiVise()) != true){
            return new Laser(this, getEnnemiVise());
        }
        return null;
    }
}
