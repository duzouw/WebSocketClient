package cn.duzou.channel.douyu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 斗鱼直播 https://www.douyu.com
 * 斗鱼直播消息模型类
 * @version 1.0
 */
public class P6eDouYuChannelMessage {

    /** 原消息的内容 */
    private byte[] original;
    /** 原消息的文本内容 */
    private String text;
    /** 反序列化后的消息内容 */
    private Map<String, Object> data;

    /**
     * 创建斗鱼消息模型类
     * @param bytes 消息二进制流数组
     * @param text 消息文本内容
     * @return P6eHuoMaoChannelMessage 消息模型类
     */
    public static P6eDouYuChannelMessage build(byte[] bytes, String text) {
        return new P6eDouYuChannelMessage(bytes, text);
    }

    /**
     * 构造方法消息模型类
     * @param bytes 消息二进制数组
     * @param text 消息文本内容
     */
    private P6eDouYuChannelMessage(byte[] bytes, String text) {
        this.original = bytes;
        this.text = text;
        this.data = deserialization(text);
    }

    /**
     * 获取消息二进制数组
     * @return 消息二进制数组
     */
    public byte[] original() {
        return original;
    }

    /**
     * 获取消息文本
     * @return 消息文本
     */
    public String text() {
        return text;
    }

    /**
     * 获取消息反序列化的对象
     * @return 反序列化的对象
     */
    public Map<String, Object> data() {
        return data;
    }

    /**
     * 对消息进行序列化处理
     * @param text 消息的内容
     * @return 序列化后的内容
     */
    private static Map<String, Object> deserialization(String text) {
//        boolean bool = false;
//        List<Object> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        String[] ts = text.trim().split("/");
        for (String t : ts) {
            String[] is = t.split("@=");
            if (is.length == 1) {
                if (!(is[0].contains("/") || is[0].contains("@S") || is[0].contains("@A"))) map.put(is[0], "");
//                else {
//                    bool = true;
//                    is[0] = is[0].replaceAll("@S", "\\/").replaceAll("@A", "@");
//                    list.add(deserialization(is[0]));
//                }
            } else {
                is[1] = is[1].replaceAll("@S", "\\/").replaceAll("@A", "@");
                if (is[1].contains("@=") || is[1].contains("@A") || is[1].contains("@S")) map.put(is[0], deserialization(is[1]));
                else map.put(is[0], is[1]);
            }
        }
        return  map;
    }
}
