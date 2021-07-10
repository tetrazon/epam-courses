package by.training.task04.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sentence {
    private List<Word> wordList;

    public Sentence(Sentence sentence) {
        this.wordList = new ArrayList<>(sentence.getWordList());
    }

    /**
     * Appends the specified word to the end of this wordList
     * @param word word to add
     */
    public void addWord(Word word){
        wordList.add(word);
    }

    /**
     * Inserts the specified word at the specified position in this wordList
     * Shifts the element currently at that position (if any)
     * and any subsequent elements to the right (adds one to their indices).
     * @param index insert index
     * @param word word to add
     */
    public void addWordByIndex(int index, Word word){
        wordList.add(index, word);
    }

    /**
     * changes a word by index in the wordList
     * @param index index to change
     * @param newWord new word
     */
    public void changeWordByIndex(int index, Word newWord){
        wordList.set(index, newWord);
    }

    /**
     * removes a word from wordList
     * @param index index to remove
     */
    public void removeWordByIndex(int index){
        wordList.remove(index);
    }

    /**
     * clears the sentence
     */
    public void clear(){
        wordList.clear();
    }

    @Override
    public String toString() {
        String stringBuilder;
        String value = wordList.get(0).getValue();
        stringBuilder = IntStream
                .range(1, wordList.size())
                .mapToObj(i -> wordList.get(i).getValue() + " ")
                .collect(Collectors.joining("",
                        value.substring(0, 1).toUpperCase() + value.substring(1) + " ",
                        ".\n"));
        return stringBuilder;
    }
}
