# Sentiment Analysis in Java

The purpose of this project is to make it easy to get started with **sentiment analysis** in Java:

The `SentimentAnalyzer` class takes a natural language sentence, such as "Cats are wonderful!", as **input** and then it gives the sentencen's sentiment as **output**. The sentiment will be one of the five categories listed below:

* Very positive
* Positive
* Neutral
* Negative
* Very negative

The code is a wrapper around Stanford CoreNLP library (https://stanfordnlp.github.io/CoreNLP/) and seeks to make this powerful NLP library easier to use for the casual user.

Note that Stanford CoreNLP Library is large (>500 MB), so expect that it will take a little time when the Maven dependencies are downloaded.

## Code Examples

### Example 1: The minimal example
The code below gets the sentiment of a customer complaint:

```java
SentimentAnalyzer analyzer = new SentimentAnalyzer();
Sentiment sentiment = analyzer.getSentiment("Never mind that, my lad. I wish to complain about this parrot what I purchased not half an hour ago from this very boutique.");

System.out.println(sentiment); // Output: NEGATIVE
```

### Example 2: Get the sentiment of each sentence in Lincoln's Gettysburg Address

```java
String gettysburgAddress = "Four score and seven years ago our fathers brought forth, upon this continent, a new nation, conceived in liberty, and dedicated to the proposition that all men are created equal. Now we are engaged in a great civil war, testing whether that nation, or any nation so conceived, and so dedicated, can long endure. We are met on a great battle field of that war. We come to dedicate a portion of it, as a final resting place for those who died here, that the nation might live. This we may, in all propriety do. But, in a larger sense, we can not dedicate we can not consecrate we can not hallow, this ground The brave men, living and dead, who struggled here, have hallowed it, far above our poor power to add or detract. The world will little note, nor long remember what we say here; while it can never forget what they did here. It is rather for us, the living, we here be dedicated to the great task remaining before us that, from these honored dead we take increased devotion to that cause for which they here, gave the last full measure of devotion that we here highly resolve these dead shall not have died in vain; that the nation, shall have a new birth of freedom, and that government of the people, by the people, for the people, shall not perish from the earth.";

SentimentAnalyzer analyzer = new SentimentAnalyzer();
List<Sentence> sentences = analyzer.getSentiments(gettysburgAddress);
for(Sentence s : sentences) {
    System.out.println(s.getSentiment() + " => " + s.getText());
}
```

### Example 3: Create a Histogram of Lincoln's Gettysburg Address
This code example makes a histogram of Lincoln's Gettysburg address and prints it in the CSV format, so it can be processed and visualized further in a spreadsheet:  

```java
String gettysburgAddress = "Four score and seven years ago our fathers brought forth, upon this continent, a new nation, conceived in liberty, and dedicated to the proposition that all men are created equal. Now we are engaged in a great civil war, testing whether that nation, or any nation so conceived, and so dedicated, can long endure. We are met on a great battle field of that war. We come to dedicate a portion of it, as a final resting place for those who died here, that the nation might live. This we may, in all propriety do. But, in a larger sense, we can not dedicate we can not consecrate we can not hallow, this ground The brave men, living and dead, who struggled here, have hallowed it, far above our poor power to add or detract. The world will little note, nor long remember what we say here; while it can never forget what they did here. It is rather for us, the living, we here be dedicated to the great task remaining before us that, from these honored dead we take increased devotion to that cause for which they here, gave the last full measure of devotion that we here highly resolve these dead shall not have died in vain; that the nation, shall have a new birth of freedom, and that government of the people, by the people, for the people, shall not perish from the earth.";

SentimentAnalyzer analyzer = new SentimentAnalyzer();
Map<Sentiment, Long> histogram = analyzer.getSentimentHistogram(gettysburgAddress);

System.out.println("Sentiment, Count");
System.out.println("Very positive, " + histogram.get(Sentiment.VERY_POSITIVE));
System.out.println("Positive, " + histogram.get(Sentiment.POSITIVE));
System.out.println("Neutral, " + histogram.get(Sentiment.NEUTRAL));
System.out.println("Negative, " + histogram.get(Sentiment.NEGATIVE));
System.out.println("Very negative, " + histogram.get(Sentiment.VERY_NEGATIVE));
```

## License

Please see Stanford CoreNLP's license page (https://stanfordnlp.github.io/CoreNLP/index.html#license) for details. 

## Acknowledgments

* A big thanks to Stanford NLP Group (https://nlp.stanford.edu/) for making Stanford CoreNLP library freely available. 
* A cool example of using Stanford CoreNLP to make sentiment analysis of tweets are also available: https://blog.openshift.com/day-20-stanford-corenlp-performing-sentiment-analysis-of-twitter-using-java/
