package com.ent.hotchat.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String uploadImageToBase64(MultipartFile file);
    String uploadAudioToServer(MultipartFile file);
}