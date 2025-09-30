package test;

import classes.*;

import exceptions.MatiereInexistanteException;
import exceptions.NoteInvalideException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
        etu1.ajouterNote(algo, 14.0);
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
        // moy etu algo = 9,66
        // Moyenne du groupe = 9.75
        assertEquals(10.66, groupe.moyenneMatiere(web),0.01);
        assertEquals(9.66, groupe.moyenneMatiere(algo),0.01);
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
    void testTriAlpha_OrdreBasique() {
        groupe.ajouterEtudiant(etu1); // Dupont
        groupe.ajouterEtudiant(etu3); // Durand
        groupe.ajouterEtudiant(etu2); // Bernard

        groupe.triAlpha();

        assertEquals("Donald", groupe.getEtudiants().get(0).getIdentite().getNom());
        assertEquals("Dupont",  groupe.getEtudiants().get(1).getIdentite().getNom());
        assertEquals("Durand",  groupe.getEtudiants().get(2).getIdentite().getNom());
    }



    @Test
    void testTriAlpha_NomsIdentiquesDifferentsPrenoms() {
        Etudiant e5 = new Etudiant(new Identite("5", "Dupont", "Alice"), formation);
        Etudiant e6 = new Etudiant(new Identite("6", "Dupont", "Zacharie"), formation);

        groupe.ajouterEtudiant(e5);
        groupe.ajouterEtudiant(e6);

        groupe.triAlpha();

        // Les deux ont le même nom → tri stable (par ordre d'insertion)
        assertEquals("Alice", groupe.getEtudiants().get(0).getIdentite().getPrenom());
        assertEquals("Zacharie", groupe.getEtudiants().get(1).getIdentite().getPrenom());
    }

    @Test
    void testTriAlpha_GroupeVide() {
        groupe.triAlpha();
        assertTrue(groupe.getEtudiants().isEmpty(), "Un groupe vide reste vide après tri");
    }

    @Test
    void testTriAlpha_UnSeulEtudiant() {
        groupe.ajouterEtudiant(etu1);
        groupe.triAlpha();
        assertEquals("Dupont", groupe.getEtudiants().get(0).getIdentite().getNom(),
                "Un seul étudiant ne change pas de position");
    }


    @Test
    void testTriAntiAlpha_OrdreBasique() {
        groupe.ajouterEtudiant(etu1); // Dupont
        groupe.ajouterEtudiant(etu2); // Durand
        groupe.ajouterEtudiant(etu3); // Bernard

        groupe.triAntiAlpha();

        assertEquals("Durand", groupe.getEtudiants().get(0).getIdentite().getNom());
        assertEquals("Dupont", groupe.getEtudiants().get(1).getIdentite().getNom());
        assertEquals("Donald", groupe.getEtudiants().get(2).getIdentite().getNom());
    }



    @Test
    void testTriAntiAlpha_NomsIdentiquesDifferentsPrenoms() {
        Etudiant e5 = new Etudiant(new Identite("5", "Dupont", "Alice"), formation);
        Etudiant e6 = new Etudiant(new Identite("6", "Dupont", "Zacharie"), formation);

        groupe.ajouterEtudiant(e5);
        groupe.ajouterEtudiant(e6);

        groupe.triAntiAlpha();

        // Même nom, tri stable, l'ordre reste celui d'insertion
        assertEquals("Alice", groupe.getEtudiants().get(0).getIdentite().getPrenom());
        assertEquals("Zacharie", groupe.getEtudiants().get(1).getIdentite().getPrenom());
    }

    @Test
    void testTriAntiAlpha_UnSeulEtudiant() {
        groupe.ajouterEtudiant(etu2);
        groupe.triAntiAlpha();

        assertEquals("Durand", groupe.getEtudiants().get(0).getIdentite().getNom(),
                "Un seul étudiant reste en place après tri inverse");
    }

    @Test
    void testGetFormation() {
        assertEquals(formation, groupe.getFormation(),
                "Le getter doit retourner la formation associée");
    }

    @Test
    void testGetEtudiants() {
        assertTrue(groupe.getEtudiants().isEmpty(),
                "Un groupe nouvellement créé doit avoir une liste d'étudiants vide");
        groupe.ajouterEtudiant(etu1);
        assertEquals(1, groupe.getEtudiants().size(),
                "La liste doit refléter les étudiants ajoutés");
    }

    @Test
    void testMoyenneGeneraleSansEtudiant() throws MatiereInexistanteException {
        assertEquals(0.0, groupe.moyenneGenerale(),
                "Sans étudiants, la moyenne générale doit être 0");
    }

    @Test
    void testMoyenneGeneraleAvecEtudiants() throws Exception {
        etu1.ajouterNote(web, 10.0);
        etu1.ajouterNote(reseau, 12.0);
        etu1.ajouterNote(algo, 14.0);

        etu2.ajouterNote(web, 8.0);
        etu2.ajouterNote(reseau, 9.0);
        etu2.ajouterNote(algo, 10.0);

        groupe.ajouterEtudiant(etu1);
        groupe.ajouterEtudiant(etu2);

        double moyenneAttendue = (etu1.moyenneGenerale() + etu2.moyenneGenerale()) / 2.0;
        assertEquals(moyenneAttendue, groupe.moyenneGenerale(), 0.01);
    }

/*
    @Test
    public void testListeVide() {
        List<Etudiant> result = groupe.triMerite();
        assertTrue(result.isEmpty(), "La liste vide doit rester vide");
        // Vérifie que le message est affiché
    }

    @Test
    public void testUnSeulEtudiant() {
        etu1 = new Etudiant(new Identite("1","Dupont", "Arthur"), formation);
        groupe.ajouterEtudiant(etu1);

        List<Etudiant> result = groupe.triMerite();
        assertEquals(1, result.size(), "Doit contenir un seul étudiant");
        assertEquals(etu1, result.get(0), "L'étudiant doit être le même");
        // Vérifie que le message est affiché (nécessite capture de sortie)
    }

    @Test
    public void testTriDecroissant() {
        groupe.ajouterEtudiant(etu1);
        groupe.ajouterEtudiant(etu2);
        groupe.ajouterEtudiant(etu3);
        List<Etudiant> result = groupe.triMerite();

        etu1 = new Etudiant(new Identite("1","Dupont", "Arthur"), formation);
        etu2 = new Etudiant(new Identite("2", "Durand", "Astrid"), formation);
        etu3 = new Etudiant(new Identite("3","Donald", "David"), formation);
        assertEquals(3, result.size(), "Doit contenir les 3 étudiants");
        assertEquals("Arthur Dupont (Moyenne : 12.0)", result.get(0).toString(), "Premier doit être Arthur");
        assertEquals("Astrid Durand (Moyenne : 10.0)", result.get(1).toString(), "Deuxième doit être Astrid");
        assertEquals("David Donald (Moyenne : 9.0)", result.get(2).toString(), "Troisième doit être David");
    }

    @Test
    public void testExceptionGenerale() throws MatiereInexistanteException, NoteInvalideException {
        // Simuler une erreur générale
        formation.ajouterMatiere(web, 1.0);
        formation.ajouterMatiere(reseau, 1.0);
        formation.ajouterMatiere(algo, 2.0);

        groupe = new Groupe(formation);

        Etudiant etu4 = new Etudiant(new Identite("5", "Dup", "Austerre"), formation);
        etu4.ajouterNote(web, -11.0);
        etu4.ajouterNote(reseau, 12.0);
        etu4.ajouterNote(algo, -9.0);

        groupe.ajouterEtudiant(etu4);
        groupe.ajouterEtudiant(etu2);
        List<Etudiant> result = groupe.triMerite();

        assertEquals(2, result.size(), "Doit contenir les 2 étudiants");
        System.out.println("Résultat avec exception générale : " + result);

    }

 */
}
