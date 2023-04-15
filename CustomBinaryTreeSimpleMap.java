import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class CustomBinaryTreeSimpleMap {
    private SimpleMap<String, String> index;

    public CustomBinaryTreeSimpleMap() {
        index = new PlaceholderBinaryTreeSimpleMap<>();
    }

    public void indexFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int lineNumber = 0;
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            StringTokenizer tokenizer = new StringTokenizer(line, " ,.!?");
            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken().toLowerCase();
                word = word.replaceAll("[^a-zA-Z0-9]", ""); // remove punctuation
                index.put(word, lineNumber + ": " + line);
            }
        }
        reader.close();
    }

    public void search(String searchTerm) {
        String term = searchTerm.toLowerCase();
        for (String key : index.keySet()) {
            if (key.equalsIgnoreCase(term)) {
                System.out.println(index.get(key));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        TextSearcher searcher = new TextSearcher();
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter path to text file: ");
        String filePath = inputReader.readLine();
        searcher.indexFile(filePath);
        while (true) {
            System.out.print("Enter search term (or q to quit): ");
            String searchTerm = inputReader.readLine();
            if (searchTerm.equals("q")) {
                break;
            }
            searcher.search(searchTerm);
        }
        inputReader.close();
    }
}