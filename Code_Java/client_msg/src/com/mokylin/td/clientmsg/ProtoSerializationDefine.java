// Generated by the SocketProtoGenerationTool.  DO NOT EDIT!
package com.mokylin.td.clientmsg;

import com.mokylin.td.clientmsg.core.ICommunicationDataBase;
import com.mokylin.td.clientmsg.core.ICommunicationDataDefine;
import com.mokylin.td.clientmsg.proto.CS_CreateRole;
import com.mokylin.td.clientmsg.proto.CS_Login;
import com.mokylin.td.clientmsg.proto.SC_CreateRole;
import com.mokylin.td.clientmsg.proto.SC_CreateRoleFail;
import com.mokylin.td.clientmsg.proto.SC_LoginFail;
import com.mokylin.td.clientmsg.proto.SC_RoleList;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.NotSupportedException;

import io.netty.buffer.ByteBuf;

// Proto Seriialization Define IDs
public class ProtoSerializationDefine implements ICommunicationDataDefine {
    public static final int CS_LOGIN = 11;
    public static final int CS_CREATEROLE = 12;
    public static final int SC_LOGINFAIL = 13;
    public static final int SC_CREATEROLE = 14;
    public static final int SC_ROLELIST = 15;
    public static final int SC_CREATEROLEFAIL = 16;
    //Serialization ID Define in HashMap
    private Map<Integer, Class<?>> _serializationDefine;


    public ProtoSerializationDefine() {
        this._serializationDefine = new HashMap<>();


        this._serializationDefine.put(CS_LOGIN, CS_Login.class);


        this._serializationDefine.put(CS_CREATEROLE, CS_CreateRole.class);


        this._serializationDefine.put(SC_LOGINFAIL, SC_LoginFail.class);


        this._serializationDefine.put(SC_CREATEROLE, SC_CreateRole.class);


        this._serializationDefine.put(SC_ROLELIST, SC_RoleList.class);


        this._serializationDefine.put(SC_CREATEROLEFAIL, SC_CreateRoleFail.class);


    }

    public ICommunicationDataBase getCommunicationData(ByteBuf __bytes, int __messageID)
            throws InstantiationException, IllegalAccessException, UnsupportedEncodingException,
            NotSupportedException {
        if (_serializationDefine.containsKey(__messageID)) {
            Class<?> __type = _serializationDefine.get(__messageID);
            ICommunicationDataBase __data = (ICommunicationDataBase) __type.newInstance();
            __data.fromBytes(__bytes);
            return __data;
        }
        return null;
    }
}

		