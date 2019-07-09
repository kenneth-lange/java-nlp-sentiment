package com.kennethlange.nlp.sentiment;

import java.util.Objects;

/**
 * Represents a sentence, both its actual text ("Cats are good!") and its sentiment (POSITIVE). The class is immutable.
 */
public final class Sentence {
    private String text;
    private Sentiment sentiment;

    /**
     * Constructs a new, immutable sentence.
     * @param text The text of the sentence.
     * @param sentiment The sentiment of the sentence.
     * @throws NullPointerException If text or sentiment are null.
     */
    public Sentence(String text, Sentiment sentiment) {
        this.text = Objects.requireNonNull(text);
        this.sentiment = Objects.requireNonNull(sentiment);
    }

    /**
     * Gets the text of the sentence.
     * @return text of the sentence.
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the sentiment of the sentence.
     * @return sentiment of the sentence.
     */
    public Sentiment getSentiment() {
        return sentiment;
    }
}