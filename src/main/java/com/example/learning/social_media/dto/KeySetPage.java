package com.example.learning.social_media.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class KeySetPage<T, C> {
    List<T> items;
    C lastCursor;
    boolean hasMore;
    public static <T, C> KeySetPage<T, C> of(List<T> items, int pageSize, java.util.function.Function<T, C> cursorExtractor) {
        boolean hasMore = items.size() > pageSize;

        if(hasMore) {
            items = items.subList(0, pageSize);
        }

        C lastCursor = items.isEmpty()
                ? null
                : cursorExtractor.apply(items.getLast());

        return new KeySetPage<>(items, lastCursor, hasMore);
    }
}
