package com.yanll.api.indexdata.vo;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yanll.framework.util.jackson.UtilJackson;

/**
 * Created by YANLL on 2016/06/08.
 */
public class ModuleVO<T> {
    private String module_name; //模块名称
    private String module_icon_url; //模块ICON
    private Integer is_show_module;//是否显示该模块
    private Integer is_show_more;//模块是否有更多按钮
    private String action_url = "";
    private String action_name = "";
    private ObjectNode action_params = UtilJackson.createObjectNode();
    private T data;

    public ModuleVO() {
    }

    /**
     * @param module_name
     * @param module_icon_url
     * @param is_show_module
     * @param is_show_more
     */
    public ModuleVO(String module_name, String module_icon_url, Integer is_show_module, Integer is_show_more) {
        this.module_name = module_name;
        this.module_icon_url = module_icon_url;
        this.is_show_module = is_show_module;
        this.is_show_more = is_show_more;
    }

    public String getAction_url() {
        return action_url;
    }

    public void setAction_url(String action_url) {
        this.action_url = action_url;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getModule_icon_url() {
        return module_icon_url;
    }

    public void setModule_icon_url(String module_icon_url) {
        this.module_icon_url = module_icon_url;
    }

    public Integer getIs_show_module() {
        return is_show_module;
    }

    public void setIs_show_module(Integer is_show_module) {
        this.is_show_module = is_show_module;
    }

    public Integer getIs_show_more() {
        return is_show_more;
    }

    public void setIs_show_more(Integer is_show_more) {
        this.is_show_more = is_show_more;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
