package model.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Mail {
    private int id;
    private User sender;
    private User recipient;
    private LocalDateTime dateTime;
    private String title;
    private List <String> tags;
    private Category category;
    private String message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail = (Mail) o;
        return sender.equals(mail.sender) &&
                recipient.equals(mail.recipient) &&
                dateTime.equals(mail.dateTime) &&
                Objects.equals(title, mail.title) &&
                Objects.equals(tags, mail.tags) &&
                category == mail.category &&
                message.equals(mail.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, recipient, dateTime, title, tags, category, message);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
