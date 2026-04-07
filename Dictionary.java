import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dictionary {
    private static final String DEFAULT_WORD_FILE = "./data/scrabble_words.txt";

    private final Set<String> words;
    private final Map<Integer, ArrayList<String>> wordsByLength;

    public Dictionary() throws IOException {
        this(DEFAULT_WORD_FILE);
    }

    public Dictionary(String wordFilePath) throws IOException {
        words = new HashSet<>();
        wordsByLength = new HashMap<>();
        loadFromFile(wordFilePath);
    }

    private void loadFromFile(String path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.trim();
                if (!word.isEmpty()) {
                    addWordInternal(word);
                }
            }
        }
    }

    private void addWordInternal(String word) {
        if (words.add(word)) {
            int n = word.length();
            wordsByLength.computeIfAbsent(n, k -> new ArrayList<>()).add(word);
        }
    }

    public void addWord(String word) {
        if (word == null) {
            throw new NullPointerException("word");
        }
        String w = word.trim();
        if (w.isEmpty()) {
            return;
        }
        addWordInternal(w);
    }

    public void removeWord(String word) {
        if (word == null) {
            return;
        }
        String w = word.trim();
        if (words.remove(w)) {
            ArrayList<String> bucket = wordsByLength.get(w.length());
            if (bucket != null) {
                bucket.remove(w);
            }
        }
    }

    public boolean contains(String word) {
        if (word == null) {
            return false;
        }
        return words.contains(word.trim());
    }

    public ArrayList<String> getNLetterWords(int n) {
        if (n < 0) {
            return new ArrayList<>();
        }
        ArrayList<String> bucket = wordsByLength.get(n);
        if (bucket == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(bucket);
    }
}
