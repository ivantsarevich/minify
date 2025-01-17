package efr.iv.igr.minify.service;

import efr.iv.igr.minify.model.File;
import efr.iv.igr.minify.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<File> findAll() {
        return fileRepository.findAll();
    }

    public File findOne(long id) {
        return fileRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("File not found!"));
    }

    public File save(File file) {
        return fileRepository.save(file);
    }
}
