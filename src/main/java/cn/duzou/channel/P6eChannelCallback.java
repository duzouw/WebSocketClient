package cn.duzou.channel;



import cn.duzou.channel.blibli.P6eBliBliChannelMessage;
import cn.duzou.channel.douyu.P6eDouYuChannelMessage;

import java.util.List;

/**
 * 弹幕服务连接器的消息回调类
 * @version 1.0
 */
public interface P6eChannelCallback {

    /**
     * 斗鱼直播的消息回调
     */
    public interface DouYu {
        public void execute(List<P6eDouYuChannelMessage> messages);
    }

    /**
     * BliBli直播的消息回调
     */
    public interface BliBli {
        public void execute(List<P6eBliBliChannelMessage> messages);
    }
}
