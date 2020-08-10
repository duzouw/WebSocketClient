package cn.duzou.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 创建时间：2020/5/27
 * 修改时间：2020/5/27
 * 描述：
 *
 * @author lzw
 * 版本：private String.0
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DouYuTv implements Serializable {

    /**
     * id
     */
    @TableId(value = "douyu_tv_id", type = IdType.AUTO)
    private String douyuTvId;
    private String nn;
    private String eid;
    private String bst;
    private String brid;
    private String bnid;
    private String gfid;
    private String bl;
    private String pid;
    private String type;
    private String rid;
    private String uid;
    private String receiveUid;
    private String dw;
    private String ic;//avatar_v3/202001/24c89ba9ae354cfa951c1cd4d6a1b92e
    private String dfrom;
    private String gpf;
    private String bnl;
    private String bnn;
    private String receiveNn;//receive_nn
    private String level;
    private String el;
    private String bcnt;
    private String cm;
    private String gs;
    private String hits;
    private String gfcnt;
    private String ct;
    private String sahf;
    private String hc;
    private String fc;
    private String eic;

    private String cst;
    private String fl;
    private String urlev;
    private String dms;
    private String txt;
    private String pdg;
    private String cbid;
    private String pdk;
    private String cid;
    private String lk;

    /**
     * 弹幕时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

//    private String type;       // 表示为“弹幕”消息，固定为 chatmsg
//    private String rid;        // 房间 id
//    private String uid;      // 发送者 uid
//    private String nn;        // 发送者昵称
//    private String txt;      // 弹幕文本内容
//    private String level;     // 用户等级
//    private String gt;          // 礼物头衔：默认值 0（表示没有头衔）
//    private String col;      // 颜色：默认值 0（表示默认颜色弹幕）
//    private String ct;       // 客户端类型：默认值 0
//    private String rg;       // 房间权限组：默认值 private String（表示普通权限用户）
//    private String pg;        // 平台权限组：默认值 private String（表示普通权限用户）
//    private String dlv;       // 酬勤等级：默认值 0（表示没有酬勤）
//    private String dc;       // 酬勤数量：默认值 0（表示没有酬勤数量）
//    private String bdlv;     // 最高酬勤等级：默认值 0（表示全站都没有酬勤）
//    private String cmt;     // 弹幕具体类型: 默认值 0（普通弹幕）
//    private String sahf;    // 扩展字段，一般不使用，可忽略
//    private String ic;      // 用户头像
//    private String nl;      // 贵族等级
//    private String nc;      // 贵族弹幕标识,0-非贵族弹幕,private String-贵族弹幕,默认值 0
//    private String gatin;     // 进入网关服务时间戳
//    private String chtin;     // 进入房间服务时间戳
//    private String repin;      // 进入发送服务时间戳
//    private String bnn;       // 徽章昵称
//    private String bl;       // 徽章等级
//    private String brid;      // 徽章房间 id
//    private String hc;       // 徽章信息校验码
//    private String ol;       // 主播等级
//    private String rev;       // 是否反向弹幕标记: 0-普通弹幕，private String-反向弹幕, 默认值 0
//    private String hl;      // 是否高亮弹幕标记: 0-普通，private String-高亮, 默认值 0
//    private String ifs;      // 是否粉丝弹幕标记: 0-非粉丝弹幕，private String-粉丝弹幕, 默认值 0
//    private String p2p;      // 服务功能字段
//    private String el;      // 用户获得的连击特效：数组类型，数组包含 eid（特效 id）,etp（特效类型）,sc（特效次数）信息，ef（特效标志）。

}
