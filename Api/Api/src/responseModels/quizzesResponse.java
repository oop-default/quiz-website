package responseModels;

public class quizzesResponse {
    private int id;
    private String title;
    private int friendId;
    private String creator;

    public quizzesResponse(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public quizzesResponse(int id, String title, int friendId, String creator) {
        this.id = id;
        this.title = title;
        this.friendId = friendId;
        this.creator = creator;
    }

}
