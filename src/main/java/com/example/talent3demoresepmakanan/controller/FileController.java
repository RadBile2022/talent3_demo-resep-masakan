package com.example.talent3demoresepmakanan.controller;

import com.example.talent3demoresepmakanan.dto.categories.GeneralResponseDTO;
import com.example.talent3demoresepmakanan.dto.file_storage.UploadFileDto;
import com.example.talent3demoresepmakanan.service.FileService;
import io.minio.errors.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/minio")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileController {
    private final FileService fileService;

    @Value("${minio.bucket}")
    private String defaultBucketName;

    @PostMapping("/upload")
    public GeneralResponseDTO uploadFileWithMinio(@ModelAttribute UploadFileDto req) throws ServerException, InsufficientDataException, InternalException, ErrorResponseException, NoSuchAlgorithmException, IOException, InvalidKeyException, InvalidResponseException, XmlParserException {
        fileService.upload(req.getFile(), defaultBucketName,req.getFilename());
        return GeneralResponseDTO.builder()
                .isSuccess(true)
                .message("File berhasil diupload")
                .build();
    }

    @GetMapping("/view")
    public void getFileFromMinio(@RequestParam("filename") String filename, HttpServletResponse response) throws IOException, ServerException, InsufficientDataException,ErrorResponseException,NoSuchAlgorithmException,InvalidKeyException,InvalidResponseException,XmlParserException,InternalException{
        fileService.view(response,defaultBucketName,filename);
    }

    @PostMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("filename") String objectName){
        InputStreamResource fileDownloaded = fileService.download(objectName, defaultBucketName);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + objectName + "\"")
                .body(fileDownloaded);

    }

}
