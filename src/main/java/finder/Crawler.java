package finder;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Crawler {
    private final Logger logger = Logger.getLogger(Crawler.class);
    private final int THREAD_COUNT = 4;
    private ExecutorService crawlerThreadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    private ArrayList<FileCrawlerThread> fileCrawlerList = new ArrayList<FileCrawlerThread>();
    private HashSet<String> wordsSet;

    public void getOccurencies(String[] sources, String[] words, String res) throws InterruptedException {
        logger.info("Start crawler with sources list: " + String.join(", ", sources) +
                " and words: " + String.join(", ", words));
        wordsSet = new HashSet<>(words.length);
        for (String word: words) {
            wordsSet.add(word.toLowerCase());
        }
        ResultWriter resultWriter = new ResultWriter(res);

        for (String source: sources) {
            FileCrawlerThread thread = new FileCrawlerThread(source, wordsSet, resultWriter);
            crawlerThreadPool.execute(thread);
            fileCrawlerList.add(thread);
        }

        crawlerThreadPool.shutdown();
        crawlerThreadPool.awaitTermination(20, TimeUnit.MINUTES);
        resultWriter.close();
        logger.info("Crawler finished");
    }
}
