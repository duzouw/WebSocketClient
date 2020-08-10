package cn.duzou.netty.client.actuator;


import cn.duzou.netty.client.P6eWebSocketClient;
import lombok.extern.slf4j.Slf4j;

/**
 * 对同步处理器的默认实现
 * @author LiDaShuang
 * @version 1.0.0
 */

@Slf4j
public class P6eActuatorDefault extends P6eActuatorAbstract {

    @Override
    public void onOpen(P6eWebSocketClient webSocket) {
        log.debug("WebSocket [ " + webSocket + " ] ==> onOpen.");
    }

    @Override
    public void onClose(P6eWebSocketClient webSocket) {
        log.debug("WebSocket [ " + webSocket + " ] ==> onClose.");
    }

    @Override
    public void onError(P6eWebSocketClient webSocket, Throwable throwable) {
        log.debug("WebSocket [ " + webSocket + " ] ==> onError. \n throwable => " + throwable.getMessage());
    }

    @Override
    public void onMessageText(P6eWebSocketClient webSocket, String message) {
        log.debug("WebSocket [ " + webSocket + " ] ==> onMessageText. \n message => " + message);
    }

    @Override
    public void onMessageBinary(P6eWebSocketClient webSocket, byte[] message) {
        log.debug("WebSocket [ " + webSocket + " ] ==> onMessageBinary. \n message => " + new String(message));
    }

    @Override
    public void onMessagePong(P6eWebSocketClient webSocket, byte[] message) {
        log.debug("WebSocket [ " + webSocket + " ] ==> onMessagePong. \n message => " + new String(message));
    }

    @Override
    public void onMessagePing(P6eWebSocketClient webSocket, byte[] message) {
        log.debug("WebSocket [ " + webSocket + " ] ==> onMessagePing. \n message => " + new String(message));
    }

    @Override
    public void onMessageContinuation(P6eWebSocketClient webSocket, byte[] message) {
        log.debug("WebSocket [ " + webSocket + " ] ==> onMessageContinuation. \n message => " + new String(message));
    }

}
