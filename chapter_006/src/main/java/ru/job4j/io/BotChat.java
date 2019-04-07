package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BotChat {

    private static final String SENTENCE_REGULAR_EXPRESSION = "(.+?[!\\?\\.]+)+?";
    private static final Pattern SENTENCE_PATTERN = Pattern.compile(SENTENCE_REGULAR_EXPRESSION);
    private Scanner scanner = new Scanner(System.in);
    private final String STOP = "стоп";
    private final String CONTINUE = "продолжить";
    private final String EXIT = "закончить";
    private boolean switchValue = true;



    private static Collection<String> parseText(File file) throws IOException {
        ArrayList<String> result = new ArrayList<>();
        String line;
        try {
            FileInputStream fs = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            while ((line = br.readLine()) != null) {
                Matcher matcher = SENTENCE_PATTERN.matcher(line);
                while (matcher.find()) {
                    result.add(matcher.group().trim());
                }
            }
            Random rand = new Random();
            int randIndex = rand.nextInt(result.size());
            result.get(randIndex);
            System.out.println(result.get(randIndex));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    private void switcher(String string) {
        if (this.STOP.equals(string)) {
            this.switchValue = false;
        } else if (this.CONTINUE.equals(string)) {
            this.switchValue = true;
        }
    }


    public void run(File file) throws IOException {
        String userText = scanner.nextLine();
            while (!userText.equals(EXIT)) {
                this.switcher(userText);
                if (switchValue) {
                    this.parseText(file);
                }
                userText = scanner.nextLine();
            }
        System.out.println("Bye!");
        }


    public static void main(String[] args) {
        BotChat b = new BotChat();
        try {
            b.run(new File("/home/arkaleks/Downloads/1.txt"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
