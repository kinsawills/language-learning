/**
 * @author Kevin Buie
 */
package com.language.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.model.Gender;
import com.model.PartOfSpeech;
import com.model.Phrase;
import com.model.Word;
import com.model.WritingQuestion;

public class WritingQuestionTest {
    private WritingQuestion writingQuestion;
    private Phrase phrase;

    @Before
    public void setup() {
        ArrayList<Word> englishPhrase = new ArrayList<>();
        ArrayList<Word> translatedPhrase = new ArrayList<>();

        englishPhrase.add(new Word("hello", "hola", "oh-lah", PartOfSpeech.NOUN, Gender.NEITHER, 1));
        englishPhrase.add(new Word("friend", "amigo", "ah-mee-go", PartOfSpeech.NOUN, Gender.MASCULINE, 1));

        translatedPhrase.add(new Word("hello", "hola", "oh-lah", PartOfSpeech.NOUN, Gender.NEITHER, 1));
        translatedPhrase.add(new Word("friend", "amigo", "ah-mee-go", PartOfSpeech.NOUN, Gender.MASCULINE, 1));

        phrase = new Phrase("greeting", englishPhrase, translatedPhrase, 1);
        writingQuestion = new WritingQuestion(phrase);
    }

    @Test
    public void testGenerateQuestion() {
        String question = writingQuestion.getQuestion();
        System.out.println("Generated Question: " + question);

        assertTrue(
                (question.contains("hello") || question.contains("__________")) &&
                        (question.contains("amigo") || question.contains("__________")));
    }

    @Test
    public void testIsCorrect() {
        String correctAnswer = writingQuestion.getAnswer();
        assertTrue(writingQuestion.isCorrect(correctAnswer));
        assertFalse(writingQuestion.isCorrect("incorrect answer"));
    }

    @Test
    public void testGetPhrase() {
        assertEquals(phrase, writingQuestion.getPhrase());
    }

    @Test
    public void testToString() {
        String output = writingQuestion.toString();
        assertTrue(output.contains("Question:"));
        assertTrue(output.contains("Answer:"));
    }
}
