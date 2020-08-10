package cn.duzou.netty.client;

import cn.duzou.netty.client.actuator.P6eActuatorAbstractAsync;
import cn.duzou.netty.client.config.P6eConfig;
import cn.duzou.netty.client.model.P6eModel;
import cn.duzou.netty.client.model.P6eModelCache;
import cn.duzou.netty.client.model.P6eNioModel;

/**
 * Netty Web Socket 应用类
 * @author LiDaShuang
 * @version 1.0.0
 */
public class P6eWebsocketClientApplication {
    private P6eModel p6eModel;

    public static P6eWebsocketClientApplication run() {
        return new P6eWebsocketClientApplication(P6eNioModel.class, new P6eConfig[] {});
    }

    public static P6eWebsocketClientApplication run(P6eModel model) {
        return new P6eWebsocketClientApplication(model, new P6eConfig[] {});
    }

    public static P6eWebsocketClientApplication run(Class<? extends P6eModel> model) {
        return new P6eWebsocketClientApplication(model, new P6eConfig[] {});
    }

    public static P6eWebsocketClientApplication run(P6eModel model, P6eConfig... products) {
        return new P6eWebsocketClientApplication(model, products);
    }

    public static P6eWebsocketClientApplication run(Class<? extends P6eModel> model, P6eConfig... products) {
        return new P6eWebsocketClientApplication(model, products);
    }

    public P6eWebsocketClientApplication(Class<? extends P6eModel> model, P6eConfig[] products) {
        try {
            this.p6eModel = model.newInstance().run().connect(products);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public P6eWebsocketClientApplication(P6eModel model, P6eConfig[] products) {
        this.p6eModel = model.connect(products);
    }

    public P6eWebsocketClientApplication connect(P6eConfig product) {
        this.p6eModel.connect(product);
        return this;
    }

    public P6eWebsocketClientApplication connect(P6eConfig[] products) {
        this.p6eModel.connect(products);
        return this;
    }

    public P6eWebsocketClientApplication close() {
        this.p6eModel.close();
        return this;
    }

    public P6eWebsocketClientApplication close(String id) {
        this.p6eModel.close(id);
        return this;
    }

    public P6eWebsocketClientApplication close(String[] ids) {
        this.p6eModel.close(ids);
        return this;
    }

    public void destroy() {
        this.p6eModel.close();
        P6eActuatorAbstractAsync.destroyThreadPool();
    }

    public P6eModelCache getP6eModelCache() {
        return this.p6eModel.getP6eModelCache();
    }

}
