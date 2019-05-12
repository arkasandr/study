package ru.job4j.io;

import com.sun.scenario.Settings;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BotChat {

    private static final String SENTENCE_REGULAR_EXPRESSION = "(.+?[!\\?\\.]+)+?";
    private static final Pattern SENTENCE_PATTERN = Pattern.compile(SENTENCE_REGULAR_EXPRESSION);
    static final String STOP = "стоп";
    static final String CONTINUE = "продолжить";
    static final String EXIT = "закончить";
    private boolean switchValue = true;
    private File botAnswers = new File(this.getPathFromProperties("Answers_Path"));
    private File log = new File(this.getPathFromProperties("Log_Path"));

    /**
     * Коснтруктор для теста.
     */
    public BotChat(File newLog, File newAnswers) throws IOException {
        this.botAnswers = newAnswers;
        this.log = newLog;
    }

    /**
     * Метод парсит текст и выбирает ответ бота
     * @param file
     * @return
     * @throws IOException
     */
    private static String parseText(File file) {
        ArrayList<String> resultTemp = new ArrayList<>();
        String line;
        String result = null;
        try {
            FileInputStream fs = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            while ((line = br.readLine()) != null) {
                Matcher matcher = SENTENCE_PATTERN.matcher(line);
                while (matcher.find()) {
                    resultTemp.add(matcher.group().trim());
                }
            }
            Random rand = new Random();
            int randIndex = rand.nextInt(resultTemp.size());
            result = resultTemp.get(randIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод определяет окончание или продолжение диалога с ботом
     * @param string
     * @return
     * @throws
     */
    private void switcher(String string) {
        if (this.STOP.equals(string)) {
            this.switchValue = false;
        } else if (this.CONTINUE.equals(string)) {
            this.switchValue = true;
        }
    }

    /**
     * Метод возвращает пути для файлов ответов и лога из botChat.properties
     * @param key
     * @return
     * @throws IOException
     */
    private String getPathFromProperties(String key) throws IOException {
        Properties props = new Properties();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("botChat/botChat.properties")) {
            props.load(in);
        }
        return props.getProperty(key);
    }

    /**
     * Метод реализует диалог пользователя и бота
     * @param in
     * @return
     * @throws IOException
     */
    public void run(InputStream in) throws IOException {
        try (FileWriter writer = new FileWriter(this.log, true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in))
            ) {
            String userText;
            while ((userText = reader.readLine()) != null) {
            while (!userText.equals(EXIT)) {
                this.switcher(userText);
                writer.write("-USER: " + userText + System.lineSeparator());
                if (switchValue) {
                    this.parseText(this.botAnswers);
                    writer.write("-BOT: " + this.parseText(this.botAnswers) + System.lineSeparator());
                }
                break;
            }
            }
            writer.write("-EXIT: " + EXIT + System.lineSeparator());
        }
    }
}
