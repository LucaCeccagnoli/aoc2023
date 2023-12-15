package utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TrieTest {

    @Test
    public void whenWordIsSamefindReturnsWordLength(){
        Trie trie = new Trie();
        String word = "test";

        trie.insert(word);
        assertEquals(trie.find(word), word.length()-1);
    }

    @Test
    public void whenWordNotAtTheBeginningFindReturnsNotFound(){
        Trie trie = new Trie();
        String word = "test";
        String toSearch = "atest";

        trie.insert(word);
        assertEquals(trie.find(toSearch), -1);
    }

    @Test
    public void whenWordHasTextAfterFindShouldReturnWordLength(){
        Trie trie = new Trie();
        String word = "test";
        String toSearch = "testaaa";

        trie.insert(word);
        assertEquals(trie.find(toSearch), word.length()-1);
    }

    @Test 
    public void whenWordNotAtTheBeginningFindAnyFirstReturnsWord(){
        Trie trie = new Trie();
        String toInsert = "test";
        String toSearch = "aaatestbbbb";

        trie.insert(toInsert);
        assertEquals(trie.findAnyFirst(toSearch), toInsert );
    }

    @Test 
    public void whenTwoWordsFindAnyFirstReturnsFirst(){
        Trie trie = new Trie();
        String toInsert1 = "test1";
        String toInsert2 = "test2";
        String toSearch = "aatest1bbtest2cc";

        trie.insert(toInsert1);
        trie.insert(toInsert2);
        assertEquals(trie.findAnyFirst(toSearch), toInsert1 );
    }

    @Test 
    public void whenWordNotInStringFindAnyFirstReturnsEmptyString(){
        Trie trie = new Trie();
        String toInsert = "test";
        String toSearch = "aaabbbb";

        trie.insert(toInsert);
        assertEquals(trie.findAnyFirst(toSearch), "" );
    }
}
