package pl.milej.michal.worldofreaders.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserRequest {

    private String username;
    private String password;
    private String email;
}
