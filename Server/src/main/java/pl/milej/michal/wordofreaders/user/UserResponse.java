package pl.milej.michal.wordofreaders.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private Role role;
    private Long profilePhotoId;
    private boolean banned;
}