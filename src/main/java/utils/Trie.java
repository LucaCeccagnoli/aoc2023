package utils;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Map;

public class Trie {
    private final Node root;

    private class Node {
        public Map<Character, Node> children;
        private String content;
        boolean isWordEnd;

        public Node(String content){
            this.content = content;
            this.isWordEnd = false;
            this.children = new HashMap<>();
        }
    }

    public Trie(){
        this.root = new Node("");
    }

    public void insert(String word){
        Node current = root;

        for(char currentChar : word.toCharArray()){
            if(current.children.containsKey(currentChar)){
                // go to the node that contains the next character
                current = current.children.get(currentChar);
            }else{
                // add a link to a new node
                Node newNode = new Node(current.content + currentChar);
                current.children.put(currentChar, newNode);
                current = newNode;
            }
        }

        current.isWordEnd = true;
    }

    public int find(String word){
        Node currentNode = this.root;
        int endsAtDepth = -1;
        for(char currentChar : word.toCharArray()){
            if(currentNode.children.containsKey(currentChar)){
                currentNode = currentNode.children.get(currentChar);
                endsAtDepth++;
            } else if (currentNode.isWordEnd){
                break;
            } else {
                break;
            }
        }
        return currentNode.isWordEnd ? endsAtDepth : -1;
    }

    public int findReverse(String word){
        Node currentNode = this.root;
        int foundAtDepth = -1;

        CharacterIterator itr = new StringCharacterIterator(word);
        char currentChar = itr.last();
        while(currentChar != CharacterIterator.DONE){
            if(currentNode.children.containsKey(currentChar)){
                currentNode = currentNode.children.get(currentChar);
                foundAtDepth++;

                currentChar = itr.previous();
            } else {
                return -1;
            }
        }
        return foundAtDepth;
    }

    /**
     * find the first word in the trie that is also contained in the input string
     * return the word if it is found, otherwise return an empty string
     * 
     * @param input
     * @return
     */
    public String findAnyFirst(String input){
        int endsAtDepth = 0;
        for(int i = 0; i < input.length(); i++){
            String currentWord = input.substring(i);
            if( (endsAtDepth = find(currentWord)) != -1 ){
                return input.substring(i, i + endsAtDepth + 1);
            }
        }
        return "";
    }

    public String findAnyFirstReverse(String input){
        int endsAtDepth = 0;
        for(int i = 0; i <= input.length(); i++){
            if( (endsAtDepth = find(input.substring( input.length() - i))) != -1 ){
                return input.substring(input.length() - i, input.length() - i + endsAtDepth + 1);
            }
        }
        return "";
    }
}
