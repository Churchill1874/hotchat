package com.ent.hotchat.service.serviceimpl;

import com.ent.hotchat.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Base64;
import java.util.UUID;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Value("${file.upload-dir:/opt/files/audio/}")
    private String audioUploadDir;

    @Override
    public String uploadImageToBase64(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            throw new RuntimeException("图片处理失败: " + e.getMessage(), e);
        }
    }

    @Override
    public String uploadAudioToServer(MultipartFile file) {
        try {
            Path uploadPath = Paths.get(audioUploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String filename = UUID.randomUUID().toString() + "." + extension;
            Path fullPath = uploadPath.resolve(filename);

            // 使用 transferTo 方法
            file.transferTo(fullPath.toFile());

            // 关键：设置文件为所有用户可读，防止 nginx 无法访问
            fullPath.toFile().setReadable(true, false);
            log.info("音频文件已上传成功，路径: {}", fullPath.toAbsolutePath());
            return "http://119.28.214.138/files/audio/" + filename;
        } catch (IOException e) {
            log.error("音频上传失败: {}", e.getMessage());
            throw new RuntimeException("音频上传失败: " + e.getMessage(), e);
        }
    }

}