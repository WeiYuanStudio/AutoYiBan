import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WeiYuan
 * @version 0.1
 * @since JDK 11.0.2
 */
public class GetCookies {
    public static List<String> getFileContent(String path) {
        List<String> strList = new ArrayList<String>();
        File file = new File(path);
        InputStreamReader read = null;
        BufferedReader reader = null;
        try {
            read = new InputStreamReader(new FileInputStream(file),"utf-8");
            reader = new BufferedReader(read);
            String line;
            while ((line = reader.readLine()) != null) {
                strList.add(line);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        return strList;
    }
}
