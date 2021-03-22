package app_2D.modele.algos;

import app_2D.modele.PyPolygone2D;
import app_2D.modele.PyScene2D;


public abstract class PyAlgoBase
{
	PyScene2D scene2D;
	
	PyAlgoBase(PyScene2D _scene2D)
	{
		scene2D = _scene2D;
	}
	
}
