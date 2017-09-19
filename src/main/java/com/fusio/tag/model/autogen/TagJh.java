package com.fusio.tag.model.autogen;

import java.io.Serializable;
import java.util.Date;

public class TagJh implements Serializable {
    private String tagId;

    private String tagName;

    private String path;

    private String tagGroupId;

    private String parentTagId;

    private Date createTime;

    private Integer level;

    private static final long serialVersionUID = 1L;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTagGroupId() {
        return tagGroupId;
    }

    public void setTagGroupId(String tagGroupId) {
        this.tagGroupId = tagGroupId;
    }

    public String getParentTagId() {
        return parentTagId;
    }

    public void setParentTagId(String parentTagId) {
        this.parentTagId = parentTagId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}