package efr.iv.igr.minify.repository;

import efr.iv.igr.minify.model.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Disabled
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FileRepositoryTests {
    @Autowired
    private FileRepository fileRepository;

    @Test
    void fileRepositorySaveMethodTest() {
        File file = File
                .builder()
                .filename("text.txt")
                .fileOriginalName("text.txt")
                .contentType("plain/text")
                .filePath("plain/text")
                .build();
        fileRepository.save(file);

        Assertions.assertEquals(1, fileRepository.count());
    }

    @Test
    void fileRepositoryFindByIdTest() {
        Assertions.assertNull(fileRepository.findById(1L).orElse(null));
    }
}
