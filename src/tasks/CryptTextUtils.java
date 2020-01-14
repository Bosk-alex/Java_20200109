package tasks;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

class CryptTextUtils {
    private CryptTextUtils() {
        throw new IllegalStateException("Utility class");
    }
    static final String RUS_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    static final int RUS_ALPHABET_MAX_INDEX = 32;
    static StringBuilder cryptText() {
        StringBuilder outputText = new StringBuilder();
        String inputText = "";
        try {
            inputText = Files.readString(Paths.get("D:\\JavaApps\\FL_Tasks\\Java 20200109\\resources\\input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < inputText.length(); i++) {
            char currentLetter = inputText.charAt(i);
            if (Character.isLetter(currentLetter)) {
                currentLetter = changeChar(currentLetter);
            }
            outputText.append(currentLetter);
        }

        try {
            Files.writeString(Paths.get("D:\\JavaApps\\FL_Tasks\\Java 20200109\\out\\output\\output.txt"), outputText, StandardCharsets.UTF_16);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputText;
    }

    private static char changeChar(char letter) {
        if (Character.isUpperCase(letter)) {
            char changedLetter = codeLetter(Character.toLowerCase(letter));
            return Character.toUpperCase(changedLetter);
        } else return codeLetter(letter);
    }

    private static char codeLetter(char letter) {
        int letterIndex = 0;
        for (int i = 0; i < RUS_ALPHABET.length(); i++) {
            if (RUS_ALPHABET.charAt(i) == letter) {
                letterIndex = i;
            }
        }
        return RUS_ALPHABET.charAt(RUS_ALPHABET_MAX_INDEX - letterIndex);
    }
}
