package com.web.upload.repository.model.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * tb_file_resource
 * @author 
 */
@Data
@Builder
public class FileResource {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件路径
     */
    private String url;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}