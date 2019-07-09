package com.kennethlange.nlp.sentiment;

import java.util.*;

public class Examples {

    public static void example1() {
        // Example 1: Quick start
        SentimentAnalyzer analyzer = new SentimentAnalyzer();
        Sentiment sentiment = analyzer.getSentiment("Never mind that, my lad. I wish to complain about this parrot what I purchased not half an hour ago from this very boutique.");

        System.out.println(sentiment); // Output: NEGATIVE
    }

    public static void example2() {
        // Example 2: Get sentiment of each sentence in Lincoln's Gettysburg Address
        String gettysburgAddress = "Four score and seven years ago our fathers brought forth, upon this continent, a new nation, conceived in liberty, and dedicated to the proposition that all men are created equal. Now we are engaged in a great civil war, testing whether that nation, or any nation so conceived, and so dedicated, can long endure. We are met on a great battle field of that war. We come to dedicate a portion of it, as a final resting place for those who died here, that the nation might live. This we may, in all propriety do. But, in a larger sense, we can not dedicate we can not consecrate we can not hallow, this ground The brave men, living and dead, who struggled here, have hallowed it, far above our poor power to add or detract. The world will little note, nor long remember what we say here; while it can never forget what they did here. It is rather for us, the living, we here be dedicated to the great task remaining before us that, from these honored dead we take increased devotion to that cause for which they here, gave the last full measure of devotion that we here highly resolve these dead shall not have died in vain; that the nation, shall have a new birth of freedom, and that government of the people, by the people, for the people, shall not perish from the earth.";

        SentimentAnalyzer analyzer = new SentimentAnalyzer();
        List<Sentence> sentences = analyzer.getSentiments(gettysburgAddress);
        for(Sentence s : sentences) {
            System.out.println(s.getSentiment() + " => " + s.getText());
        }
    }

    public static void example3() {
        // Example 3: Histogram (easy to convert to CSV for further processing and visualization in a spreadsheet)
        String gettysburgAddress = "Four score and seven years ago our fathers brought forth, upon this continent, a new nation, conceived in liberty, and dedicated to the proposition that all men are created equal. Now we are engaged in a great civil war, testing whether that nation, or any nation so conceived, and so dedicated, can long endure. We are met on a great battle field of that war. We come to dedicate a portion of it, as a final resting place for those who died here, that the nation might live. This we may, in all propriety do. But, in a larger sense, we can not dedicate we can not consecrate we can not hallow, this ground The brave men, living and dead, who struggled here, have hallowed it, far above our poor power to add or detract. The world will little note, nor long remember what we say here; while it can never forget what they did here. It is rather for us, the living, we here be dedicated to the great task remaining before us that, from these honored dead we take increased devotion to that cause for which they here, gave the last full measure of devotion that we here highly resolve these dead shall not have died in vain; that the nation, shall have a new birth of freedom, and that government of the people, by the people, for the people, shall not perish from the earth.";

        SentimentAnalyzer analyzer = new SentimentAnalyzer();
        Map<Sentiment, Long> histogram = analyzer.getSentimentHistogram(gettysburgAddress);

        System.out.println("Sentiment, Count");
        System.out.println("Very positive, " + histogram.get(Sentiment.VERY_POSITIVE));
        System.out.println("Positive, " + histogram.get(Sentiment.POSITIVE));
        System.out.println("Neutral, " + histogram.get(Sentiment.NEUTRAL));
        System.out.println("Negative, " + histogram.get(Sentiment.NEGATIVE));
        System.out.println("Very negative, " + histogram.get(Sentiment.VERY_NEGATIVE));
    }


    public static void main(String[] args) {
        example1();
        example2();
        example3();
    }
}
