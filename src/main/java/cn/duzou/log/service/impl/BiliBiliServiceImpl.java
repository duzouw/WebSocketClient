package cn.duzou.log.service.impl;


import cn.duzou.log.entity.BilibiliTv;
import cn.duzou.log.mapper.BiliBiliMapper;
import cn.duzou.log.service.BiliBiliService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BiliBiliServiceImpl extends ServiceImpl<BiliBiliMapper, BilibiliTv> implements BiliBiliService {
    @Override
    public void insertMes(BilibiliTv b) {
        b.setCreateTime(LocalDateTime.now());
        save(b);
    }
}
