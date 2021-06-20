package by.training.task04.entity;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class Word {
    @NonNull
    private String value;

    @Override
    public String toString() {
        return value ;
    }
}
