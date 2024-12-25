package itmo.is.lab1.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class MinIOConfig {

    @Value("${minio.port}")
    private String minioPort;

    @Value("${minio.file.import.bucket}")
    private String minioBucket;

    @Bean
    public MinioClient minioClient()
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://127.0.0.1:" + minioPort)
                            .credentials("1sLM1jn7JXgenD9S7ouF", "KXExtlxW8lQMeQcCc4ufQscLcnb9SI3wvqYS4ypW")
                            .build();

            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioBucket).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioBucket).build());
            } else {
                System.out.println("Bucket 'file_imports' already exists.");
            }
            return minioClient;
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
        return null;
    }
}
