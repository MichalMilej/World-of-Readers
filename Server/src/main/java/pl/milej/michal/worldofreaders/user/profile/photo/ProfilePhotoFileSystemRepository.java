package pl.milej.michal.worldofreaders.user.profile.photo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import pl.milej.michal.worldofreaders.exception.BadServerRequestException;
import pl.milej.michal.worldofreaders.exception.SavingFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Repository
public class ProfilePhotoFileSystemRepository {

    @Value("${filesystem.path.images}/profile photos/")
    private String directory;

    public String saveProfilePhoto(final MultipartFile profilePhoto) {
        final Path path = Paths.get(directory + new Date().getTime() + "-" + profilePhoto.getOriginalFilename());
        try {
            Files.write(path, profilePhoto.getBytes());
        } catch (IOException e) {
            throw new SavingFileException("There was a problem with saving profile photo");
        }
        return path.toAbsolutePath().toString();
    }

    public FileSystemResource getProfilePhoto(final String location) {
        return new FileSystemResource(Paths.get(location));
    }

    public void deleteProfilePhoto(final String location) {
        final Path path = Paths.get(location);
        try {
            Files.delete(path);
        } catch (IOException e ) {
            throw new BadServerRequestException("File location not found");
        }
    }
}
