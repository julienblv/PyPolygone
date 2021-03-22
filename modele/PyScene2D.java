package app_2D.modele;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import java.util.List;
import app_2D.vue.vecteur;
import app_2D.vue.GraTupple2d;
import app_2D.vue.Trait;

public class PyScene2D
{
	//liste des polygones
	private List<PyPolygone2D> lesPolygones = new ArrayList<>();
	private List<Trait> traits = new ArrayList<>();
	
	public PyScene2D()
	{
		
	}
	
	//liste des traits
	
	//on recupere les traits
	public List<Trait> getLesTraits() {
		return traits;
	}
	//on ajoute le trait a la liste
	public void AddTraits(Trait t) {
		traits.add(t);
	}
	
	public void addPolygone(PyPolygone2D polygone)
	{
		lesPolygones.add(polygone);
	}
	
	//on supprime tout
	public void clearScene()
	{
		traits.clear();
		lesPolygones.clear();
	}

	public List<PyPolygone2D> getLesPolygones() {
		return lesPolygones;
	}

}
