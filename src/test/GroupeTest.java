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
    private Matiere math;
    private Matiere francais;
    private Groupe groupe;

    private Etudiant etu1;
    private Etudiant etu2;
    private Etudiant etu3;
    private Etudiant etu4;

    @BeforeEach
    void setUp() throws Exception {
        formation = new Formation("INFO1");
        math = new Matiere("Mathématiques");
        francais = new Matiere("Français");


        // Coefficients
        formation.ajouterMatiere(math, 3.0);
        formation.ajouterMatiere(francais, 2.0);

        groupe = new Groupe(formation);

        etu1 = new Etudiant(new Identite("1","Dupont", "Arthur"), formation);
        etu2 = new Etudiant(new Identite("2", "Durand", "Astrid"), formation);
        etu3 = new Etudiant(new Identite("3", "Bernard", "Lucie"), formation);
        etu4 = new Etudiant(new Identite("4", "dupont", "Zoé"), formation);

        // initialiser la map de résultats avec des listes vides
        etu1.getResultat().put(math, new java.util.ArrayList<>());
        etu1.getResultat().put(francais, new java.util.ArrayList<>());

        etu2.getResultat().put(math, new java.util.ArrayList<>());
        etu2.getResultat().put(francais, new java.util.ArrayList<>());
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
        assertEquals(0.0, groupe.moyenneMatiere(math));
    }

    @Test
    void testMoyenneMatiereAvecNotes() throws Exception {
        etu1.ajouterNote(math, 10.0);
        etu1.ajouterNote(math, 14.0);

        etu2.ajouterNote(math, 16.0);

        groupe.ajouterEtudiant(etu1);
        groupe.ajouterEtudiant(etu2);

        // Moyenne de etu1 en maths = (10+14)/2 = 12
        // Moyenne de etu2 en maths = 16
        // Moyenne du groupe = (12 + 16)/2 = 14
        assertEquals(14.0, groupe.moyenneMatiere(math));
    }

    @Test
    void testMoyenneMatiereSansNotes() throws MatiereInexistanteException {
        groupe.ajouterEtudiant(etu1);
        groupe.ajouterEtudiant(etu2);

        // Aucun étudiant n'a de note → moyenne = 0
        assertEquals(0.0, groupe.moyenneMatiere(math));
    }
    @Test
    void testTriAlpha_OrdreBasique() {
        groupe.ajouterEtudiant(etu1); // Dupont
        groupe.ajouterEtudiant(etu2); // Durand
        groupe.ajouterEtudiant(etu3); // Bernard

        groupe.triAlpha();

        assertEquals("Bernard", groupe.getEtudiants().get(0).getIdentite().getNom());
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
        assertEquals("Bernard", groupe.getEtudiants().get(2).getIdentite().getNom());
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
}
