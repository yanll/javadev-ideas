package com.yanll.api.indexdata.vo;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by YANLL on 2016/05/04.
 */
public class IndexDataVO {
    private String title;
    private String url;
    private String img_url;
    private String action_name;
    private ObjectNode action_params;

    public IndexDataVO() {
    }

    public IndexDataVO(String title, String img_url, String action_name, ObjectNode action_params) {
        this.title = title;
        this.img_url = img_url;
        this.action_name = action_name;
        this.action_params = action_params;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public ObjectNode getAction_params() {
        return action_params;
    }

    public void setAction_params(ObjectNode action_params) {
        this.action_params = action_params;
    }

}
