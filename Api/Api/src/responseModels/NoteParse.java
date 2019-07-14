package responseModels;

public class NoteParse {
    private int fromId;
    private String fromName;
    private String note;

    public NoteParse(int fromId, String fromName, String note) {
        this.fromId = fromId;
        this.fromName = fromName;
        this.note = note;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
