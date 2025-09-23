package test;
import classes.*;
import exceptions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FormationTest {
    
    private Formation formation;
    private Matiere matiereMath;
    private Matiere matierePhysique;
    
    @BeforeEach
    void setUp() {
        formation = new Formation("INFO1");
        matiereMath = new Matiere("Mathématiques");
        matierePhysique = new Matiere("Physique");
    }
    
    @Test
    void testConstructeur() {
        assertNotNull(formation);
        assertEquals("INFO1", formation.getIdFormation());
        assertNotNull(formation.getMatieres());
        assertTrue(formation.getMatieres().isEmpty());
    }
    
    @Test
    void testAjouterMatiere() {
        formation.ajouterMatiere(matiereMath, 2.0);
        
        assertEquals(1, formation.getMatieres().size());
        assertTrue(formation.getMatieres().contains(matiereMath));
        assertEquals(2.0, formation.getCoefficient(matiereMath), 0.001);
    }
    
    @Test
    void testAjouterPlusieursMatieres() {
        formation.ajouterMatiere(matiereMath, 2.0);
        formation.ajouterMatiere(matierePhysique, 1.5);
        
        assertEquals(2, formation.getMatieres().size());
        assertTrue(formation.getMatieres().contains(matiereMath));
        assertTrue(formation.getMatieres().contains(matierePhysique));
    }
    
    @Test
    void testSupprimerMatiere() {
        formation.ajouterMatiere(matiereMath, 2.0);
        formation.ajouterMatiere(matierePhysique, 1.5);
        
        formation.supprimerMatiere(matiereMath);
        
        assertEquals(1, formation.getMatieres().size());
        assertFalse(formation.getMatieres().contains(matiereMath));
        assertTrue(formation.getMatieres().contains(matierePhysique));
    }
    
    @Test
    void testGetCoefficientMatiereExistante() {
        formation.ajouterMatiere(matiereMath, 2.0);
        
        double coef = formation.getCoefficient(matiereMath);
        assertEquals(2.0, coef, 0.001);
    }
    
    @Test
    void testGetCoefficientMatiereInexistante() {
        double coef = formation.getCoefficient(matiereMath);
        assertEquals(-1, coef, 0.001);
    }
    
    @Test
    void testGetMatiereNamesSet() {
        formation.ajouterMatiere(matiereMath, 2.0);
        formation.ajouterMatiere(matierePhysique, 1.5);
        
        java.util.HashSet<String> nomsMatieres = formation.getMatiereNamesSet();
        
        assertEquals(2, nomsMatieres.size());
        assertTrue(nomsMatieres.contains("Mathématiques"));
        assertTrue(nomsMatieres.contains("Physique"));
    }
    
    @Test
    void testGetMatiereNamesSetFormationVide() {
        java.util.HashSet<String> nomsMatieres = formation.getMatiereNamesSet();
        
        assertTrue(nomsMatieres.isEmpty());
    }
    
    @Test
    void testSetIdFormation() {
        formation.setIdFormation("INFO2");
        assertEquals("INFO2", formation.getIdFormation());
    }
    
    @Test
    void testEquals() {
        Formation formation1 = new Formation("INFO1");
        Formation formation2 = new Formation("INFO1");
        Formation formation3 = new Formation("INFO2");
        
        formation1.ajouterMatiere(matiereMath, 2.0);
        formation2.ajouterMatiere(matiereMath, 2.0);
        formation3.ajouterMatiere(matiereMath, 2.0);
        
        assertEquals(formation1, formation2);
        assertNotEquals(formation1, formation3);
    }
    
    @Test
    void testToString() {
        formation.ajouterMatiere(matiereMath, 2.0);
        
        String toString = formation.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("INFO1"));
        assertTrue(toString.contains("Mathématiques"));
    }
    
    @Test
    void testHashCode() {
        Formation formation1 = new Formation("INFO1");
        Formation formation2 = new Formation("INFO1");
        
        formation1.ajouterMatiere(matiereMath, 2.0);
        formation2.ajouterMatiere(matiereMath, 2.0);
        
        assertEquals(formation1.hashCode(), formation2.hashCode());
    }
}
