package model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Mail {
    private long id;
    private String sender;
    private String recipient;
    private LocalDateTime dateTime;
    private String title;
    private ArrayList <String> tags;
    private Category category;
    private Category customCategory;
    private String message;
    private User relatedUser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
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

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCustomCategory() {
        return customCategory;
    }

    public void setCustomCategory(Category customCategory) {
        this.customCategory = customCategory;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getRelatedUser() {
        return relatedUser;
    }

    public void setRelatedUser(User relatedUser) {
        this.relatedUser = relatedUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail = (Mail) o;
        return sender.equals(mail.sender) &&
                recipient.equals(mail.recipient) &&
                Objects.equals(title, mail.title) &&
                Objects.equals(tags, mail.tags) &&
                Objects.equals(category, mail.category) &&
                message.equals(mail.message) &&
                relatedUser.equals(mail.relatedUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, recipient, title, tags, category, message, relatedUser);
    }
}
