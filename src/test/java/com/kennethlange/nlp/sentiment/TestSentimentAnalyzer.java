package com.kennethlange.nlp.sentiment;

import junit.framework.TestCase;

import java.util.List;
import java.util.Map;

public class TestSentimentAnalyzer extends TestCase {

    public void testConstructor_NoArg_ShouldPass() {
        SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
    }

    public void testGetSentiment_Positive_ShouldPass() {
        SentimentAnalyzer analyzer = new SentimentAnalyzer();

        Sentiment sentiment = analyzer.getSentiment("Cats are good!");
        assertEquals(Sentiment.POSITIVE, sentiment);
    }

    public void testGetSentiment_Negative_ShouldPass() {
        SentimentAnalyzer analyzer = new SentimentAnalyzer();

        Sentiment sentiment = analyzer.getSentiment("Snakes are bad!");
        assertEquals(Sentiment.NEGATIVE, sentiment);
    }

    public void testGetSentiment_TwoSentences_ShouldPass() {
        SentimentAnalyzer analyzer = new SentimentAnalyzer();

        Sentiment sentiment = analyzer.getSentiment("Cats are good! Snakes are bad!");
        assertEquals(Sentiment.NEUTRAL, sentiment);
    }

    public void testGetSentiments_Normal_ShouldPass() {
        String text = "Cats are good! Snakes are bad!";

        SentimentAnalyzer analyzer = new SentimentAnalyzer();
        List<Sentence> sentences = analyzer.getSentiments(text);

        assertEquals("Cats are good!", sentences.get(0).getText());
        assertEquals(Sentiment.POSITIVE, sentences.get(0).getSentiment());

        assertEquals("Snakes are bad!", sentences.get(1).getText());
        assertEquals(Sentiment.NEGATIVE, sentences.get(1).getSentiment());
    }

    public void testGetSentimentHistogram_Normal_ShouldPass() {
        String text = "Cats are good! Snakes are bad!";

        SentimentAnalyzer analyzer = new SentimentAnalyzer();

        Map<Sentiment, Long> histogram = analyzer.getSentimentHistogram(text);

        assertEquals(Long.valueOf(0L), histogram.get(Sentiment.VERY_POSITIVE));
        assertEquals(Long.valueOf(1L), histogram.get(Sentiment.POSITIVE));
        assertEquals(Long.valueOf(0L), histogram.get(Sentiment.NEUTRAL));
        assertEquals(Long.valueOf(1L), histogram.get(Sentiment.NEGATIVE));
        assertEquals(Long.valueOf(0L), histogram.get(Sentiment.VERY_NEGATIVE));
    }
}
