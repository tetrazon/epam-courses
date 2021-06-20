package by.training.task04.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Text {
    private String head;
    private List<Sentence> sentenceList;

    public Text() {
    }

    /**
     *Appends the specified sentence to the end of this sentencesList
     * @param sentence
     */
    public void addSentence(Sentence sentence){
        sentenceList.add(sentence);
    }

    /**
     * Inserts the specified sentence at the specified position in this sentencesList
     * Shifts the element currently at that position (if any)
     * and any subsequent elements to the right (adds one to their indices).
     * @param sentence sentence to add
     * @param index insert index
     */
    public void addSentenceByIndex(Sentence sentence, int index){
        sentenceList.add(index, sentence);
    }

    /**
     * changes a sentence by index in the sentencesList
     * @param index index to change
     * @param newSentence new sentence
     */
    public void changeSentenceByIndex(int index, Sentence newSentence){
        sentenceList.set(index, newSentence);
    }

    /**
     * removes a sentence from sentencesList
     * @param index index to remove
     */
    public void removeSentenceByIndex(int index){
        sentenceList.remove(index);
    }

    /**
     * clears the sentencesList
     * In order to implement composition relation between
     * the text and sentencesList, before clearing the text
     * we need to clear sentencesList
     */
    public void clear(){
        sentenceList.forEach(Sentence::clear);
        sentenceList.clear();
    }

    public String SentencesToString(){
        final StringBuilder sb = new StringBuilder();
        for (Sentence sentence : sentenceList) {
            sb.append(sentence);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(head).append('\n');
        for (Sentence sentence : sentenceList) {
            sb.append(sentence);
        }
        return sb.toString();
    }
}
