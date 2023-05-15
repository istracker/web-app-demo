package com.web.upload.controller;

import com.alibaba.fastjson2.JSONObject;
import com.web.upload.oss.OSSComponent;
import com.web.upload.repository.model.bo.AliyunOssSts;
import com.web.upload.repository.model.vo.req.SaveReqVO;
import com.web.upload.repository.model.vo.resp.ImageUrlRespVO;
import com.web.upload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    private FileService fileService;
    @Autowired
    private OSSComponent ossComponent;


    @PostMapping("/save")
    public void saveImage(@RequestBody SaveReqVO reqVO) {
        fileService.save(reqVO.getName(), reqVO.getUrl());
    }

    @GetMapping("/imageList")
    public List<String> getImageList() {
        return fileService.findAllNames();
    }

    @GetMapping("/image/{fileName}")
    public ImageUrlRespVO test(@PathVariable String fileName) {
        return fileService.getImageUrl(fileName);
    }


    @GetMapping("/signature")
    public AliyunOssSts getSignature() {
        return ossComponent.getSignature();
    }


}
