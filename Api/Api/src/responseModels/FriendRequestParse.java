package responseModels;

public class FriendRequestParse {
    private String fromName;
    private int fromId;


    public FriendRequestParse(String fromName, int fromId) {
        this.fromName = fromName;
        this.fromId = fromId;
    }

    public void setFromId(int fromId) { this.fromId = fromId; }

    public int getFromId() {
        return fromId;
    }

    public void getfromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromName() {
        return fromName;
    }

}
