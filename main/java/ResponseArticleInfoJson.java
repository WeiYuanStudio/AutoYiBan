import java.util.List;

/**
 * Ajax响应返回话题列表类型，用于给Gson构建对象
 *
 * @author WeiYuan
 * @version 0.1
 * @since JDK 11.0.2
 */
public class ResponseArticleInfoJson {
    int code;
    String message;
    jData data;

    static class jData {
        List<jList> list;
        String time;
        jCount count;
        String is_end;

        static class jList{
            String id;
            String Channel_id;
            String title;
            jAuthor author;

            static class jAuthor {
                String name;
            }
        }
        static class jCount{}
    }
}
