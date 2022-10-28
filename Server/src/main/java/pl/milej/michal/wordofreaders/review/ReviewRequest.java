package pl.milej.michal.wordofreaders.review;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewRequest {
    private String text;
    private Long bookId;
    private Long userId;
}
