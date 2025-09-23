package test;
import classes.*;
import exceptions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class EtudiantTest {
    
    private Etudiant etudiant;
    private Formation formation;
    private Identite identite;
    private Matiere matiereMath;
    private Matiere matierePhysique;
    
    @BeforeEach
    void setUp() {
        formation = new Formation("INFO1");
        identite = new Identite("12345", "Dupont", "Jean");
        etudiant = new Etudiant(identite, formation);
        
        matiereMath = new Matiere("Mathématiques");
        matierePhysique = new Matiere("Physique");
        
        formation.ajouterMatiere(matiereMath, 2.0);
        formation.ajouterMatiere(matierePhysique, 1.5);
        
        etudiant.getResultat().put(matiereMath, new java.util.ArrayList<>());
        etudiant.getResultat().put(matierePhysique, new java.util.ArrayList<>());
    }
    
    @Test
    void testConstructeur() {
        assertNotNull(etudiant);
        assertEquals(identite, etudiant.getIdentite());
        assertEquals(formation, etudiant.getFormation());
        assertNotNull(etudiant.getResultat());
        assertTrue(etudiant.getResultat().isEmpty());
    }
    
    @Test
    void testAjouterNoteValide() throws NoteInvalideException, MatiereInexistanteException {
        etudiant.AjouterNote(matiereMath, 15.0);
        
        List<Double> notesMath = etudiant.getResultat().get(matiereMath);
        assertEquals(1, notesMath.size());
        assertEquals(15.0, notesMath.get(0), 0.001);
    }
    
    @Test
    void testAjouterNoteInvalideNegative() {
        assertThrows(NoteInvalideException.class, () -> {
            etudiant.AjouterNote(matiereMath, -5.0);
        });
    }
    
    @Test
    void testAjouterNoteInvalideSuperieure20() {
        assertThrows(NoteInvalideException.class, () -> {
            etudiant.AjouterNote(matiereMath, 25.0);
        });
    }
    
    @Test
    void testAjouterNoteMatiereInexistante() {
        Matiere matiereInconnue = new Matiere("Chimie");
        
        assertThrows(MatiereInexistanteException.class, () -> {
            etudiant.AjouterNote(matiereInconnue, 15.0);
        });
    }
    
    @Test
    void testMoyenneMatiere() throws MatiereInexistanteException {
        etudiant.getResultat().get(matiereMath).add(10.0);
        etudiant.getResultat().get(matiereMath).add(20.0);
        
        double moyenne = etudiant.moyenne(matiereMath);
        assertEquals(15.0, moyenne, 0.001);
    }
    
    @Test
    void testMoyenneMatiereSansNotes() throws MatiereInexistanteException {
        double moyenne = etudiant.moyenne(matiereMath);
        assertEquals(0.0, moyenne, 0.001); // Division par 0 dans la méthode actuelle
    }
    
    @Test
    void testMoyenneMatiereInexistante() {
        Matiere matiereInconnue = new Matiere("Chimie");
        
        assertThrows(MatiereInexistanteException.class, () -> {
            etudiant.moyenne(matiereInconnue);
        });
    }
    
    @Test
    void testMoyenneGenerale() throws MatiereInexistanteException {
        etudiant.getResultat().get(matiereMath).add(10.0);
        etudiant.getResultat().get(matiereMath).add(20.0); // Moyenne: 15.0 * coef 2 = 30
        
        etudiant.getResultat().get(matierePhysique).add(10.0); // Moyenne: 10.0 * coef 1.5 = 15
        
        double moyenneGen = etudiant.moyenneGenerale();
        assertEquals(12.857, moyenneGen, 0.001);
    }
    
    @Test
    void testMoyenneGeneraleSansNotes() throws MatiereInexistanteException {
        double moyenneGen = etudiant.moyenneGenerale();
        assertEquals(0.0, moyenneGen, 0.001);
    }
    
    @Test
    void testGetters() {
        assertEquals(identite, etudiant.getIdentite());
        assertEquals(formation, etudiant.getFormation());
        assertNotNull(etudiant.getResultat());
    }
}
