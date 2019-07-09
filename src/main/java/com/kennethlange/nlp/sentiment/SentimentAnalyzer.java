package com.kennethlange.nlp.sentiment;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import org.apache.log4j.PropertyConfigurator;

import java.util.*;

/**
 * Sentiment analysis in Java. A wrapper around Stanford CoreNLP library (https://stanfordnlp.github.io/CoreNLP/)
 * that should make it easier to try it out for new users.
 */
public final class SentimentAnalyzer {
    private StanfordCoreNLP pipeline;

    /**
     * Constructs a new instance. The user decides if log4j should be automatically configured by the class, or if
     * the user prefers to configure log4j elsewhere.
     * @param autoConfigLog should log4j be automatically configured?
     */
    public SentimentAnalyzer(boolean autoConfigLog) {
        if(autoConfigLog) {
            Properties prop = new Properties();
            prop.setProperty("log4j.rootLogger", "WARN");
            PropertyConfigurator.configure(prop);
        }

        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit, parse, sentiment");

        this.pipeline = new StanfordCoreNLP(properties);
    }

    /**
     * Constructs a new instance and automatically configures log4j.
     */
    public SentimentAnalyzer() {
        this(true);
    }

    /**
     * Returns the average sentiment of all the sentences in the document.
     * @param  text a string that be analyzed for its average sentiment.
     * @return the average sentiment of the document.
     */
    public Sentiment getSentiment(String text) {
        Objects.requireNonNull(text);

        Annotation annotation = pipeline.process(text);

        int totalScore = 0;
        int numberOfSentences = 0;
        List<Sentence> sentences = new ArrayList<>();
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            int sentiment = RNNCoreAnnotations.getPredictedClass(tree);

            totalScore += sentiment;
            numberOfSentences++;
        }

        // The part after "+" is for rounding up the integer.
        int averageScore = (totalScore / numberOfSentences) + (totalScore % numberOfSentences == 0 ? 0 : 1);
        return Sentiment.valueOf(averageScore);
    }

    /**
     * Splits a text document into list of sentences with sentiment.
     * @param  text a string that should be split into sentiment sentences.
     * @return a list of sentences (each sentence includes its sentiment.)
     */
    public List<Sentence> getSentiments(String text) {
        if (text == null) {
            return Collections.emptyList();
        }

        Annotation annotation = pipeline.process(text);

        List<Sentence> sentences = new ArrayList<>();
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            int sentiment = RNNCoreAnnotations.getPredictedClass(tree);

            sentences.add(new Sentence(sentence.toString(), Sentiment.valueOf(sentiment)));
        }

        return sentences;
    }

    /**
     * Creates a histogram of the sentiments of the sentences in a document. The histogram will always return all five
     * sentiment types - even if no sentence has that sentiment.
     * @param  text the string that the histogram should be based on.
     * @return a map with the five sentiments as keys and the number of sentences with that sentiment as value.
     */
    public Map<Sentiment, Long> getSentimentHistogram(String text) {
        Map<Sentiment, Long> histogram = new HashMap<>();
        histogram.put(Sentiment.VERY_POSITIVE, 0L);
        histogram.put(Sentiment.POSITIVE, 0L);
        histogram.put(Sentiment.NEUTRAL, 0L);
        histogram.put(Sentiment.NEGATIVE, 0L);
        histogram.put(Sentiment.VERY_NEGATIVE, 0L);

        List<Sentence> sentences = getSentiments(text);
        for(Sentence s : sentences) {
            Long newCount = histogram.get(s.getSentiment()) + 1L;
            histogram.put(s.getSentiment(), newCount);
        }

        return Collections.unmodifiableMap(histogram);
    }
}
