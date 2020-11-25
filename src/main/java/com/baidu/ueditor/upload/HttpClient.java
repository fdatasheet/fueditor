package com.baidu.ueditor.upload;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClient {

    final static String UA = "Fds fueditor 2.0.1";

    public static HttpEntity doPostUploadFileToHttpd(String url, String param, File f) throws IOException {
        HttpEntity _entity = null;

        try (CloseableHttpClient httpclient = HttpClients.createDefault();) {
            HttpPost post = new HttpPost(url);
            post.addHeader("User-Agent", UA);

            FileBody _fbody = new FileBody(f);
            HttpEntity _reqEntity = MultipartEntityBuilder.create().addPart(param, _fbody).build();

            post.setEntity(_reqEntity);
            System.out.println("url = " + url);
            System.out.println(EntityUtils.toString(_reqEntity));
            System.out.println("=========================================");

            try (CloseableHttpResponse resp = httpclient.execute(post);) {
                HttpEntity _resEntity = resp.getEntity();
                if (_resEntity != null) {
                    System.out.println("Response content length: " + _resEntity.getContentLength() + ", "
                            + EntityUtils.toString(_resEntity, "utf-8"));
                }
                EntityUtils.consume(_resEntity);

                _entity = _resEntity;
            }
        }

        return _entity;
    }

    public static HttpEntity doGetSaveFileToHttpPath(String url, String tmpfile, String fname) throws IOException {
        HttpEntity _entity = null;

        try (CloseableHttpClient httpclient = HttpClients.createDefault();) {
            HttpGet _get = new HttpGet(url);
            _get.addHeader("User-Agent", UA);

            HttpEntity _reqEntity = EntityBuilder.create().setParameters(new BasicNameValuePair("", ""))
                    .setParameters(new BasicNameValuePair("", "")).build();

            System.out.println("url = " + url);
            System.out.println(EntityUtils.toString(_reqEntity));
            System.out.println("=========================================");

            try (CloseableHttpResponse resp = httpclient.execute(_get);) {
                HttpEntity _resEntity = resp.getEntity();
                if (_resEntity != null) {
                    System.out.println("Response content length: " + _resEntity.getContentLength() + ", "
                            + EntityUtils.toString(_resEntity, "utf-8"));
                }
                EntityUtils.consume(_resEntity);

                _entity = _resEntity;
            }
        }

        return _entity;
    }

}
