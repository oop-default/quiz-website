package models;

public class FriendRequest {
    private String status;
    private int senderId, receiverId;


    public FriendRequest(String status, int senderId) {
        this.status = status;
        this.senderId = senderId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getStatus() {
        return status;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    @Override
    public String toString() {
        return "FriendRequest [status=" + status + ", senderId=" + senderId + ", receiverId=" + receiverId + "]";
    }
}
