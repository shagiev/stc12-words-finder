import finder.Crawler;
import org.apache.log4j.Logger;

public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        logger.info("Start crawler");

        Crawler crawler = new Crawler();
        String res = "result.txt";
        String[] sources = {
                "http://tululu.org/txt.php?id=11850",
                "http://tululu.org/txt.php?id=11858",
                "http://tululu.org/txt.php?id=57798",
                "http://tululu.org/txt.php?id=64638",
                "http://tululu.org/txt.php?id=62664",
                "http://tululu.org/txt.php?id=66968",
                "http://tululu.org/txt.php?id=71653",
                "http://tululu.org/txt.php?id=72523",
                "http://tululu.org/txt.php?id=75242",
                "http://tululu.org/txt.php?id=77440",
                "http://tululu.org/txt.php?id=77441",
                "http://tululu.org/txt.php?id=77442",
                "http://tululu.org/txt.php?id=77443",
                "http://tululu.org/txt.php?id=58517",
                "/Users/lenarsagiev/IdeaProjects/stc12-words-finder/data/azazel.htm"
        };
        String[] words = {"понедельник", "вторник", "Java", "мед", "кабы"};
        try {
            crawler.getOccurencies(sources, words, res);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        long time = System.currentTimeMillis() - startTime;
        logger.info("Crawler work time: " + time + " millisec.");
    }

}
