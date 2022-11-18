package pl.milej.michal.wordofreaders.review.reaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReactionAuthenticationService {

    final ReactionServiceImpl reactionService;

    public boolean isUserIdInReactionIdEqualsPrincipalId(final Long reactionId, final long principalId) {
        return reactionService.findUserReactionById(reactionId).getUser().getId() == principalId;
    }
}