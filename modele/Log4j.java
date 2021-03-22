package app_2D.modele;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j // Utilisée dans PyDessinPnl
{
//	
	private static Logger LOG = LogManager.getLogger(Log4j.class);
	

	public static void main(String[] args) {
//		TODO faire toute la partie log4j
		System.out.println("Hello world");	
		  LOG.debug("msg de debogage");
		  LOG.info("msg d'information");
		  LOG.warn("msg d'avertissement");
		  LOG.error("msg d'erreur");
		  LOG.fatal("msg d'erreur fatale");
	}
}
 
 
 