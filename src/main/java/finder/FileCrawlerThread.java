package finder;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Pattern;

public class FileCrawlerThread implements Runnable {

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
        try (InputStream stream = getStream(filename)){
            Reader reader = new InputStreamReader(stream);
            ArrayList<Character> charList = new ArrayList<Character>();
            StringBuilder sb = new StringBuilder();

            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char)c);
                charList.add((Character)(char)c);

                if (c == '.' || c == '!' || c == '?') {
                    String sentence = sb.toString();
                    handleSentence(sentence);
                    sb = new StringBuilder();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                e.printStackTrace();
            }
        } else {
            try {
                stream = new BufferedInputStream(new FileInputStream(filename));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
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
