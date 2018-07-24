package com.mason.blog.entity;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/7/4
 * Time: 14:24
 */
public class ArticalSortInfo {

    private String sortNames;

    private Long id;

    private String title;

    private Boolean is_top;

    private Integer traffic;

    public Integer getTraffic() {
        return traffic;
    }

    public void setTraffic(Integer traffic) {
        this.traffic = traffic;
    }

    public String getSortIds() {
        return sortIds;
    }

    public void setSortIds(String sortIds) {
        this.sortIds = sortIds;
    }

    private String sortIds;

    //private Integer traffic;
    private String content;

    private String create_by;

    private String modified_by;

    private String summary;

    private Boolean is_effective;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getIs_top() {
        return is_top;
    }

    public void setIs_top(Boolean is_top) {
        this.is_top = is_top;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
//    public Integer getTraffic() {
//        return traffic;
//    }
//
//    public void setTraffic(Integer traffic) {
//        this.traffic = traffic;
//    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public String getModified_by() {
        return modified_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }
    public Boolean getIs_effective() {
        return is_effective;
    }

    public void setIs_effective(Boolean is_effective) {
        this.is_effective = is_effective;
    }



    public String getSortNames() {
        return sortNames;
    }

    public void setSortNames(String sortNames) {
        this.sortNames = sortNames;
    }


}
