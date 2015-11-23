package com.memory.base.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.memory.base.constant.ResponseCode;
import com.memory.base.model.BaseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * base controller
 *
 * @author memoryaxis@gmail.com
 * @date Oct 27, 2015
 */
public class BaseController {

    protected static final Logger log = LoggerFactory.getLogger(BaseController.class);

    protected static final ObjectMapper jsonMapper = new ObjectMapper();

    protected static final ResponseCode SUCCESS = ResponseCode.SUCCESS;
    protected static final ResponseCode ERROR = ResponseCode.ERROR;

    protected static final String JSON_WITH_UTF8 = "application/json;charset=utf-8";
    protected static final String SESSION_CAPTCHA = "captcha_session";
    public static final String SESSION_USER = "user_session";

    protected static final String PAGE_SIZE = "10";
    protected static final String PAGE_NO = "1";

    protected String responseJson(Object obj) {
        return responseJson(SUCCESS, obj);
    }

    protected String responseJson(ResponseCode code) {
        return jsonMapper.createObjectNode().put("code", code.getCode())
                .put("message", code.getMsg()).toString();
    }

    protected String responseJson(ResponseCode code, Object obj) {
        if (obj == null) {
            return responseJson(ERROR);
        }

        if (obj instanceof ArrayNode) {
            return jsonMapper
                    .createObjectNode()
                    .put("code", SUCCESS.getCode())
                    .put("message", SUCCESS.getMsg())
                    .set("data", (ArrayNode) obj)
                    .toString();
        }

        return jsonMapper
                .createObjectNode()
                .put("code", SUCCESS.getCode())
                .put("message", SUCCESS.getMsg())
                .set("data", jsonMapper.createObjectNode()
                        .set("obj", jsonMapper.valueToTree(obj)))
                .toString();
    }

    protected String responseJson(List<? extends BaseModel> dataList) {
        if (dataList == null || dataList.size() == 0) {
            return responseJson(ERROR);
        }

        return jsonMapper
                .createObjectNode()
                .put("code", SUCCESS.getCode())
                .put("message", SUCCESS.getMsg())
                .set("data",
                        jsonMapper.createObjectNode().set("list", jsonMapper.valueToTree(dataList)))
                .toString();
    }

    protected String responseJson(List<? extends BaseModel> dataList, int count, int currentPage) {
        if (dataList == null || dataList.size() == 0) {
            return responseJson(ERROR);
        }

        return jsonMapper
                .createObjectNode()
                .put("code", SUCCESS.getCode())
                .put("message", SUCCESS.getMsg())
                .set("data",
                        // TODO
                        jsonMapper.createObjectNode().put("total", count)
                                .put("currentPage", currentPage)
                                .set("list", jsonMapper.valueToTree(dataList))).toString();
    }

    @Deprecated
    protected String responseJson(ResponseCode code, Map<String, Object> data) {
        ObjectNode json = jsonMapper.createObjectNode();

        Map<String, JsonNode> dataJson = new HashMap<String, JsonNode>();
        Set<Entry<String, Object>> dataSet = data.entrySet();
        Iterator<Entry<String, Object>> iterator = dataSet.iterator();
        while (iterator.hasNext()) {
            Entry<String, Object> entry = iterator.next();
            dataJson.put(entry.getKey(), jsonMapper.valueToTree(entry.getValue()));
        }

        json.put("code", code.getCode())
                .put("message", code.getMsg())
                .putObject("data").setAll(dataJson);

        return json.toString();
    }

    @ResponseBody
    @ExceptionHandler
    protected String exp(HttpServletRequest request, Exception ex) {
        request.setAttribute("ex", ex);
        return responseJson(ResponseCode.ERROR);
    }
}
