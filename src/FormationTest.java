import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FormationTest {
    
    @Test
    public void testAjouterMatiere() {
        Formation formation = new Formation("INFO001");
        Matiere java = new Matiere("Java");
        
        formation.ajouterMatiere(java, 3);
        
        assertEquals(3, formation.getCoefficient(java));
        assertTrue(formation.getCoefficient(java) > 0);
    }
    
    @Test
    public void testSupprimerMatiere() {
        Formation formation = new Formation("INFO001");
        Matiere java = new Matiere("Java");
        Matiere python = new Matiere("Python");
        
        formation.ajouterMatiere(java, 3);
        formation.ajouterMatiere(python, 2);
        
        formation.supprimerMatiere(java);
        
        // Vérifier que java n'existe plus
        assertThrows(Exception.class, () -> formation.getCoefficient(java));
        // Vérifier que python existe toujours
        assertEquals(2, formation.getCoefficient(python));
    }
    
    @Test
    public void testGetCoefficientMatiereExistante() {
        Formation formation = new Formation("INFO001");
        Matiere java = new Matiere("Java");
        
        formation.ajouterMatiere(java, 3);
        
        assertEquals(3, formation.getCoefficient(java));
    }
    
    @Test
    public void testGetCoefficientMatiereInexistante() {
        Formation formation = new Formation("INFO001");
        Matiere java = new Matiere("Java");
        Matiere python = new Matiere("Python");
        
        formation.ajouterMatiere(java, 3);
        
        // Doit lever une exception pour une matière non présente
        assertThrows(IllegalArgumentException.class, () -> formation.getCoefficient(python));
    }
    
    @Test
    public void testAjouterMatiereAvecCoefficientNegatif() {
        Formation formation = new Formation("INFO001");
        Matiere java = new Matiere("Java");
        
        // Doit lever une exception pour un coefficient négatif
        assertThrows(IllegalArgumentException.class, () -> formation.ajouterMatiere(java, -1));
    }
    
    @Test
    public void testAjouterMatiereDejaExistante() {
        Formation formation = new Formation("INFO001");
        Matiere java = new Matiere("Java");
        
        formation.ajouterMatiere(java, 3);
        
        // Doit lever une exception si on ajoute la même matière deux fois
        assertThrows(IllegalArgumentException.class, () -> formation.ajouterMatiere(java, 2));
    }
}
