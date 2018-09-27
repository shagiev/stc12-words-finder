package finder;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Crawler {
    private final int THREAD_COUNT = 10;
    private ExecutorService crawlerThreadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    private boolean finished;

    public void getOccurencies(String[] sources, String[] words, String res) {
        ResultWriterThread resultWriter = new ResultWriterThread(res);

        int currentSource = 0;

        for (String source: sources) {
            crawlerThreadPool.submit();
        }

    }
}
