package test;

import classes.*;

import exceptions.MatiereInexistanteException;
import exceptions.NoteInvalideException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class GroupeTest {

    private Formation formation;
    private Matiere web;
    private Matiere reseau;
    private Matiere algo;
    private Groupe groupe;

    private Etudiant etu1;
    private Etudiant etu2;
    private Etudiant etu3;

    @BeforeEach
    void setUp() throws Exception {
        formation = new Formation("INFO1");
        web = new Matiere("Web");
        reseau = new Matiere("Reseau");
        algo = new Matiere("Algo");



        // Coefficients
        formation.ajouterMatiere(web, 1.0);
        formation.ajouterMatiere(reseau, 1.0);
        formation.ajouterMatiere(algo, 2.0);

        groupe = new Groupe(formation);

        etu1 = new Etudiant(new Identite("1","Dupont", "Arthur"), formation);
        etu2 = new Etudiant(new Identite("2", "Durand", "Astrid"), formation);
        etu3 = new Etudiant(new Identite("3","Donald", "David"), formation);

        // initialiser la map de résultats avec des listes vides
        etu1.getResultat().put(web, new java.util.ArrayList<>());
        etu1.getResultat().put(reseau, new java.util.ArrayList<>());
        etu1.getResultat().put(algo, new java.util.ArrayList<>());

        etu2.getResultat().put(web, new java.util.ArrayList<>());
        etu2.getResultat().put(reseau, new java.util.ArrayList<>());
        etu2.getResultat().put(algo, new java.util.ArrayList<>());

        etu3.getResultat().put(web, new java.util.ArrayList<>());
        etu3.getResultat().put(reseau, new java.util.ArrayList<>());
        etu3.getResultat().put(algo, new java.util.ArrayList<>());
    }

    @Test
    void testAjouterEtudiantBonneFormation() {
        groupe.ajouterEtudiant(etu1);
        assertEquals(1, groupe.getEtudiants().size());
    }

    @Test
    void testAjouterEtudiantMauvaiseFormation() {
        Formation autreFormation = new Formation("INFO2");
        Etudiant etuMauvais = new Etudiant(new Identite("3","Martin", "Claire"), autreFormation);

        groupe.ajouterEtudiant(etuMauvais);
        assertEquals(0, groupe.getEtudiants().size(),
                "L'étudiant d'une autre formation ne doit pas être ajouté");
    }

    @Test
    void testSupprimerEtudiant() {
        groupe.ajouterEtudiant(etu1);
        groupe.supprimerEtudiant(etu1);
        assertTrue(groupe.getEtudiants().isEmpty());
    }

    @Test
    void testMoyenneMatiereSansEtudiant() throws MatiereInexistanteException {
        assertEquals(0.0, groupe.moyenneMatiere(web));
    }

    @Test
    void testMoyenneMatiereAvecNotes() throws Exception {
        etu1.ajouterNote(web, 12.0);
        etu1.ajouterNote(web, 10.0);
        etu1.ajouterNote(web, 11.0);
        etu1.ajouterNote(web, 15.0);
        etu1.ajouterNote(reseau, 12.0);
        etu1.ajouterNote(reseau, 10.0);
        etu1.ajouterNote(reseau, 11.0);
        etu1.ajouterNote(algo, 9.0);
        etu1.ajouterNote(algo, 8.0);
        etu1.ajouterNote(algo, 10.0);



        etu2.ajouterNote(web, 9.0);
        etu2.ajouterNote(web, 8.0);
        etu2.ajouterNote(web, 10.0);
        etu2.ajouterNote(reseau, 9.0);
        etu2.ajouterNote(reseau, 8.0);
        etu2.ajouterNote(reseau, 10.0);
        etu2.ajouterNote(algo, 9.0);
        etu2.ajouterNote(algo, 8.0);
        etu2.ajouterNote(algo, 10.0);



        etu3.ajouterNote(web, 12.0);
        etu3.ajouterNote(web, 10.0);
        etu3.ajouterNote(web, 11.0);
        etu3.ajouterNote(reseau, 12.0);
        etu3.ajouterNote(reseau, 10.0);
        etu3.ajouterNote(reseau, 11.0);
        etu3.ajouterNote(algo, 9.0);
        etu3.ajouterNote(algo, 8.0);
        etu3.ajouterNote(algo, 10.0);

        groupe.ajouterEtudiant(etu1);
        groupe.ajouterEtudiant(etu2);
        groupe.ajouterEtudiant(etu3);

        // Moyenne de etu1 en web = 12
        // Moyenne de etu2 en web = 9
        // moyenne de etu3 en web = 11

        // moy etu1 reseau = 11
        // moy etu algo = 9
        // Moyenne du groupe = 9.75
        assertEquals(10.66, groupe.moyenneMatiere(web),0.01);
    }

    @Test
    void testMoyenneMatiereSansNotes() throws MatiereInexistanteException {
        groupe.ajouterEtudiant(etu1);
        groupe.ajouterEtudiant(etu2);

        // Aucun étudiant n'a de note → moyenne = 0
        assertEquals(0.0, groupe.moyenneMatiere(web),0.01);
    }

    void testMoyenneGeneral() throws MatiereInexistanteException {
        groupe.ajouterEtudiant(etu1);
        groupe.ajouterEtudiant(etu2);
        groupe.ajouterEtudiant(etu3);

        assertEquals(10.66, groupe.moyenneMatiere(web),0.01);

    }

    @Test
    void testMoyenneGeneraleSansEtudiants() throws MatiereInexistanteException {
        // Aucun étudiant dans le groupe
        assertEquals(0.0, groupe.moyenneGenerale(), 0.01,
                "La moyenne générale doit être 0 si aucun étudiant n'est présent");
    }

    @Test
    void testMoyenneGeneraleSansNotes() throws MatiereInexistanteException {
        groupe.ajouterEtudiant(etu1);
        groupe.ajouterEtudiant(etu2);

        // Aucun étudiant n'a de notes
        assertEquals(0.0, groupe.moyenneGenerale(), 0.01,
                "La moyenne générale doit être 0 si aucun étudiant n'a de notes");
    }

    @Test
    void testMoyenneGeneraleAvecNotes() throws Exception {
        // etu1
        etu1.ajouterNote(web, 12.0);
        etu1.ajouterNote(reseau, 10.0);
        etu1.ajouterNote(algo, 14.0);

        // etu2
        etu2.ajouterNote(web, 8.0);
        etu2.ajouterNote(reseau, 9.0);
        etu2.ajouterNote(algo, 10.0);

        // etu3
        etu3.ajouterNote(web, 11.0);
        etu3.ajouterNote(reseau, 12.0);
        etu3.ajouterNote(algo, 9.0);

        groupe.ajouterEtudiant(etu1);
        groupe.ajouterEtudiant(etu2);
        groupe.ajouterEtudiant(etu3);

        // Vérification du calcul :
        // etu1 : moyenne générale = (12*1 + 10*1 + 14*2) / 4 = 12.5
        // etu2 : moyenne générale = (8*1 + 9*1 + 10*2) / 4 = 9.25
        // etu3 : moyenne générale = (11*1 + 12*1 + 9*2) / 4 = 10.25
        // Moyenne du groupe = (12.5 + 9.25 + 10.25) / 3 = 10.66...

        assertEquals(10.66, groupe.moyenneGenerale(), 0.01,
                "La moyenne générale du groupe doit être correctement calculée");
    }

}
