package fr.diginamic.recensement.services;


import java.util.List;
import java.util.Scanner;



import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.BornePopulationLettreException;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws BornePopulationLettreException {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();
		for (int i = 0; i < saisieMin.length(); i++) {
			if (Character.isLetter(saisieMin.charAt(i))) {
				throw new BornePopulationLettreException("Veuillez saisir un nombre pour la population minimale");
			}
		}

		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();
		for (int i = 0; i < saisieMax.length(); i++) {
			if (Character.isLetter(saisieMax.charAt(i))) {
				throw new BornePopulationLettreException("Veuillez saisir un nombre pour la population max");
			}
		}
		
		int min = Integer.parseInt(saisieMin) * 1000;

		int max = Integer.parseInt(saisieMax) * 1000;
		if (min<0 || max<0 || min>max){
			throw new BornePopulationLettreException("le nombre saisi n'est pas valide\nles nombres doivent être positif et le minimum doit etre supérieur au maximum");
			
		}

		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max)
					System.out.println(ville);
			}
		}
	}

}
