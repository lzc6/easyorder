package zqeasyorder.util;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



public class HttpClient {

    private CloseableHttpClient client;

    private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass());

    public String sendXMLDataByPost(String url, String xmlData)
            throws IOException{

        Integer statusCode = -1;
        if (client == null) {
            // Create HttpClient Object
            client = new DefaultHttpClient();
        }

        try{
            HttpPost post = new HttpPost(url);
            StringEntity entity = new StringEntity(xmlData, "utf-8");
            post.setEntity(entity);
            post.setHeader("Content-Type", "application/xml");
            HttpResponse response = client.execute(post);
            statusCode = response.getStatusLine().getStatusCode();
            //只有请求成功200了，才做处理
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null){
                String reposemessage = EntityUtils.toString(httpEntity, "UTF-8") ;
//                log.info("Response:+++"+reposemessage);
                return reposemessage;
            }
        }catch (Exception e){
            return null;
        }

        return null;
    }
}

 