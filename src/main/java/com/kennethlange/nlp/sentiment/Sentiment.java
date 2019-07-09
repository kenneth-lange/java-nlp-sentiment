package com.kennethlange.nlp.sentiment;

/**
 * Express the sentiment, such as very positive, of a sentence. The enum is a descriptive wrapper around the int-based
 * sentiment used in Stanford CoreNLP.
 */
public enum Sentiment {
    VERY_NEGATIVE, NEGATIVE, NEUTRAL, POSITIVE, VERY_POSITIVE;

    /**
     * Maps Stanford CoreNLP's int-based sentiment to a more descriptive enum.
     * The mapping of these magic numbers are taken from:
     * https://stackoverflow.com/questions/32248522/how-to-interprete-rnncoreannotations-getpredictedclass-value
     * @param  sentiment an int between 0 and 4.
     * @return a more descriptive enum that correspondence to the int.
     * @throws IllegalArgumentException if sentiment is not between 0 and 4 (both inclusive)
     */
    public static Sentiment valueOf(int sentiment) {
        switch (sentiment) {
            case 0: return Sentiment.VERY_NEGATIVE;
            case 1: return Sentiment.NEGATIVE;
            case 2: return Sentiment.NEUTRAL;
            case 3: return Sentiment.POSITIVE;
            case 4: return Sentiment.VERY_POSITIVE;
            default: throw new IllegalArgumentException();
        }
    }
}
