package com.web.upload.service;

import com.web.upload.repository.model.vo.resp.ImageUrlRespVO;

import java.util.List;

/**
 * @author Lcp
 * @version 1.0
 * @date 2023/5/15 12:40
 */
public interface FileService {

    /**
     * 保存文件
     *
     * @param name 文件名
     * @param url  文件url
     */
    void save(String name, String url);

    /**
     * 查询所有文件名
     *
     * @return 文件名列表
     */
    List<String> findAllNames();

    /**
     * 获取文件url
     *
     * @param fileName 文件名
     * @return 文件url
     */
    ImageUrlRespVO getImageUrl(String fileName);
}
