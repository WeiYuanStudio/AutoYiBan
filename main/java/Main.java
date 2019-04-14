import com.google.gson.*;

import java.util.List;

/**
 * @author WeiYuan
 * @version 0.1
 * @since JDK 11.0.2
 */
public class Main extends Thread {
    public static void main(String[] args) throws InterruptedException {

        List<String> allCookies = GetCookies.getFileContent("./CookiesDataBase"); //填写你放置cookies文件的路径，cookies文件一行一个User Token
        for (String i : allCookies){
            System.out.println(i);
        }
        System.out.println("Have " + allCookies.size() + " Info In DataBase");
        UserInfo[] allUserInfo = new UserInfo[allCookies.size()];
        for (int i = 0; i < allCookies.size(); i++) {
            allUserInfo[i] = new UserInfo(
                    allCookies.get(i)
                    /*以下是班级信息，请使用浏览器F12功能抓包，或者通过你的易班班级主页URL中的信息查看,这里需要修改成您自己的信息*/
                    ,"433068" //channelID
                    ,"8323797" //puid
                    ,"648688" //groupID
            );
        }

        System.out.println(new YiBanAPI().listArticle(allUserInfo[0], 1));
        int i = 0;
        while (true) {
            System.gc();
            ResponseArticleInfoJson articleInfoJson = new Gson().fromJson((String) new YiBanAPI().listArticle(allUserInfo[0], 1), ResponseArticleInfoJson.class);
            System.out.println(articleInfoJson.data);
            for (int j = 0; j < 200; j++) {
                System.out.println(articleInfoJson.data.list.get(j).id);
            } //打印爬取到的前200条话题的articleID
//
            for (int j = 0; j < 200; j++) {
                System.out.println("正在回复第" + (j + 1) + "条");
//                String yiyan = "世界线变动率" + Math.random() + "这一切都是命运石之门的选择"; //计划加入一言API,获取随机ACG语录
                for (UserInfo u :allUserInfo) {
                    YiBanAPI.replyArticle(u, articleInfoJson.data.list.get(j).id, "世界线变动率" + Math.random() + "这一切都是命运石之门的选择");
                } //所有已经添加的用户回复该条评论
                int newArticleUserIndex = (int) (Math.random()*allUserInfo.length); //随机抽取一名用户去创建新文章
                System.out.println("New Article By " + newArticleUserIndex);
                YiBanAPI.newArticle(allUserInfo[newArticleUserIndex]);
                Thread.sleep(80000); //技能冷却时间,建议不要设置太短，以免触发验证码
            }
        }
    }

/*下面是投票部分的加入计划，还未完善*/
//    public static void main(String[] args) throws IOException, InterruptedException {
//        List<String> allCookies = GetCookies.getFileContent("/disk2/playground/CookiesDataBase");
//        for (String i : allCookies) {
//            System.out.println(i);
//        }
//        System.out.println("Have " + allCookies.size() + " Info In DataBase");
//        UserInfo[] allUserInfo = new UserInfo[allCookies.size()];
//        for (int i = 0; i < allCookies.size(); i++) {
//            allUserInfo[i] = new UserInfo(
//                    allCookies.get(i)
//                    , "433068"
//                    , "8323797"
//                    , "648688"
//            );
//        }
////        String regex = "vote_id/[0-9]{0,}";
////        Pattern pattern = Pattern.compile(regex);
////        Matcher matcher = pattern.matcher(votePageHTML);
////        Vector<String> rexVoteIDs = new Vector<String>();
////        while (matcher.find()) {
////            rexVoteIDs.add(matcher.group(0));
////        }
////        for (String rexVoteID : rexVoteIDs) {
////            System.out.println(rexVoteID);
////        }
////
////        Vector<String> voteIDs = new Vector<String>();
////        for (String rexVoteID : rexVoteIDs) {
////            if (voteIDs.isEmpty()){
////                voteIDs.add(rexVoteID.substring(8));
////            }else if (!rexVoteID.substring(8).equals(voteIDs.lastElement()))
////                voteIDs.add(rexVoteID.substring(8));
////        }
////
////        System.out.println("\n");
////
////        for (String voteID : voteIDs) {
////            System.out.println(voteID);
////        }
//        Vector<String> listVote = new YiBanAPI().listVote(allUserInfo[0], 1);
//        for (UserInfo i : allUserInfo) {
//            for (String j : listVote) {
//                YiBanAPI.likeVote(i, j);
//            }
//        }
//    }
}