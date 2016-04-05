package com.wa.net.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wa.net.vo.PageResultVo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 16-3-31.
 **/
public class JsonHelp {

    public final  static  String CURRENT_PAGE="currentPage";
    public final  static   String CURRENT_SIZE="currentSize";
    public static final String PAGE = "page";
    public static final String ROWS = "rows";

    public static  String getPageJson( PageResultVo p ,Integer page,Integer currentsize){
        Integer totalPage = (int)Math.ceil(Float.parseFloat(String.valueOf(p.getTotal())) / currentsize);
        StringBuffer sb =new StringBuffer();
        sb.append("{"+escapeDQ("page")+":"+page+",");
        sb.append(escapeDQ("records")+":"+p.getTotal()+",");
        sb.append(escapeDQ("total")+":"+totalPage+",");
        sb.append(escapeDQ("rows")+":"+ JSON.toJSONString(p.getResult(), SerializerFeature.WriteDateUseDateFormat)+"}");
        return sb.toString();
    }

    public static Map<String,Object> parserPageParms(HttpServletRequest request){
        Map<String,Object> pagemap=new HashMap<>();
        String page = request.getParameter(PAGE);
        String pageSize = request.getParameter(ROWS);
        pagemap.put(CURRENT_PAGE,"".equals(page) || page == null ? 1 : Integer.parseInt(page));
        pagemap.put(CURRENT_SIZE,"".equals(pageSize) || pageSize == null? 10 : Integer.parseInt(pageSize));
        return pagemap;
    }

    static  String escapeDQ(String str){
        return  '"'+str+'"';
    }

}
