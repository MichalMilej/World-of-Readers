package pl.milej.michal.wordofreaders.book.review;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import pl.milej.michal.wordofreaders.user.UserResponsePublic;

import java.sql.Date;

@Data
@Builder
public class ReviewResponse {

    private Long id;
    private String text;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date publicationDate;
    private Integer likes;
    private Integer dislikes;
    private Long bookId;
    private UserResponsePublic userResponsePublic;
}