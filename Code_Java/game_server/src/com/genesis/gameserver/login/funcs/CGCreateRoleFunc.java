package com.genesis.gameserver.login.funcs;
//
//import HumanInfo;
//import UUIDType;
//import ServerGlobals;
//import HumanInfoCache;
//import AbstractClientMsgFunc;
//import CreatePlayerActorHelper;
//import Player;
//import PlayerManagerArgs;
//import com.genesis.protobuf.PlayerMessage.CGCreateRole;
//import com.genesis.protobuf.PlayerMessage.CreateRoleFailReason;
//import com.genesis.protobuf.PlayerMessage.GCCreateRoleFail;
//import com.genesis.protobuf.PlayerMessage.GCCreateRoleFail.Builder;
//
//import java.util.List;
//
///**
// * 处理客户端创建角色消息的函数对象。<p>
// *
// * 该函数对象在PlayerManagerActor中执行。
// *
// * @author pangchong
// *
// */
//public class CGCreateRoleFunc
//        extends AbstractClientMsgFunc<CGCreateRole, ServerGlobals, PlayerManagerArgs> {
//
//    @Override
//    public void handle(Player player, CGCreateRole msg, ServerGlobals sGlobals,
//            PlayerManagerArgs playerManagerArgs) {
//        HumanInfoCache humanInfoCache = playerManagerArgs.humanInfoCache;
//        List<HumanInfo> humans =
//                humanInfoCache.getHumanInfoList(player.getChannel(), player.getAccountId());
//
//        if (humans != null && humans.size() > 0) {
//            //已经有角色了
//            Builder builder = GCCreateRoleFail.newBuilder();
//            builder.setFailReason(CreateRoleFailReason.ALREADY_HAVE_ROLE);
//            GCCreateRoleFail msgToSend = builder.build();
//            player.sendMessage(msgToSend);
//            return;
//        }
//
//        //2.0检查名字
//        final String name = msg.getName();
//        //2.1检查是否重名
//        if (humanInfoCache.isExist(name)) {
//            Builder builder = GCCreateRoleFail.newBuilder();
//            builder.setFailReason(CreateRoleFailReason.NAME_EXISTS);
//            GCCreateRoleFail msgToSend = builder.build();
//            player.sendMessage(msgToSend);
//            return;
//        }
//
//        //2.2检查名字是否有非法字符 TODO
//        HumanInfo humanInfo = new HumanInfo();
//        humanInfo.setAccountId(player.getAccountId());
//        humanInfo.setChannel(player.getChannel());
//        humanInfo.setName(name);
//        humanInfo.setOriginalServerId(sGlobals.getServerId());
//        final long id = sGlobals.getUUIDGenerator().getNextUUID(UUIDType.Human);
//        humanInfo.setId(id);
//        humanInfoCache.insert(humanInfo, sGlobals);
//
//        //create human
//        CreatePlayerActorHelper.createPlayerActor(player, humanInfo, sGlobals, playerManagerArgs);
//    }
//
//}
