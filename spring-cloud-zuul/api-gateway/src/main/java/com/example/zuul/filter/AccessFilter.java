package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AccessFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext() ;
        HttpServletRequest request = ctx.getRequest();
        return request.getRequestURL().toString().contains("/notes");
    }

    @Override
    public Object run(){
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String string = request.getParameter("notes");
        if(string != null && string.contains("中国")){
            HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(response.getOutputStream());
                outputStreamWriter.write("forbidden words!");
                outputStreamWriter.flush();
                outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return string;
    }
}
