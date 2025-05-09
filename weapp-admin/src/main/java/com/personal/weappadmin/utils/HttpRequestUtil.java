package com.personal.weappadmin.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpRequestUtil {

    //    get请求带参数
    public static String sentGet() throws Exception {
//        创建一个常见的HttpClient对象,使用的是HttpClients工具类创建
        CloseableHttpClient client = HttpClients.createDefault();
//        创建一个封装uri的对象
        String url="https://api.mairui.club/hszb/fsjy/000001/dn/6ef178140a410b770";
        URIBuilder uriBuilder=new URIBuilder(url);
//        设置请求参数
//        创建一个gat请求对象
        HttpGet httpGet=new HttpGet(uriBuilder.build());
//        发送请求，返回响应对象
        CloseableHttpResponse execute = client.execute(httpGet);
//        获取响应状态码
        int code = execute.getStatusLine().getStatusCode();
//        获取响应内容
//        通过getEntity()获取
        HttpEntity entity = execute.getEntity();
//        然后可以使用EntityUtils工具类来获取,它有两个参数，第二个是编码格式
        String content = EntityUtils.toString(entity, "utf-8");
        System.out.println("状态码为："+code);
        System.out.println("响应的内容为："+content);
//        关闭连接
        client.close();
        return content;
    }
}

