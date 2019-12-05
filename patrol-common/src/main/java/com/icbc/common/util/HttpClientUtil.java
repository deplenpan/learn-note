package com.icbc.common.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/18 9:53
 * @description : Http Client Util
 */
public class HttpClientUtil implements Serializable {

    private static final long serialVersionUID = -428957050333852614L;

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * 带参数的get请求
     *
     * @param url    request url
     * @param params request parameter
     * @return String
     */
    private static String doGet(String url, Map<String, String> params) {
        // create httpClient object
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String resultString = "";

        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if (params != null) {
                for (String key : params.keySet()) {
                    builder.addParameter(key, params.get(key));
                }
            }
            URI uri = builder.build();
            // create http get request
            HttpGet httpGet = new HttpGet(uri);

            // execute request
            response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }

        } catch (Exception e) {
            logger.error("Get Request exception occurs. Exception message: {}" + e.getMessage());
        } finally {

            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                logger.error("Stream close exception occurs. Exception message: {}" + e.getMessage());
            }
        }
        return resultString;
    }

    /**
     * get request without parameter
     *
     * @param url request url
     * @return String
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * post request with parameter
     *
     * @param url    request url
     * @param params request param
     * @return String
     */
    public static String doPost(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // create post request
            HttpPost httpPost = new HttpPost(url);
            // create request content
            if (params != null) {
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                for (String key : params.keySet()) {
                    list.add(new BasicNameValuePair(key, params.get(key)));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list);
                httpPost.setEntity(entity);
            }
            // execute request
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            logger.error("Post Request exception occurs. Exception message: {}" + e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error("Stream close exception occurs. Exception message: {}" + e.getMessage());
            }
        }
        return resultString;
    }

    /**
     * post request without parameter
     *
     * @param url request rul
     * @return String
     */
    public static String doPost(String url) {
        return doPost(url, null);
    }

    /**
     * post request with json type data
     *
     * @param url  request url
     * @param json json type data
     * @return String
     */
    public static String doPostJson(String url, String json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // create post request
            HttpPost httpPost = new HttpPost(url);
            // create request content
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // execute request
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(httpPost.getEntity(), "UTF-8");
        } catch (Exception e) {
            logger.error("Post Request exception occurs. Exception message: {}" + e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error("Stream close exception occurs. Exception message: {}" + e.getMessage());
            }
        }
        return resultString;
    }

}
