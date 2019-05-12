package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;
import java.io.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BotChatTest {

    String path = System.getProperty("java.io.tmpdir");
    BotChat chat;
    FileWriter testAnswerWriter;
    BufferedReader logReader;
    String ansPath;
    String logPath;


        /**
         * Метод для очистки testLog.
         * @param logFile
         * @throws IOException
         */

        private void clearLog(File logFile) throws IOException {
            if (logFile.exists()) {
                logFile.delete();
                logFile.createNewFile();
            }
        }

        @Before
        public void setUp() throws IOException {

            ansPath = path + "\\testAnswers";
            logPath = path + "\\testLog";

            File testAnsw = new File(ansPath);
            testAnsw.createNewFile();
            File testLog = new File(logPath);
            clearLog(testLog);

            testAnswerWriter = new FileWriter(testAnsw);
            testAnswerWriter.write("Test." + System.lineSeparator()
                    + "Test." + System.lineSeparator()
                    + "Test." + System.lineSeparator()
                    + "Test." + System.lineSeparator());
            testAnswerWriter.close();
            logReader = new BufferedReader(new FileReader(testLog));
            chat = new BotChat(testLog, testAnsw);
        }

    @Test
    public void whenAllBotCommandsIsPresentThenTrue() throws IOException {

        String inputString = "старт" + System.lineSeparator()
                    + "раз" + System.lineSeparator()
                    + "стоп" + System.lineSeparator()
                    + "два" + System.lineSeparator()
                    + "продолжить" + System.lineSeparator()
                    + "три" + System.lineSeparator()
                    + "закончить";
            ByteArrayInputStream bIn = new ByteArrayInputStream(inputString.getBytes());
            chat.run(bIn);
            String logResult = "";
            String buffer;
            while ((buffer = logReader.readLine()) != null) {
                logResult += buffer + System.lineSeparator();
            }
            String expected = "-USER: старт" + System.lineSeparator()
                    + "-BOT: Test." + System.lineSeparator()
                    + "-USER: раз" + System.lineSeparator()
                    + "-BOT: Test." + System.lineSeparator()
                    + "-USER: стоп" + System.lineSeparator()
                    + "-USER: два" + System.lineSeparator()
                    + "-USER: продолжить" + System.lineSeparator()
                    + "-BOT: Test." + System.lineSeparator()
                    + "-USER: три" + System.lineSeparator()
                    + "-BOT: Test." + System.lineSeparator()
                    + "-EXIT: закончить" + System.lineSeparator();
            assertThat(logResult, is(expected));
        }
    }
