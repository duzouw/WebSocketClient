package cn.duzou.log.service;


import cn.duzou.log.entity.BilibiliTv;
import cn.duzou.log.entity.DouYuTv;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.scheduling.annotation.Async;

import java.util.Map;

public interface DouYuTvService extends IService<DouYuTv> {

    /**
     * 添加记录
     * @param m
     * @return  成功 true 反之 false
     */
    @Async
    void insertMes(Map m);
}
