/**
 * @author WeiYuan
 * @version 0.1
 * @since JDK 11.0.2
 */
public class UserInfo {
    private String userCookie; //用户Cookie
    private String channelID; //频道ID
    private String puid; //公众号ID，比如什么什么学院频道
    private String groupID; //小组ID,班频道

    public UserInfo(){}

    public UserInfo(String userCookie, String channelID, String puid, String groupID) {
        this.userCookie = userCookie;
        this.channelID = channelID;
        this.puid = puid;
        this.groupID = groupID;
    }

    public String getUserCookie() {
        return userCookie;
    }

    public void setUserCookie(String userCookie) {
        this.userCookie = userCookie;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public String getPuid() {
        return puid;
    }

    public void setPuid(String puid) {
        this.puid = puid;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    //private String articleId; //文章ID，回复文章会用到
}