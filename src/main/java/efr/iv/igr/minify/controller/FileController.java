package efr.iv.igr.minify.controller;

import efr.iv.igr.minify.model.File;
import efr.iv.igr.minify.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api/v1/files")
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public List<File> getFiles() {
        return fileService.findAll();
    }

    @GetMapping("/{id}")
    public File getFile(@PathVariable(name = "id") long id) {
        return fileService.findOne(id);
    }

    @PostMapping
    public File createFile(@RequestBody(required = true) File file) {
        return fileService.save(file);
    }
}