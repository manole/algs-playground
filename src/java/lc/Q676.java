package lc;

/**
 * Implement a magic directory with buildDict, and search methods.
 *
 * For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.
 *
 * For the method search, you'll be given a word, and judge whether
 * if you modify exactly one character into another character in this word,
 * the modified word is in the dictionary you just built.
 */
public class Q676 {

    static class MagicDictionary {

        class TrieNode {
            TrieNode[] children = new TrieNode[26];
            boolean isEndOfWord;
        }

        private TrieNode root;

        private void insert(String word) {
            TrieNode next = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (next.children[index] == null) {
                    next.children[index] = new TrieNode();
                }
                next = next.children[index];
            }
            next.isEndOfWord = true;
        }

        public void buildDict(String[] dict) {
            root = new TrieNode();
            for (String word : dict) {
                insert(word);
            }
        }

        public boolean search(String word) {
            return search(root, word.toCharArray(), 0, true);
        }

        private static boolean search(TrieNode next, char[] word, int position, boolean canSkip) {
            while (position < word.length) {
                int index = word[position] - 'a';
                if (canSkip) {
                    for (int j = 0; j < next.children.length; j++) {
                        if (next.children[j] == null || j == index) {
                            continue;
                        }
                        if (search(next.children[j], word, position + 1, false)) {
                            return true;
                        }
                    }
                }
                if (next.children[index] == null) {
                    return false;
                }
                next = next.children[index];
                position++;
            }
            return next != null && next.isEndOfWord && !canSkip;
        }
    }

    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[] { "hello", "hellz" });
        System.out.println(magicDictionary.search("hello"));
    }
}
