package com.language.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.model.DataLoader;
import com.model.Facade;
import com.model.Language;
import com.model.LanguageList;
import com.model.Phrase;
import com.model.UserProgress;
import com.model.Word;

import software.amazon.awssdk.services.polly.endpoints.internal.Value.Str;

public class UserProgressTest {
    private ArrayList<Language> languages = DataLoader.getLanguages();
    private UserProgress userProgress = new UserProgress(languages.get(0));
    private HashMap<Phrase, Integer> phraseProgress = userProgress.getPhraseProgress();

    @Test
    public void testValidDisplayHardPhrases() {
        // UserProgress userProgress = new
        // UserProgress(LanguageList.getInstance().getLanguages().get(0));
        // String phrases = userProgress.displayHardPhrases();
        // assertTrue(phrases.length() > 0);
    }

    @Test
    public void testNullDisplayHardPhrases() {
        userProgress.getPhraseProgress(null); // Assuming a setter for testing purposes
        String phrases = userProgress.displayHardPhrases();
        assertTrue(phrases.length() == 0);
    }

    @Test
    public void testEmptyDisplayHardPhrases() {
        UserProgress userProgress = new UserProgress(LanguageList.getInstance().getLanguages().get(0));
        phraseProgress = new HashMap<Phrase, Integer>();
        assertNotNull(userProgress.displayHardPhrases());
    }
}
