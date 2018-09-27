package finder;

import java.util.ArrayList;

public class Crawler {
    private final int THREAD_COUNT = 10;
    private ArrayList<FileCrawlerThread> crawlerThreadPool;
    private boolean finished;

    public void getOccurencies(String[] sources, String[] words, String res) {
        ResultWriterThread resultWriter = new ResultWriterThread(res);

        int currentSource = 0;

        finished = false;

        while (!finished) {

        }

    }
}
