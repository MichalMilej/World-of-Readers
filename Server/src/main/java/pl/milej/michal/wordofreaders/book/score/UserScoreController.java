package pl.milej.michal.wordofreaders.book.score;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.milej.michal.wordofreaders.book.BookResponse;

import java.util.Map;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
@Transactional
public class UserScoreController {

    final UserScoreServiceImpl userScoreService;

    @PostMapping("/{bookId}/user-scores")
    Map<String, Object> addUserScore(@PathVariable long bookId, @RequestBody UserScoreRequest userScoreRequest) {
        return userScoreService.addUserScore(bookId, userScoreRequest);
    }

    @GetMapping("/user-scores/{userScoreId}")
    UserScoreResponse getUserScore(@PathVariable Long userScoreId) {
        return userScoreService.getUserScore(userScoreId);
    }

    @GetMapping("/{bookId}/user-score/user/{userId}")
    UserScoreResponse getUserScoreByBookIdAndUserId(@PathVariable long bookId, @PathVariable long userId) {
        return userScoreService.getUserScoreByUserId(bookId, userId);
    }

    @PatchMapping("/user-scores/{userScoreId}")
    Map<String, Object> updateUserScore(@PathVariable long userScoreId,
                                        @RequestBody final UserScoreRequest userScoreRequest) {
        return userScoreService.updateUserScore(userScoreId, userScoreRequest);
    }

    @DeleteMapping("user-scores/{userScoreId}")
    BookResponse deleteUserScore(@PathVariable long userScoreId) {
        return userScoreService.deleteUserScore(userScoreId);
    }
}
