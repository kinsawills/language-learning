package com.language.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.model.Gender;
import com.model.Language;
import com.model.Lesson;
import com.model.PartOfSpeech;
import com.model.Phrase;
import com.model.Question;
import com.model.Story;
import com.model.UserProgress;
import com.model.Word;

import java.util.ArrayList;
import java.util.UUID;

public class LessonTest {
    private UserProgress userProgress;
    private Language language;
    private Lesson lesson;

    @Before
    public void setUp() {
        UUID languageId = UUID.randomUUID();
        ArrayList<Word> words = new ArrayList<>();
        ArrayList<Phrase> phrases = new ArrayList<>();
        ArrayList<Story> stories = new ArrayList<>();
        language = new Language(languageId, "Spanish", words, phrases, stories);

        userProgress = new UserProgress(language);

        lesson = new Lesson(userProgress, language);
    }

    @Test
    public void testGenerateQuestions() {
        ArrayList<Question> questions = lesson.getQuestions();
        assertEquals(0, questions.size());
    }

    @Test
    public void testIncreaseScore() {
        lesson.increaseScore();
        assertEquals("1 / 0", lesson.getScore());
    }

    @Test
    public void testDecreaseScore() {
        lesson.increaseScore();
        lesson.decreaseScore();
        assertEquals("0 / 0", lesson.getScore());
    }

    @Test
    public void testGetScore() {
        assertEquals("0 / 0", lesson.getScore());
        lesson.increaseScore();
        assertEquals("1 / 0", lesson.getScore());
    }

}