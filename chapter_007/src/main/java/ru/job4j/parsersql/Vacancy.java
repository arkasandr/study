package ru.job4j.parsersql;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Vacancy {
    private String id;
    private String title;
    private String text;
    private String link;
    private LocalDateTime createDate;

    public Vacancy(String id, String title, String text, String link, LocalDateTime createDate) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.link = link;
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString(){
        return String.format("id : %s%stitle : %s%stext : %s%slink : %s%screateDate %s",
                this.id, System.lineSeparator(), this.title, System.lineSeparator(), this.link, System.lineSeparator(),
                this.text, System.lineSeparator(), this.createDate);
    }
}
