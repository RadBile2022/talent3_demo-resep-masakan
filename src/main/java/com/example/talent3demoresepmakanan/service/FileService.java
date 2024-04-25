package com.example.talent3demoresepmakanan.service;

import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class FileService {
    private final MinioClient minioClient;

    private final Long DEFAULT_EXPIRY_SECONDS = 3600L;

    public void upload(MultipartFile file, String bucket, String filename) throws ServerException, InsufficientDataException, ErrorResponseException, InvalidResponseException, XmlParserException, InternalException, IOException, NoSuchAlgorithmException, InvalidKeyException{
        try{
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(filename)
                            .stream(file.getInputStream(), file.getSize(), 0)
                            .contentType(file.getContentType())
                            .build()
            );
        } catch (MinioException e){
            log.error("Error: {}", e);
            throw e;
        } catch (Exception e){
            log.error("Unexpected error: {}");
            throw e;
        }
    }

    public InputStreamResource download(String objectName, String bucketName){
        try{
            GetObjectArgs args = GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();

            InputStream inputStream = minioClient.getObject(args);
            return new InputStreamResource(inputStream);
        }catch (MinioException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void view(HttpServletResponse response, String bucket, String filename) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        try {
            response.sendRedirect(this.getLink(bucket,filename, DEFAULT_EXPIRY_SECONDS));
        }catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
                |InvalidResponseException | NoSuchAlgorithmException | XmlParserException | ServerException
                | IllegalArgumentException | IOException e ){
            throw e;
        }
    }

    public String getLink(String bucket, String filename, Long expiry)  throws ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, IOException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket)
                            .object(filename)
                            .expiry(Math.toIntExact(expiry), TimeUnit.SECONDS)
                            .build()
            );
        }catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
                |InvalidResponseException | NoSuchAlgorithmException | XmlParserException | ServerException
                | IllegalArgumentException | IOException e ){
            throw e;
        }
    }





    }
