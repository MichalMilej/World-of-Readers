package pl.milej.michal.wordofreaders.user;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.milej.michal.wordofreaders.exception.RequiredResourceNotInDatabaseException;
import pl.milej.michal.wordofreaders.user.profile.photo.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    final UserRequestValidator userRequestValidator;
    final UserRepository userRepository;
    final ProfilePhotoRepository profilePhotoRepository;
    final ProfilePhotoServiceImpl profilePhotoService;

    @Override
    public UserResponse addUser(final UserRequest userRequest, final Role role) {
        userRequestValidator.validateUserRequest(userRequest);
        final User savedUser = saveUser(userRequest, role);
        return UserConverter.convertToUserResponse(savedUser);
    }

    private User saveUser(final UserRequest userRequest, final Role role) {
        final User newUser = new User();

        newUser.setUsername(userRequest.getUsername());
        newUser.setHashedPassword(DigestUtils.sha256Hex(userRequest.getPassword()));
        newUser.setEmail(userRequest.getEmail());
        newUser.setRole(role);
        final ProfilePhoto defaultProfilePhoto = profilePhotoRepository.findById(1L).orElseThrow(() -> {
            throw new RequiredResourceNotInDatabaseException("Default user profile photo not saved to database");
        });
        newUser.setProfilePhoto(defaultProfilePhoto);

        return userRepository.save(newUser);
    }

    @Override
    public UserResponse getUser(Long id) {
        return UserConverter.convertToUserResponse(findUserById(id));
    }

    @Override
    public FileSystemResource getUserProfilePhotoImage(Long id) {
        final User user = findUserById(id);
        final ProfilePhoto photo = profilePhotoRepository.findById(user.getProfilePhoto().getId()).orElseThrow(() -> {
            throw new ResourceNotFoundException("User profile photo not found in database");
        });
        return profilePhotoService.getProfilePhotoImage(photo.getId());
    }

    @Override
    public UserResponse updateUserProfilePhoto(final Long id, final MultipartFile profilePhotoImage) {
        final User existingUser = findUserById(id);

        final ProfilePhoto newProfilePhoto = profilePhotoService.addProfilePhoto(profilePhotoImage);
        existingUser.setProfilePhoto(newProfilePhoto);

        return UserConverter.convertToUserResponse(userRepository.save(existingUser));
    }

    private User findUserById(final Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("User not found");
        });
    }
}