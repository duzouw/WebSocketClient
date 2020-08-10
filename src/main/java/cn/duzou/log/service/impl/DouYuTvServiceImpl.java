package cn.duzou.log.service.impl;


import cn.duzou.log.entity.BilibiliTv;
import cn.duzou.log.entity.DouYuTv;
import cn.duzou.log.mapper.BiliBiliMapper;
import cn.duzou.log.mapper.DouYuTvMapper;
import cn.duzou.log.service.BiliBiliService;
import cn.duzou.log.service.DouYuTvService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DouYuTvServiceImpl extends ServiceImpl<DouYuTvMapper, DouYuTv> implements DouYuTvService {
    @Override
    public void insertMes(Map m) {
        DouYuTv d = new DouYuTv();

        d.setNn(m.containsKey("nn") ? String.valueOf(m.get("nn")) : null);
        d.setEid(m.containsKey("eid") ? String.valueOf(m.get("eid")) : null);
        d.setBst(m.containsKey("bst") ? String.valueOf(m.get("bst")) : null);
        d.setBrid(m.containsKey("brid") ? String.valueOf(m.get("brid")) : null);
        d.setBnid(m.containsKey("bnid") ? String.valueOf(m.get("bnid")) : null);
        d.setGfid(m.containsKey("gfid") ? String.valueOf(m.get("gfid")) : null);
        d.setBl(m.containsKey("bl") ? String.valueOf(m.get("bl")) : null);
        d.setPid(m.containsKey("pid") ? String.valueOf(m.get("pid")) : null);
        d.setType(m.containsKey("type") ? String.valueOf(m.get("type")) : null);
        d.setRid(m.containsKey("rid") ? String.valueOf(m.get("rid")) : null);
        d.setUid(m.containsKey("uid") ? String.valueOf(m.get("uid")) : null);
        d.setReceiveUid(m.containsKey("receive_uid") ? String.valueOf(m.get("receive_uid")) : null);
        d.setDw(m.containsKey("dw") ? String.valueOf(m.get("dw")) : null);
        d.setIc(m.containsKey("ic") ? String.valueOf(m.get("ic")) : null);
        d.setDfrom(m.containsKey("from") ? String.valueOf(m.get("from")) : null);
        d.setGpf(m.containsKey("gpf") ? String.valueOf(m.get("gpf")) : null);
        d.setBnl(m.containsKey("bnl") ? String.valueOf(m.get("bnl")) : null);
        d.setBnn(m.containsKey("bnn") ? String.valueOf(m.get("bnn")) : null);
        d.setReceiveNn(m.containsKey("receive_nn") ? String.valueOf(m.get("receive_nn")) : null);
        d.setLevel(m.containsKey("level") ? String.valueOf(m.get("level")) : null);
        d.setEl(m.containsKey("el") ? String.valueOf(m.get("el")) : null);
        d.setBcnt(m.containsKey("bcnt") ? String.valueOf(m.get("bcnt")) : null);
        d.setCm(m.containsKey("cm") ? String.valueOf(m.get("cm")) : null);
        d.setGs(m.containsKey("gs") ? String.valueOf(m.get("gs")) : null);
        d.setHits(m.containsKey("hits") ? String.valueOf(m.get("hits")) : null);
        d.setGfcnt(m.containsKey("gfcnt") ? String.valueOf(m.get("gfcnt")) : null);
        d.setCt(m.containsKey("ct") ? String.valueOf(m.get("ct")) : null);
        d.setSahf(m.containsKey("sahf") ? String.valueOf(m.get("sahf")) : null);
        d.setHc(m.containsKey("hc") ? String.valueOf(m.get("hc")) : null);
        d.setFc(m.containsKey("fc") ? String.valueOf(m.get("fc")) : null);
        d.setEic(m.containsKey("eic") ? String.valueOf(m.get("eic")) : null);
        d.setCst(m.containsKey("cst") ? String.valueOf(m.get("cst")) : null);
        d.setFl(m.containsKey("fl") ? String.valueOf(m.get("fl")) : null);
        d.setUrlev(m.containsKey("urlev") ? String.valueOf(m.get("urlev")) : null);
        d.setDms(m.containsKey("dms") ? String.valueOf(m.get("dms")) : null);
        d.setTxt(m.containsKey("txt") ? String.valueOf(m.get("txt")) : null);
        d.setPdg(m.containsKey("pdg") ? String.valueOf(m.get("pdg")) : null);
        d.setCbid(m.containsKey("cbid") ? String.valueOf(m.get("cbid")) : null);
        d.setPdk(m.containsKey("pdk") ? String.valueOf(m.get("pdk")) : null);
        d.setCid(m.containsKey("cid") ? String.valueOf(m.get("cid")) : null);
        d.setLk(m.containsKey("lk") ? String.valueOf(m.get("lk")) : null);


        d.setCreateTime(LocalDateTime.now());
        save(d);
    }
}
