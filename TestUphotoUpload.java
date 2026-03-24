import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestUphotoUpload {
    public static void main(String[] args) {
        try {
            // 创建URL对象
            URL url = new URL("http://localhost:8080/api/user/10007/uphoto/upload");
            
            // 打开连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            // 设置请求方法
            conn.setRequestMethod("POST");
            
            // 设置请求头
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            
            // 构建请求体
            String requestBody = "{\"uid\": 10007, \"uphoto_title\": \"测试作品\", \"uphoto_description\": \"测试描述\", \"pointset\": -10}";
            
            // 写入请求体
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            
            // 获取响应
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            
            // 读取响应内容
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Response: " + response.toString());
            }
            
            // 关闭连接
            conn.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}