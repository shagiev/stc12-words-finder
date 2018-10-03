package finder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Crawler {
    private final int THREAD_COUNT = 4;
    private ExecutorService crawlerThreadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    private ArrayList<FileCrawlerThread> fileCrawlerList = new ArrayList<FileCrawlerThread>();
    private HashSet<String> wordsSet;

    public void getOccurencies(String[] sources, String[] words, String res) throws InterruptedException {
        wordsSet = new HashSet<String>(words.length);
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
    }
}
