package pl.upside.bearbnbbackend.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.UUID;
import java.util.stream.Stream;

public interface FileStorageService {
    public void init();
    public void save(MultipartFile file, String listingId);
    public Resource load(String filename);
    public void deleteAll();
    public Stream<Path> loadAll();
}
