package com.icbc.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/18 9:40
 * @description : cookie util
 */
public class CookieUtil implements Serializable {

    private static final long serialVersionUID = 6439042855543068410L;

    private static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);

    /**
     * 得到Cookie的值, 不编码
     *
     * @param request request
     * @param cookieName cookie name
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        return getCookieValue(request, cookieName, null);
    }

    /**
     * 得到Cookie的值,
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, String charset) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {
                    if (charset != null && charset.length() > 0) {
                        retValue = URLDecoder.decode(cookieList[i].getValue(), charset);
                    } else {
                        retValue = cookieList[i].getValue();
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("Cookie Decode Error.", e);
        }
        return retValue;
    }

    public static CookieUtil.CookieBuilder newBuilder(HttpServletResponse response) {
        return new CookieUtil.CookieBuilder(response);
    }

    public static class CookieBuilder {
        private HttpServletRequest request;
        private HttpServletResponse response;
        private Integer maxAge;
        private String charset;
        private boolean httpOnly = false;

        public CookieBuilder(HttpServletResponse response) {
            this.response = response;
        }

        public CookieUtil.CookieBuilder request(HttpServletRequest request) {
            this.request = request;
            return this;
        }

        public CookieUtil.CookieBuilder maxAge(int maxAge) {
            this.maxAge = maxAge;
            return this;
        }

        public CookieUtil.CookieBuilder charset(String charset) {
            this.charset = charset;
            return this;
        }

        public CookieUtil.CookieBuilder httpOnly() {
            this.httpOnly = true;
            return this;
        }

        public void build(String cookieName, String cookieValue) {
            try {
                if (StringUtils.isBlank(charset)) {
                    charset = "utf-8";
                }

                if (cookieValue == null) {
                    cookieValue = "";
                } else if (StringUtils.isNotBlank(charset)) {
                    cookieValue = URLEncoder.encode(cookieValue, charset);
                }
                Cookie cookie = new Cookie(cookieName, cookieValue);
                if (maxAge != null && maxAge > 0) {
                    cookie.setMaxAge(maxAge);
                }
                if (null != request)// 设置域名的cookie
                {
                    cookie.setDomain(getDomainName(request));
                }
                cookie.setPath("/");

                cookie.setHttpOnly(httpOnly);
                response.addCookie(cookie);
            } catch (Exception e) {
                logger.error("Cookie Encode Error.", e);
            }
        }

        /**
         * 得到cookie的域名
         */
        private String getDomainName(HttpServletRequest request) {
            String domainName = null;

            String serverName = request.getRequestURL().toString();
            if (serverName == null || serverName.equals("")) {
                domainName = "";
            } else {
                serverName = serverName.toLowerCase();
                serverName = serverName.substring(7);
                final int end = serverName.indexOf("/");
                serverName = serverName.substring(0, end);
                final String[] domains = serverName.split("\\.");
                int len = domains.length;
                if (len > 3) {
                    // www.xxx.com.cn
                    domainName = domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
                } else if (len <= 3 && len > 1) {
                    // xxx.com or xxx.cn
                    domainName = domains[len - 2] + "." + domains[len - 1];
                } else {
                    domainName = serverName;
                }
            }

            if (domainName != null && domainName.indexOf(":") > 0) {
                String[] ary = domainName.split("\\:");
                domainName = ary[0];
            }
            return domainName;
        }
    }
}
