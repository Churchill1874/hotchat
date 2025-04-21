package com.ent.hotchat.controller.client;

import com.ent.hotchat.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@Api(tags = "客户管理-上传")
@RequestMapping("/client/upload")
public class FileUploadApi {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/image")
    @ApiOperation(value = "上传图片",notes ="上传图片" )
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        return uploadService.uploadImageToBase64(file);
    }

    @PostMapping("/audio")
    @ApiOperation(value = "上传声音",notes ="上传声音" )
    public String uploadAudio(@RequestParam("file") MultipartFile file) {
        return uploadService.uploadAudioToServer(file);
    }
}
