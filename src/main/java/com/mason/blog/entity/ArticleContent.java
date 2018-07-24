package com.mason.blog.entity;

public class ArticleContent {
    private Long id;

    private Long article_id;

    private String create_by;

    private String modifield_by;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public String getModifield_by() {
        return modifield_by;
    }

    public void setModifield_by(String modifield_by) {
        this.modifield_by = modifield_by;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}