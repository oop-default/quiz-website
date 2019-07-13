package responseModels;

public class FriendAchievements {
    private int id;
    private String user;
    private String achievement;

    public FriendAchievements(int id,String user,String achievement) {
        this.id = id;
        this.user = user;
        this.achievement = achievement;
    }
}
