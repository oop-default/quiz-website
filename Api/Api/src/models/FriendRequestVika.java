package models;

public class FriendRequestVika {
    private int idReceiver, idSender;
    private String status;

    public FriendRequestVika(int idReceiver, int idSender, String status) {
        this.idReceiver = idReceiver;
        this.idSender = idSender;
        this.status = status;
    }

    public int getIdReceiver() {
        return idReceiver;
    }

    public int getIdSender() {
        return idSender;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "idReceiver: " + idReceiver + ", idSender: " + idSender + ", status: " + status;
    }
}
