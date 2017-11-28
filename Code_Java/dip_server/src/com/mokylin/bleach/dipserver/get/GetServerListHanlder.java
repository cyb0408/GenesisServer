package com.mokylin.bleach.dipserver.get;

import com.mokylin.bleach.dipserver.core.handler.HttpHandler;
import com.mokylin.bleach.dipserver.core.handler.IRequestHandler;

import org.restlet.Request;
import org.restlet.Response;

/**
 * 获取服务器列表
 * @author yaguang.xiao
 *
 */
@HttpHandler(path = "/serversByJson")
public class GetServerListHanlder implements IRequestHandler {

    @Override
    public void handle(Request request, Response response) {
        //通过Akka查询信息
    }

}
