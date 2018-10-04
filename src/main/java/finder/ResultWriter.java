package finder;

import org.apache.log4j.Logger;

import java.io.*;

public class ResultWriter {

    private final Logger logger = Logger.getLogger(ResultWriter.class);
    private BufferedOutputStream stream;

    public ResultWriter(String filename) {
        logger.info("Open result writer");
        try {
            this.stream = new BufferedOutputStream(new FileOutputStream(filename));
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public synchronized void write(String sentence) {
        if (sentence == "") {
            return;
        }
        sentence = sentence.trim() + "\n";
        try {
            this.stream.write(sentence.getBytes());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void close() {
        if (this.stream == null) {
            return;
        }
        try {
            this.stream.flush();
            this.stream.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        this.stream = null;
        logger.info("ResultWriter is successfully closed");
    }
}
