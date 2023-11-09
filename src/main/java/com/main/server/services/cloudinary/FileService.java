package com.main.server.services.cloudinary;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FilesValidation filesValidation;
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        return new Cloudinary().uploader()
                .upload(multipartFile.getBytes(), Map.of("public_id", UUID.randomUUID().toString()))
                .get("url")
                .toString();
    }

    public List<String> uploadMultiFile(MultipartFile[] files) throws IOException {
        EStatus eStatus = filesValidation.validate(files);
        switch (eStatus){
            case STATUS_EMPTY_FILE -> {
                throw new IOException("Lack of files");
            }
            case STATUS_WRONG_EXT -> {
                throw new IOException("Format is not supported");
            }
            default -> {
                List<String> fileNames = new ArrayList<>();
                for(MultipartFile file : files){
                    fileNames.add(uploadFile(file));
                }
                return fileNames;
            }
        }
    }
}
