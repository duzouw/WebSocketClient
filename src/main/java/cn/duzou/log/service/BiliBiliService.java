package cn.duzou.log.service;


import cn.duzou.log.entity.BilibiliTv;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.scheduling.annotation.Async;

public interface BiliBiliService extends IService<BilibiliTv> {

    /**
     * 添加记录
     * @param b
     * @return  成功 true 反之 false
     */
    @Async
    void insertMes(BilibiliTv b);
}
