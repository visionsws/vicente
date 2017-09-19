package com.fusio.tag.model.autogen;

import java.io.Serializable;
import java.util.Date;

public class TagGroupJh implements Serializable {
    private String tagGroupId;

    private String groupName;

    private String path;

    private String parentTagGroupId;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getTagGroupId() {
        return tagGroupId;
    }

    public void setTagGroupId(String tagGroupId) {
        this.tagGroupId = tagGroupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParentTagGroupId() {
        return parentTagGroupId;
    }

    public void setParentTagGroupId(String parentTagGroupId) {
        this.parentTagGroupId = parentTagGroupId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}