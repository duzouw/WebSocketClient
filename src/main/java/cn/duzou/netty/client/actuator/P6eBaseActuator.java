package cn.duzou.netty.client.actuator;


import cn.duzou.netty.client.converter.P6eContextConverter;

/**
 * 这是 Web Socket Client 基础的执行器
 * @author LiDaShuang
 * @version 1.0.0
 */
public abstract class P6eBaseActuator implements P6eContextConverter, P6eActuator {

    /**
     * 返回当前实现的类型
     * @return 类型数据
     */
    public abstract String toType();
}
