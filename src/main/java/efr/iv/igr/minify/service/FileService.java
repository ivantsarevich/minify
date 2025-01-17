package efr.iv.igr.minify.service;

import efr.iv.igr.minify.model.File;
import efr.iv.igr.minify.repository.FileRepository;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class FileService {
    private final FileRepository fileRepository;

    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucket;

    @Autowired
    public FileService(FileRepository fileRepository, MinioClient minioClient) {
        this.fileRepository = fileRepository;
        this.minioClient = minioClient;
    }

    public List<File> findAll() {
        return fileRepository.findAll();
    }

    public File findById(long id) {
        return fileRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("File not found!"));
    }

    public File upload(MultipartFile multipartFile) throws IOException, ServerException, InsufficientDataException,
            ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException,
            XmlParserException, InternalException {
        String filename = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        long size = multipartFile.getSize();
        InputStream inputStream = multipartFile.getInputStream();

        File file = File
                .builder()
                .filename(filename)
                .contentType(contentType)
                .size(size)
                .build();

        minioClient.putObject(PutObjectArgs
                .builder()
                .bucket(bucket)
                .object(filename)
                .contentType(contentType)
                .stream(inputStream, size, -1)
                .build());

        return fileRepository.save(file);
    }

    public InputStream download(long id) throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        File file = findById(id);
        return minioClient.getObject(GetObjectArgs
                .builder()
                .bucket(bucket)
                .object(file.getFilename())
                .build());
    }
}
