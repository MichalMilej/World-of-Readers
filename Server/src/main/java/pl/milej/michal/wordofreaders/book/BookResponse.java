package pl.milej.michal.wordofreaders.book;

import lombok.Builder;
import lombok.Data;
import pl.milej.michal.wordofreaders.author.Author;
import pl.milej.michal.wordofreaders.book.cover.Cover;

import java.sql.Date;
import java.util.Set;

@Data
@Builder
public class BookResponse {

    private long id;
    private String title;
    private Date releaseDate;
    private String description;
    private Set<Author> authors;
    private Cover cover;
}