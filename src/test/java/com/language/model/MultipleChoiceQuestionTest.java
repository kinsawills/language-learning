package com.language.model;

import com.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.model.Gender;
import com.model.Language;
import com.model.PartOfSpeech;
import com.model.Phrase;
import com.model.Story;
import com.model.Word;
import com.model.MultipleChoiceQuestion;

public class MultipleChoiceQuestionTest {

    private MultipleChoiceQuestion multipleChoiceQuestion;
    private Phrase phrase;
    private Language language;

    @Before
    public void setUp() {
        Word helloWord = new Word("Hello", "Hola", "", PartOfSpeech.NOUN, Gender.NEITHER, 1);
        Word worldWord = new Word("World", "Mundo", "", PartOfSpeech.NOUN, Gender.NEITHER, 1);
        Word greetingsWord = new Word("Greetings", "Saludos", "", PartOfSpeech.NOUN, Gender.NEITHER, 1);
        Word farewellWord = new Word("Farewell", "Despedida", "", PartOfSpeech.NOUN, Gender.NEITHER, 1);

        ArrayList<Word> englishWords = new ArrayList<>();
        englishWords.add(helloWord);

        ArrayList<Word> translatedWords = new ArrayList<>();
        translatedWords.add(new Word("Hola", "Hello", "", PartOfSpeech.NOUN, Gender.NEITHER, 1));

        phrase = new Phrase("Translate Hello", englishWords, translatedWords, 1);

        ArrayList<Phrase> phrases = new ArrayList<>();
        phrases.add(phrase);
        phrases.add(new Phrase("Translate World", new ArrayList<Word>() {
            {
                add(worldWord);
            }
        }, new ArrayList<Word>() {
            {
                add(new Word("Mundo", "World", "", PartOfSpeech.NOUN, Gender.NEITHER, 1));
            }
        }, 1));

        phrases.add(new Phrase("Translate Greetings", new ArrayList<Word>() {
            {
                add(greetingsWord);
            }
        }, new ArrayList<Word>() {
            {
                add(new Word("Saludos", "Greetings", "", PartOfSpeech.NOUN, Gender.NEITHER, 1));
            }
        }, 1));

        phrases.add(new Phrase("Translate Farewell", new ArrayList<Word>() {
            {
                add(farewellWord);
            }
        }, new ArrayList<Word>() {
            {
                add(new Word("Despedida", "Farewell", "", PartOfSpeech.NOUN, Gender.NEITHER, 1));
            }
        }, 1));

        language = new Language(UUID.randomUUID(), "Spanish", new ArrayList<>(), phrases, new ArrayList<>());

        multipleChoiceQuestion = new MultipleChoiceQuestion(phrase, language);
    }

    @Test
    public void testGetQuestion() {
        String question = multipleChoiceQuestion.getQuestion();
        assertNotNull(question);
        assertTrue(question.contains("Select the correct translation by typing the matching number for :"));
        assertTrue(question.contains("\"Hello\""));
    }

    @Test
    public void testGetAnswer() {
        String answer = multipleChoiceQuestion.getAnswer();
        assertNotNull(answer);
        assertTrue(answer.matches("\\d"));
    }

    @Test
    public void testIsCorrect() {
        String correctAnswer = multipleChoiceQuestion.getAnswer();
        String incorrectAnswer = correctAnswer.equals("1") ? "2" : "1";

        assertTrue(multipleChoiceQuestion.isCorrect(correctAnswer));
        assertFalse(multipleChoiceQuestion.isCorrect(incorrectAnswer));
    }

    @Test
    public void testGetPhrase() {
        assertEquals(phrase, multipleChoiceQuestion.getPhrase());
    }
}