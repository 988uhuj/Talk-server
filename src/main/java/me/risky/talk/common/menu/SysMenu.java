package me.risky.talk.common.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sureone
 * Date: 7/18/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class SysMenu implements Serializable {
    private int id;
    private String label;
    private String url;
    private boolean active = false;
    private int parentId;
    private List<SysMenu> subMenus;
    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SysMenu(int id, String label, String url, boolean active, int parentId, String code) {
        this.id = id;
        this.label = label;
        this.url = url;
        this.active = active;
        this.parentId = parentId;
        this.code=code;
    }

    public void addSubMenu(int id,String label,String url,boolean active,String code){
        SysMenu sysMenu = new SysMenu(id,label,url,active,this.id,code);
        if(subMenus==null) subMenus=new ArrayList<SysMenu>();
        subMenus.add(sysMenu);
    }

    public void addSubMenu(SysMenu sysMenu){
        sysMenu.parentId=this.id;
        if(subMenus==null) subMenus=new ArrayList<SysMenu>();
        subMenus.add(sysMenu);
    }
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<SysMenu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<SysMenu> subMenus) {
        this.subMenus = subMenus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
