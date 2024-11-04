import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


class Translator {
    private Map<String, String> dictionary;

    public Translator() {
        this.dictionary = new HashMap<>();
    }

    // Метод для додавання пар слів
    public void addTranslation(String englishWord, String ukrainianWord) {
        dictionary.put(englishWord.toLowerCase(), ukrainianWord);
    }

    // Метод для перекладу фрази
    public String translate(String phrase) {
        StringBuilder translatedPhrase = new StringBuilder();
        String[] words = phrase.split(" ");

        for (String word : words) {
            String translatedWord = dictionary.get(word.toLowerCase());
            // Якщо слова немає в словнику, залишити без змін
            translatedPhrase.append(Objects.requireNonNullElse(translatedWord, word)).append(" ");
        }

        return translatedPhrase.toString().trim();
    }
}
