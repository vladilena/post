package model.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Mail {
    private int id;
    private int sender;
    private int recipient;
    private Timestamp dateTime;
    private String title;
    private String tags;
    private int category;
    private String message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail = (Mail) o;
        return category == mail.category &&
                Objects.equals(dateTime, mail.dateTime) &&
                title.equals(mail.title) &&
                Objects.equals(tags, mail.tags) &&
                message.equals(mail.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, title, tags, category, message);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "id=" + id +
                ", sender=" + sender +
                ", recipient=" + recipient +
                ", dateTime=" + dateTime +
                ", title='" + title + '\'' +
                ", tags=" + tags +
                ", category=" + category +
                ", message='" + message + '\'' +
                '}';
    }
}
