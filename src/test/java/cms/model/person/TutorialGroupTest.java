package cms.model.person;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TutorialGroupTest {
    @Test
    public void canonicalisation_trimsSpaces() {
        TutorialGroup t = new TutorialGroup("  12  ");
        assertEquals(12, t.value);
    }

    @Test
    public void isValidTutorialGroup_canonicalInput() {
        // null input
        assertThrows(NullPointerException.class, () -> TutorialGroup.isValidTutorialGroup(null));

        // invalid canonical forms
        assertFalse(TutorialGroup.isValidTutorialGroup("0")); // lower bound
        assertFalse(TutorialGroup.isValidTutorialGroup("100")); // upper bound
        assertFalse(TutorialGroup.isValidTutorialGroup("01")); // leading zero
        assertFalse(TutorialGroup.isValidTutorialGroup("AA")); // non-numeric

        // valid canonical forms
        assertTrue(TutorialGroup.isValidTutorialGroup("1"));
        assertTrue(TutorialGroup.isValidTutorialGroup("99"));
    }

    @Test
    public void constructor_acceptsValidInputs() {
        // valid tutorial groups
        assertDoesNotThrow(() -> new TutorialGroup("1"));
        assertDoesNotThrow(() -> new TutorialGroup("99"));
        assertDoesNotThrow(() -> new TutorialGroup(" 7 ")); // trims spaces
    }

    @Test
    public void constructor_rejectsInvalidInputs() {
        // invalid tutorial groups
        assertThrows(IllegalArgumentException.class, () -> new TutorialGroup("0"));
        assertThrows(IllegalArgumentException.class, () -> new TutorialGroup("100"));
        assertThrows(IllegalArgumentException.class, () -> new TutorialGroup("AA"));
        assertThrows(IllegalArgumentException.class, () -> new TutorialGroup("1 2"));
        assertThrows(IllegalArgumentException.class, () -> new TutorialGroup("01"));
    }
}
