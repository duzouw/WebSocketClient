package cn.duzou.controller;

import cn.duzou.channel.douyu.P6eDouYuChannel;
import cn.duzou.channel.douyu.P6eDouYuChannelMessage;
import cn.duzou.log.service.DouYuTvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@RestController
@RequestMapping
public class LiveController {

    @Resource
    private DouYuTvService douYuTvService;

    @GetMapping("/{id}")
    public String test(@PathVariable String id){


        /* 斗鱼连接弹幕房间 */
        P6eDouYuChannel p6eDouYuChannel = P6eDouYuChannel.create(id, messages -> {
            for (P6eDouYuChannelMessage message : messages) {
                douYuTvService.insertMes(message.data());
            }
        });
        return id;
    }

}
