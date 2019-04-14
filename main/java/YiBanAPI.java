import okhttp3.*;

import java.util.Vector;
import java.util.regex.*;
import java.io.IOException;

/**
 * @author WeiYuan
 * @version 0.1
 * @since JDK 11.0.2
 */
public class YiBanAPI {

    /*易班评论文章POST请求目标URL*/
    private static String replyArticleURL = "http://www.yiban.cn/forum/reply/addAjax";

    /*易班获取文章列表信息URL*/
    private static String listArticleURL = "http://www.yiban.cn/forum/article/listAjax";

    private static String newArticleURL = "http://www.yiban.cn/forum/article/addAjax";

    private static String likeVoteURL = "http://www.yiban.cn/vote/vote/editLove";

    /**
     * 回复文章
     * 参数分别是文章ID和回复内容
     */
    static void replyArticle(UserInfo userInfo ,String articleID, String message) {
        final OkHttpClient client = new OkHttpClient();

        FormBody replyInfo = new FormBody.Builder()
                .add("channel_id", userInfo.getChannelID())
                .add("puid", userInfo.getPuid())
                .add("article_id", articleID)
                .add("content", message)
                .add("reply_id", "0")
                .add("syncFeed", "0")
                .add("isAnonymous", "0")
                .build();

        Request replyRequest = new Request.Builder()
                .url(replyArticleURL)
                .header("Cookie", userInfo.getUserCookie())
                .post(replyInfo)
                .build();

        Call replyCall = client.newCall(replyRequest);

        replyCall.enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                System.out.println("Error: Call Failure!");
            }

            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Reply Call success!\nHere is Response Body\n" + response.body().string());
            }
        });
    }

    /**
     * 获取文章列表，返回json
     * 下面为抓到的请求包
     * POST
     * channel_id:
     * puid:
     * page:
     * size: 10
     * orderby: updateTime
     * Sections_id: 0
     * need_notice: 0
     * group_id: 0
     * my: 0
     *
     * @return
     */
     String listArticle(UserInfo userInfo, int page) throws InterruptedException {
        final OkHttpClient client = new OkHttpClient();

        FormBody requestListInfo = new FormBody.Builder()
                .add("channel_id", userInfo.getChannelID())
                .add("puid", userInfo.getPuid())
                .add("page", Integer.toString(page))
                .add("size", "200")
                .add("orderby", "updateTime")
                .add("group_id",userInfo.getGroupID())
                .build();

        Request listArticleRequest = new Request.Builder()
                .url(listArticleURL)
                .header("Cookie", userInfo.getUserCookie())
                .post(requestListInfo)
                .build();

        Call listArticleCall = client.newCall(listArticleRequest);

        final String[] responseInfo = new String[1]; //回调需要final

        listArticleCall.enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                System.out.println("Network Error!");
            }

            public void onResponse(Call call, Response response) throws IOException {
                responseInfo[0] = response.body().string();
                //System.out.println(responseInfo[0]);
            }
        });
        Thread.sleep(5000); //Wait Response
        return responseInfo[0];
    }

    /**
     *
     * @param responseInfo
     * @return
     */
    String analyzeArticleList(String responseInfo) {
        //Gson articleList = new Gson().;
        return "";
    }

    /**
     * 发起新话题
     * POST
     * <p>
     * puid: 15270900
     * pubArea: 0 //班级码
     * title: 12312
     * content: <p>321321<br/></p>
     * isNotice: false
     * dom: .js-submit
     */
    static void newArticle(UserInfo userInfo) throws InterruptedException {
        final OkHttpClient client = new OkHttpClient();
        FormBody requestListInfo = new FormBody.Builder()
                .add("puid", userInfo.getPuid())
                .add("pubArea", "648688") //班级码
                .add("title", "世界线变动率" + Math.random() + "这一切都是命运石之门的选择") //标题
                .add("content", "世界线变动率" + Math.random() + "这一切都是命运石之门的选择") //正文内容
                .add("isNotice", "false") //这里应该是是否发布通知
                .add("dom", ".js-submit")
                .build();

        Request newArticleRequest = new Request.Builder()
                .url(newArticleURL)
                .header("Cookie", userInfo.getUserCookie())
                .post(requestListInfo)
                .build();

        Call newArticleCall = client.newCall(newArticleRequest);

        final String[] responseInfo = new String[1]; //回调需要final

        newArticleCall.enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                System.out.println("Network Error!");
            }

            public void onResponse(Call call, Response response) throws IOException {
                responseInfo[0] = response.body().string();
                System.out.println(responseInfo[0]);
            }
        });
        Thread.sleep(5000); //Wait Response
    }

    /**
     * VoteList
     */
    static Vector<String> listVote (UserInfo userInfo, int page) throws InterruptedException {
        String listVoteURL = "http://www.yiban.cn/newgroup/showMorePub/puid/" + userInfo.getPuid() + "/group_id/" + userInfo.getGroupID() + "/type/3/page/" + page;
        System.out.println(listVoteURL);
        final OkHttpClient client = new OkHttpClient();
        Request listArticleRequest = new Request.Builder()
                .url("http://www.yiban.cn/newgroup/showMorePub/puid/" + userInfo.getPuid() + "/group_id/" + userInfo.getGroupID() + "/type/3/page/" + page)
                .header("Cookie", userInfo.getUserCookie())
                .build();

        Call listVoteCall = client.newCall(listArticleRequest);

        final String[] responseInfo = new String[1]; //回调需要final


        listVoteCall.enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                System.out.println("获取投票页面信息失败");
            }

            public void onResponse(Call call, Response response) throws IOException {
                responseInfo[0] = response.body().string();
                System.out.println(responseInfo[0]);
            }
        });
        Thread.sleep(5000); //Wait Response
//        return responseInfo[0];

        String regex = "vote_id/[0-9]{0,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(responseInfo[0]);
        Vector<String> rexVoteIDs = new Vector<String>();
        while (matcher.find()) {
            rexVoteIDs.add(matcher.group(0));
        }
        for (String rexVoteID : rexVoteIDs) {
            System.out.println(rexVoteID);
        }

        Vector<String> voteIDs = new Vector<String>();
        for (String rexVoteID : rexVoteIDs) {
            if (voteIDs.isEmpty()){
                voteIDs.add(rexVoteID.substring(8));
            }else if (!rexVoteID.substring(8).equals(voteIDs.lastElement()))
                voteIDs.add(rexVoteID.substring(8));
        }

        System.out.println("\n");

        for (String voteID : voteIDs) {
            System.out.println(voteID);
        }
        return voteIDs;
    }

    /**
     * likeVote
     */
    static void likeVote (UserInfo userInfo,String vote_id) throws InterruptedException {
        final OkHttpClient client = new OkHttpClient();

        FormBody requestLikeVote = new FormBody.Builder()
                .add("puid",userInfo.getPuid())
                .add("group_id",userInfo.getGroupID())
                .add("vote_id",vote_id)
                .add("actor_id","")
                .add("flag","1")
                .build();

        Request likeVote = new Request.Builder()
                .url(likeVoteURL)
                .header("Cookie",userInfo.getUserCookie())
                .build();

        Call likeVoteCall = client.newCall(likeVote);

        likeVoteCall.enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                System.out.println("点赞失败");
            }

            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
        Thread.sleep(5000); //Wait Response
    }
}