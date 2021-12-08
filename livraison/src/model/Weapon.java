package model;

import personnagesJeu.Personnage;
//Interface permettant de représenter une arme (dans notre cas les bombes et les mines)
public interface Weapon {
    public void detonation();
    public Personnage getOwner();
    public boolean isVisible();
}
