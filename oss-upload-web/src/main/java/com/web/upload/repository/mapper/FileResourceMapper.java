package com.web.upload.repository.mapper;

import com.web.upload.repository.model.bo.FileNameBO;
import com.web.upload.repository.model.entity.FileResource;

import java.util.List;

public interface FileResourceMapper {
    int deleteByPrimaryKey(Long fId);

    int insert(FileResource record);

    int insertSelective(FileResource record);

    FileResource selectByPrimaryKey(Long fId);

    int updateByPrimaryKeySelective(FileResource record);

    int updateByPrimaryKey(FileResource record);

    List<FileNameBO> findAllNames();
}