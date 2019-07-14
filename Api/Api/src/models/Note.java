package models;

public class Note {
    private String text;
    private int receiverId;

    public Note(String text, int receiverId) {
        this.text = text;
        this.receiverId = receiverId;
    }

    public String getText() {
        return text;
    }

    public int getReceiverId() {
        return receiverId;
    }

    @Override
    public String toString() {
        return "Note [text=" + text + ", receiverId=" + receiverId + "]";
    }
}
