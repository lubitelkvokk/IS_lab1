package itmo.is.lab1.service.file;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import itmo.is.lab1.model.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinIOService {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.file.import.bucket}")
    private String minioBucket;

    public void saveObjectToBucket(MultipartFile file, User user) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.putObject(PutObjectArgs
                .builder()
                .bucket(minioBucket)
                .object(user.getUsername() + "_" + file.getOriginalFilename())
                .stream(file.getInputStream(), file.getSize(), -1)
                .build());
    }

    public InputStream getObjectFromBucket(String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.getObject(GetObjectArgs
                .builder()
                .bucket(minioBucket)
                .object(fileName)
                .build());

    }
}
