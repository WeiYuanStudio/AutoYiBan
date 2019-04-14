import okhttp3.*;

import java.io.IOException;

/**
 * @author WeiYuan
 * @version 0.1
 * @since JDK 11.0.2
 */
public class YiYanAPI {
    private static String YiYanAPIURL = "https://v1.hitokoto.cn/?c=f&encode=text";

    static String getYiyan() throws InterruptedException {
        final OkHttpClient client = new OkHttpClient();

        Request getRequest = new Request
                .Builder()
                .url(YiYanAPIURL)
                .build();

        Call getRequestCall = client.newCall(getRequest);

        final String[] responseInfo = new String[1]; //回调需要final


        getRequestCall.enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                System.out.println("Error: Call Failure!");
            }

            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Reply Call success!\nHere is Response Body\n" + response.body().string());
                responseInfo[0] = response.body().string();
            }
        });

        Thread.sleep(5000); //Wait Response
        return responseInfo[0];
    }
}