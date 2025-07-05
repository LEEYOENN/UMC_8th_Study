package umc.spring_study.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import umc.spring_study.config.AmazonConfig;
import umc.spring_study.domain.Uuid;
import umc.spring_study.repository.UuidRepository.UuidRepository;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class AmazonS3Manager {

    private final AmazonS3 amazonS3;

    private final AmazonConfig amazonConfig;

    private final UuidRepository uuidRepository;

    public String uploadFile(String keyName, MultipartFile file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            amazonS3.putObject(new PutObjectRequest(
                    amazonConfig.getBucket(), keyName, file.getInputStream(), metadata));
        } catch (IOException e) {
            log.error("error at AmazonS3Manager uploadFile : {}", (Object) e.getStackTrace());
        }

        return amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();
    }

    public String generateReviewKeyName(Uuid uuid, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extension = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf('.')); //ex .png
        }
        return amazonConfig.getReviewPath() + '/' + uuid.getUuid() + extension;
    }

    public void deleteFile(String keyName) {
        if (amazonS3.doesObjectExist(amazonConfig.getBucket(), keyName)) {
            amazonS3.deleteObject(amazonConfig.getBucket(), keyName);
            log.info("Deleted file: {}", keyName);
        } else {
            log.info("File does not exist: {}", keyName);
        }

    }

//    public void deleteReviewImage(String uuidWithExtension) {
//        s3Manager.deleteFile("review/" + uuidWithExtension);
//    }


}
