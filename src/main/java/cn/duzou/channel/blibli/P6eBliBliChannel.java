package cn.duzou.channel.blibli;


import cn.duzou.channel.P6eChannelAbstract;
import cn.duzou.channel.P6eChannelCallback;
import cn.duzou.channel.P6eChannelTimeCallback;
import cn.duzou.common.P6eToolCommon;
import cn.duzou.netty.client.P6eWebSocketClient;
import cn.duzou.netty.client.actuator.P6eActuatorDefault;
import cn.duzou.netty.client.config.P6eConfig;
import lombok.extern.slf4j.Slf4j;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * BliBli 直播 https://live.bilibili.com/
 * BliBli 直播房间消息连接器
 * @version 1.0
 */
@Slf4j
public class P6eBliBliChannel extends P6eChannelAbstract {



    /** BliBli 房间 ID */
    private String rid;

    /** Web Socket 会话客户端 ID */
    private String clientId;

    /** BliBli 回调函数 */
    private P6eChannelCallback.BliBli callback;

    /** BliBli 配置信息 */
    private P6eBliBliChannelInventory inventory;

    /**
     * 创建 P6eBliBliChannel 实例对象
     * @param rid 房间的 rid
     * @param callback 接收消息后的回调函数
     * @return P6eBliBliChannel 实例化的对象
     */
    public static P6eBliBliChannel create(String rid, P6eChannelCallback.BliBli callback) {
        if (callback == null) throw new RuntimeException("P6eChannelCallback.BliBli null");
        return new P6eBliBliChannel(rid, callback);
    }

    /**
     * 构造方法
     * @param rid 房间 ID
     * @param callback 接收消息后的回调函数
     */
    private P6eBliBliChannel(String rid, P6eChannelCallback.BliBli callback) {
        this.rid = rid;
        this.callback = callback;
        this.inventory = new P6eBliBliChannelInventory(rid);
        System.out.println(clientApplication);
        this.connect();
    }

    /**
     * 连接斗鱼房间
     */
    protected void connect() {
        try {
            // 修改状态为连接中
            this.setStatus(CONNECT_STATUS);
            // Web Socket 连接房间信息系统
            System.out.println("1" + clientApplication.getP6eModelCache().get());
            clientApplication.connect(
                    new P6eConfig(inventory.getWebSocketWssUrl(), new P6eActuatorDefault()));
            clientApplication.connect(
                    new P6eConfig(inventory.getWebSocketWssUrl(), new P6eActuatorDefault() {

                        /** 时间回调触发器的配置信息 */
                        private P6eChannelTimeCallback.Config config;

                        @Override
                        public void onOpen(P6eWebSocketClient webSocket) {
                            System.out.println(webSocket);
                            clientId = webSocket.getId();

                            log.debug("BliBli onOpenAsync [ CLIENT: " + webSocket.getId() + ", RID: " + rid + " ]");

                            // 发送登录的消息
                            webSocket.sendBinaryMessage(messageEncoder(inventory.getLoginInfo(), inventory.getLoginType()));

                            // 创建定时器
                            config = new P6eChannelTimeCallback.Config(30, true, true, config ->
                                    webSocket.sendBinaryMessage(messageEncoder(inventory.getPantInfo(), inventory.getPantType())));

                            // 启动定时器
                            P6eChannelTimeCallback.addConfig(config);
                        }

                        @Override
                        public void onClose(P6eWebSocketClient webSocket) {
                            log.debug("BliBli onCloseAsync [ CLIENT: " + webSocket.getId() + ", RID: " + rid + " ]");
                            if (this.config != null) P6eChannelTimeCallback.removeConfig(this.config);
                            if (!isClose()) {
                                log.error("BliBli onCloseAsync [ CLIENT: " + webSocket.getId() + ", RID: " + rid + " ]" +
                                        " ==> retry " + incrementRetry() + ", retryCount" + getRetryCount());
                                reconnect(); // 重新连接
                            }
                        }

                        @Override
                        public void onError(P6eWebSocketClient webSocket, Throwable throwable) {
                            log.error("BliBli onErrorAsync [ CLIENT: "
                                    + webSocket.getId() + ", RID: " + rid + " ] ==> " + throwable.getMessage());
                        }

                        @Override
                        public void onMessageText(P6eWebSocketClient webSocket, String message) {
                            log.debug("BliBli onMessageTextAsync [ CLIENT: "
                                    + webSocket.getId() + ", RID: " + rid + " ] ==> " + message);
                        }

                        @Override
                        public void onMessageBinary(P6eWebSocketClient webSocket, byte[] message) {
                            List<Source> sources = messageDecoder(message);
                            List<P6eBliBliChannelMessage> messages = new ArrayList<>();
                            for (Source source : sources) {
                                // 重置错误的连接次数
                                if (source.type == inventory.getLoginResultType()) resetReconnect();
                                messages.add(P6eBliBliChannelMessage.build(source.bytes, source.type, source.content));
                            }
                            if (callback != null) callback.execute(messages);
                        }

                        @Override
                        public void onMessagePong(P6eWebSocketClient webSocket, byte[] message) {
                            log.debug("BliBli onMessagePongAsync [ CLIENT: "
                                    + webSocket.getId() + ", RID: " + rid + " ] ==> " + new String(message));
                        }

                        @Override
                        public void onMessagePing(P6eWebSocketClient webSocket, byte[] message) {
                            log.debug("BliBli onMessagePingAsync [ CLIENT: "
                                    + webSocket.getId() + ", RID: " + rid + " ] ==> " + new String(message));
                        }

                        @Override
                        public void onMessageContinuation(P6eWebSocketClient webSocket, byte[] message) {
                            log.debug("BliBli onMessageContinuationAsync [ CLIENT: "
                                    + webSocket.getId() + ", RID: " + rid + " ] ==> " + new String(message));
                        }
                    }));
            System.out.println("2" + clientApplication.getP6eModelCache().get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭 BliBli 的房间信息服务器
     */
    @Override
    public void close() {
        if (clientApplication != null) {
            this.removeCache();
            this.setStatus(CLOSE_STATUS);
            clientApplication.close(clientId);
        } else throw new RuntimeException("BliBli client application null");
    }

    @Override
    protected void dump() {
        if (clientApplication != null) {
            this.setStatus(CLOSE_STATUS);
            clientApplication.close(clientId);
        }
    }

    /**
     * BliBli 发送消息
     * BliBli 消息 = 消息头部
     *              (
     *                  消息总长度 [ 大端模式转换 (4) ]
     *                  消息头部总长度 [ 数据包头部长度，固定为 16 (2) ]
     *                  数据包协议版本 [
     *                      0	数据包有效负载为未压缩的JSON格式数据
     *                      1	客户端心跳包，或服务器心跳回应（带有人气值）
     *                      2	数据包有效负载为通过zlib压缩后的JSON格式数据
     *                      (2)
     *                  ]
     *                  数据包类型 [
     *                      2	客户端	心跳	不发送心跳包，50-60秒后服务器会强制断开连接
     *                      3	服务器	心跳回应	有效负载为直播间人气值
     *                      5	服务器	通知	有效负载为礼物、弹幕、公告等
     *                      7	客户端	认证（加入房间）	客户端成功建立连接后发送的第一个数据包
     *                      8	服务器	认证成功回应	服务器接受认证包后回应的第一个数据包
     *                      (4)
     *                  ]
     *                  备用字段 [ 固定为 1 (4) ]
     *              )
     *         + 消息内容
     * @param message 发送的消息内容
     * @param type 发送消息的类型
     */
    private byte[] messageEncoder(String message, int type) {
        int len = 16 + message.length();
        byte[] bytes = new byte[len];
        P6eToolCommon.arrayJoinByte(
                bytes,
                P6eToolCommon.intToBytesBig(len),
                new byte[] { 0, 16, 0, 1 },
                P6eToolCommon.intToBytesBig(type),
                P6eToolCommon.intToBytesBig(1),
                message.getBytes(StandardCharsets.UTF_8)
        );
        return bytes;
    }

    /**
     * BliBli 接收消息
     * BliBli 消息 = 消息头部
     *              (
     *                  消息总长度 [ 大端模式转换 (4) ]
     *                  消息头部总长度 [ 数据包头部长度，固定为 16 (2) ]
     *                  数据包协议版本 [
     *                      0	数据包有效负载为未压缩的JSON格式数据
     *                      1	客户端心跳包，或服务器心跳回应（带有人气值）
     *                      2	数据包有效负载为通过zlib压缩后的JSON格式数据
     *                      (2)
     *                  ]
     *                  数据包类型 [
     *                      2	客户端	心跳	不发送心跳包，50-60秒后服务器会强制断开连接
     *                      3	服务器	心跳回应	有效负载为直播间人气值
     *                      5	服务器	通知	有效负载为礼物、弹幕、公告等
     *                      7	客户端	认证（加入房间）	客户端成功建立连接后发送的第一个数据包
     *                      8	服务器	认证成功回应	服务器接受认证包后回应的第一个数据包
     *                      (4)
     *                  ]
     *                  备用字段 [ 固定为 0 (4) ]
     *              )
     *         + 消息内容
     * @param data 接收的消息的内容
     */
    private List<Source> messageDecoder(byte[] data) {
        int index = 0;
        List<Source> sources = new ArrayList<>();
        while (true) {
            if (data.length - index >= 16) {

                // 内容1 部分
                byte[] len1Bytes = new byte[]{data[index++], data[index++], data[index++], data[index++]};
                int len1 = P6eToolCommon.bytesToIntBig(len1Bytes);

                // 内容2 部分
                byte[] len2Bytes = new byte[]{data[index++], data[index++]};
                int len2 = len2Bytes[0] + len2Bytes[1];

                // 内容3 部分
                byte[] agreementBytes = new byte[]{data[index++], data[index++]};
                int agreement = agreementBytes[0] + agreementBytes[1];

                // 内容4 部分
                byte[] typeBytes = new byte[]{data[index++], data[index++], data[index++], data[index++]};
                int type = P6eToolCommon.bytesToIntBig(typeBytes);

                // 内容5 部分
                byte[] spareBytes = new byte[]{data[index++], data[index++], data[index++], data[index++]};
                int spare = P6eToolCommon.bytesToIntBig(spareBytes);

                // 验证是否符合接收消息的格式
                if (len1 > 16 && len2 == 16 && spare == 0) {
                    byte[] contentBytes = new byte[len1];
                    P6eToolCommon.arrayJoinByte(contentBytes, len1Bytes, len2Bytes, agreementBytes, typeBytes, spareBytes);
                    byte[] messageByte = new byte[len1 - 16];
                    for (int _index = index; index < _index + len1 - 16; index++) {
                        messageByte[index - _index] = data[index];
                    }
                    P6eToolCommon.arrayJoinByte(contentBytes, messageByte);
                    if (agreement == 2) {
                        // 发送协议为 2 的类型的数据的时候，可能是多条和并推送过来的
                        // 需要拆开为一条条的数据
                        int z = 0;
                        byte[] zBytes = P6eToolCommon.decompressZlib(messageByte);
                        while (zBytes.length > z) {
                            int zLen = P6eToolCommon.bytesToIntBig(new byte[] { zBytes[z], zBytes[z + 1], zBytes[z + 2], zBytes[z + 3] });
                            byte[] itemBytes = new byte[zLen];
                            for (int i = z; z < i + zLen; z++) itemBytes[z - i] = zBytes[z];
                            sources.add(new Source(itemBytes, type, new String(itemBytes, StandardCharsets.UTF_8).substring(16)));
                        }
                    } else sources.add(new Source(contentBytes, type, new String(messageByte, StandardCharsets.UTF_8)));
                }
            } else break;
        }
        return sources;
    }


    /**
     * 消息源的模型类，内部使用
     */
    private static class Source {

        /** 构造方法注入数据 */
        private byte[] bytes;

        /** 构造方法注入数据 */
        private int type;

        /** 构造方法注入数据 */
        private String content;

        /** 构造方法注入数据 */
        Source(byte[] bytes, int type, String content) {
            this.bytes = bytes;
            this.type = type;
            this.content = content;
        }
    }
}
