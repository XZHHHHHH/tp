package cms.model.person;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GithubUsernameTest {
    @Test
    public void canonicalisation_trimsAndLowercases() {
        GithubUsername g = new GithubUsername("  John-Doe  ");
        assertEquals("john-doe", g.value);
    }

    @Test
    public void isValidGithubUsername_standardizedBehavior() {
        assertFalse(GithubUsername.isValidGithubUsername(null));
        assertTrue(GithubUsername.isValidGithubUsername("  John-Doe  "));
    }

    @Test
    public void validGithubUsernames() {
        // valid github usernames
        assertDoesNotThrow(() -> new GithubUsername("johndoe"));
        assertDoesNotThrow(() -> new GithubUsername("john-doe"));
        assertDoesNotThrow(() -> new GithubUsername("John-Doe"));
        assertDoesNotThrow(() -> new GithubUsername("a123"));
        // 39 chars
        assertDoesNotThrow(() -> new GithubUsername(
            "a12345678901234567890123456789012345678"));
    }

    @Test
    public void invalidGithubUsernames() {
        // invalid github usernames
        assertThrows(IllegalArgumentException.class, () -> new GithubUsername("-johndoe"));
        assertThrows(IllegalArgumentException.class, () -> new GithubUsername("johndoe-"));
        assertThrows(IllegalArgumentException.class, () -> new GithubUsername("john--doe"));
        assertThrows(IllegalArgumentException.class, () -> new GithubUsername("john doe"));
        assertThrows(IllegalArgumentException.class, () -> new GithubUsername("john@doe"));
        assertThrows(IllegalArgumentException.class, () -> new GithubUsername("john/doe"));
        assertThrows(IllegalArgumentException.class, () -> new GithubUsername(""));
        // 40 chars
        assertThrows(IllegalArgumentException.class, () -> new GithubUsername(
            "a123456789012345678901234567890123456789"));
    }

    @Test
    public void canonicalise_null_returnsNull() {
        assertEquals(null, GithubUsername.canonicalise(null));
    }
}
