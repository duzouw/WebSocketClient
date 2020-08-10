package cn.duzou.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BilibiliTv implements Serializable {
    private static final long serialVersionUID = 5324347846126810724L;

    /**
     * id
     */
    @TableId(value = "bilibili_id", type = IdType.AUTO)
    private String bilibiliId;

    /**
     * 房间号
     */
    private String roomId;

    /**
     * 消息类型
     */
    private String mesType;

    /**
     * 用户名
     */
    private String username;

    /**
     * 弹幕
     */
    private String mes;

    /**
     * 弹幕时间
     */
    private LocalDateTime createTime;
}
