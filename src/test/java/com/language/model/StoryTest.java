package com.language.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.model.Story;

public class StoryTest {

    @Test
    public void testDisplayEmptyStory() {
        Story story = new Story("title", "", "");
        String display = story.displayStory();
        assertEquals("", display);
    }

    @Test
    public void testDisplayValidStory() {
        Story story = new Story("title", "english", "spanish");
        String display = story.displayStory();
        assertEquals("spanish", display);
    }

    @Test
    public void testDisplayNullStory() {
        Story story = new Story("title", null, null);
        String display = story.displayStory();
        assertNull(display);
    }

}
