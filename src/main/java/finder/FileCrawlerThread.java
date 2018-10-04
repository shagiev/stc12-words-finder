package finder;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Pattern;

public class FileCrawlerThread implements Runnable {

    private final Logger logger = Logger.getLogger(FileCrawlerThread.class);

    private String filename;
    private HashSet<String> wordsSet;
    private ResultWriter resultWriter;
    private BufferedInputStream stream;

    public FileCrawlerThread(String filename, HashSet<String> wordsSet, ResultWriter resultWriter) {
        this.filename = filename;
        this.wordsSet = wordsSet;
        this.resultWriter = resultWriter;
    }

    public void run() {
        logger.info("File crawler thread " + Thread.currentThread().getName()  + " started with file " + filename);
        try (InputStream stream = getStream(filename)){
            Reader reader = new InputStreamReader(stream);
            StringBuilder sb = new StringBuilder();

            int currentChar;
            while ((currentChar = reader.read()) != -1) {
                sb.append((char)currentChar);

                if (currentChar == '.' || currentChar == '!' || currentChar == '?') {
                    String sentence = sb.toString();
                    handleSentence(sentence);
                    sb.setLength(0);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private InputStream getStream(String filename) {
        InputStream stream = null;
        boolean isUrl = Pattern.matches("^(http(s)?|ftp)://.*", filename);
        if (isUrl) {
            try {
                URL url = new URL(filename);
                stream = url.openStream();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        } else {
            try {
                stream = new BufferedInputStream(new FileInputStream(filename));
            } catch (FileNotFoundException e) {
                logger.error(e.getMessage());
            }
        }
        return stream;
    }

    private void handleSentence(String sentence) {
        String[] words = sentence.split("[ ,;:\"]+");
        if (Arrays.stream(words).anyMatch(s -> wordsSet.contains(s.toLowerCase()))) {
            resultWriter.write(sentence);
        }
    }

}
