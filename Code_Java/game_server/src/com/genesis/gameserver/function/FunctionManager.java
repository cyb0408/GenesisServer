package com.genesis.gameserver.function;

import com.genesis.gameserver.core.global.Globals;
import com.genesis.gameserver.human.Human;
import com.google.common.collect.Maps;

import com.genesis.common.core.GlobalData;
import com.genesis.common.function.FunctionType;
import com.genesis.common.function.template.FunctionTemplate;
import com.genesis.gamedb.orm.entity.FunctionEntity;
import com.genesis.gamedb.uuid.UUIDType;
import com.genesis.protobuf.FunctionMessage.GCFunctionOpen;
import com.genesis.protobuf.FunctionMessage.GCFunctionOpenList;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 功能管理器
 * @author yaguang.xiao
 *
 */
public class FunctionManager {

    private Human human;
    /** <功能类型, 功能对象> */
    private Map<FunctionType, Function> funcs = Maps.newHashMap();

    public FunctionManager(Human human) {
        this.human = human;
    }

    /**
     * 从数据库加载
     * @param funcEntities
     */
    public void loadFromEntities(List<FunctionEntity> funcEntities) {
        if (funcEntities == null || funcEntities.isEmpty()) {
            return;
        }

        for (FunctionEntity entity : funcEntities) {
            Function func = new Function(this.human);
            func.fromEntity(entity);
            this.funcs.put(func.getType(), func);
        }
    }

    /**
     * 创建角色的时候的初始化
     */
    public void initWhenNewCreateHuman() {
        Map<Integer, FunctionTemplate> tmpls =
                GlobalData.getTemplateService().getAll(FunctionTemplate.class);
        Timestamp openTime = new Timestamp(Globals.getTimeService().now());
        for (FunctionTemplate tmpl : tmpls.values()) {
            if (!tmpl.isDefaultOpen()) {
                continue;
            }

            long funcUUID =
                    this.human.getServerGlobals().getUUIDGenerator().getNextUUID(UUIDType.Function);
            Function func = new Function(this.human, funcUUID, tmpl.getFuncType(), openTime);
            func.setModified();
            this.funcs.put(func.getType(), func);
        }
    }

    /**
     * 开启功能
     * @param funcType
     */
    public void openFunc(FunctionType funcType) {
        if (funcType == null) {
            return;
        }

        if (this.funcs.containsKey(funcType)) {
            return;
        }

        Timestamp openTime = new Timestamp(Globals.getTimeService().now());
        long funcUUID =
                this.human.getServerGlobals().getUUIDGenerator().getNextUUID(UUIDType.Function);

        Function function = new Function(this.human, funcUUID, funcType, openTime);
        function.setModified();

        this.funcs.put(funcType, function);

        GCFunctionOpen.Builder msgB = GCFunctionOpen.newBuilder();
        msgB.setFuncType(funcType.getIndex());
        this.human.sendMessage(msgB);
    }

    /**
     * 指定功能是否开启
     * @param funcType
     * @return
     */
    public boolean isOpenFunction(FunctionType funcType) {
        return this.funcs.containsKey(funcType);
    }

    /**
     * 登录之后给客户端发送消息
     */
    public void notifyOnLogin() {
        GCFunctionOpenList.Builder msgB = GCFunctionOpenList.newBuilder();
        for (FunctionType funcType : this.funcs.keySet()) {
            msgB.addFuncTypes(funcType.getIndex());
        }

        this.human.sendMessage(msgB);
    }
}
