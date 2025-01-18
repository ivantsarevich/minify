package efr.iv.igr.minify.repository;

import efr.iv.igr.minify.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
