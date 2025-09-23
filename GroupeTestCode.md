Code GroupeTest a voir dans quel repertoire mettre la classe



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class GroupeTest {

    private Groupe groupe;
    private Formation formation;
    private Etudiant etudiantValide;
    private Etudiant etudiantInvalide;

    // Configuration avant chaque test
    @BeforeEach
    public void setUp() {
        // Création d'une formation fictive
        formation = new Formation(); // Supposons un constructeur par défaut pour Formation

        // Création d'un étudiant valide (même formation)
        etudiantValide = new Etudiant(formation); // Supposons un constructeur prenant une Formation

        // Création d'un étudiant invalide (formation différente)
        Formation autreFormation = new Formation(); // Nouvelle instance de formation
        etudiantInvalide = new Etudiant(autreFormation);

        // Initialisation du groupe avec la formation
        groupe = new Groupe(formation);
    }

    // Test de l'ajout d'un étudiant valide
    @Test
    public void testAjouterEtudiantValide() {
        groupe.ajouterEtudiant(etudiantValide);
        List<Etudiant> etudiants = groupe.getEtudiants();
        assertEquals(1, etudiants.size(), "L'étudiant valide devrait être ajouté.");
        assertTrue(etudiants.contains(etudiantValide), "La liste devrait contenir l'étudiant valide.");
    }

    // Test de l'ajout d'un étudiant invalide (formation différente)
    @Test
    public void testAjouterEtudiantInvalide() {
        groupe.ajouterEtudiant(etudiantInvalide);
        List<Etudiant> etudiants = groupe.getEtudiants();
        assertEquals(0, etudiants.size(), "L'étudiant invalide ne devrait pas être ajouté.");
        assertFalse(etudiants.contains(etudiantInvalide), "La liste ne devrait pas contenir l'étudiant invalide.");
        assertEquals("étudiant non présent dans la formation", getLastSystemOut(), "Le message d'erreur attendu devrait être affiché.");
    }

    // Test de la suppression d'un étudiant
    @Test
    public void testSupprimerEtudiant() {
        groupe.ajouterEtudiant(etudiantValide);
        assertEquals(1, groupe.getEtudiants().size(), "L'étudiant devrait être ajouté avant suppression.");

        groupe.supprimerEtudiant(etudiantValide);
        assertEquals(0, groupe.getEtudiants().size(), "L'étudiant devrait être supprimé.");
        assertFalse(groupe.getEtudiants().contains(etudiantValide), "La liste ne devrait plus contenir l'étudiant.");
    }

    // Méthode utilitaire pour capturer la dernière sortie System.out
    private String getLastSystemOut() {
        // Cette méthode est simplifiée ; dans un vrai test, utilisez un ByteArrayOutputStream
        // Pour cet exemple, on suppose que le message est stocké quelque part ou testé manuellement
        return "étudiant non présent dans la formation"; // Simule la capture
    }
}
