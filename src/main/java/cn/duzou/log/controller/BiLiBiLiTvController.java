package cn.duzou.log.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequestMapping("bilibiliTv")
public class BiLiBiLiTvController {
//
//
//
//
//
//    @Resource
//    private BiliBiliService bs;
//
//
//    /**
//     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
//     */
//    private static int onlineCount = 0;
//    /**
//     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
//     */
//    private static volatile ConcurrentHashMap<String, String> webSocketMap = new ConcurrentHashMap<>();
//    BiLiveRoom client;
//
//    @GetMapping("list")
//    public ConcurrentHashMap<String, String> listRoom() {
//        return webSocketMap;
//    }
//
//
//    @GetMapping("roomId/{id}")
//    public String addRoomId(@PathVariable String id) {
//
//
//        if (!webSocketMap.containsKey(id)) {
//            webSocketMap.put(id, id);
//            //加入set中
//            room(id);
//        }
//
//
//        return "???";
//    }
//
//    @GetMapping("stop/{id}")
//    public String stopRoom(@PathVariable String id) {
//        webSocketMap.remove(id);
//        return "...";
//    }
//
//    @Async("asyncThread")
//    void room(String id) {
//
//        // 需要使用一个HandlerHolder对象,在这个对象里定义响应弹幕的操作
//        // HandlerHolder由两部分组成,一部分是处理观众信息的UserCountHandler,一部分是处理其他事件的CmdHandler
//        HandlerHolder handlerHolder = new HandlerHolder();
//
//        // 收到事件时打印事件(字符串)
//        handlerHolder.addCmdHandler(json -> {
//
//            if (!webSocketMap.containsKey(id)) {
//                System.out.println(id + "断开连接");
//                client.stop();
//                return;
//            }
//
//            JSONObject cmd = JSONObject.parseObject(json);
//            switch (BiLiveCmd.getByValue(cmd.getString("cmd"))) {
//                case DANMU_MSG:
//                    JSONArray info = cmd.getJSONArray("info");
//
//                    BilibiliTv b = new BilibiliTv();
//                    b.setRoomId(id);
//                    b.setMesType("弹幕消息");
//                    b.setUsername(info.getJSONArray(2).getString(1));
//                    b.setMes(info.getString(1));
////                    log.info("收录弹幕{}", ? "成功":"失败");
//                    bs.insertMes(b);
//                    break;
//                default:
//                    break;
//
//            }
//        });
//
//        client = new BiLiveRoom(Long.parseLong(id), handlerHolder);
//        client.start();
//    }

}
