package com.web.upload.service.impl;

import com.web.upload.repository.model.bo.FileNameBO;
import com.web.upload.repository.model.entity.FileResource;
import com.web.upload.repository.mapper.FileResourceMapper;
import com.web.upload.repository.model.vo.resp.ImageUrlRespVO;
import com.web.upload.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Lcp
 * @version 1.0
 * @date 2023/5/15 12:41
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileResourceMapper fileResourceMapper;


    @Override
    public void save(String name, String url) {
        String type = url.substring(url.lastIndexOf(".") + 1);
        FileResource fileResource = FileResource.builder()
                .name(name)
                .type(type)
                .url(url)
                .updateTime(LocalDateTime.now())
                .createTime(LocalDateTime.now())
                .build();
        fileResourceMapper.insert(fileResource);
    }

    @Override
    public List<String> findAllNames() {
        List<FileNameBO> nameBOS = fileResourceMapper.findAllNames();
        return nameBOS.stream().map(nameBO -> nameBO.getId() + "__" + nameBO.getName())
                .collect(Collectors.toList());
    }

    @Override
    public ImageUrlRespVO getImageUrl(String fileName) {
        // fileName格式为id__name, 解析出id
        Long id = Long.valueOf(fileName.substring(0, fileName.indexOf("__")));
        FileResource fileResource = fileResourceMapper.selectByPrimaryKey(id);
        ImageUrlRespVO respVO = new ImageUrlRespVO();
        respVO.setUrl(Objects.nonNull(fileResource) ? fileResource.getUrl() : null);
        return respVO;
    }
}
