package com.ursful.framework.mina.common.support;

/**
 * 类名：IClientReady
 * 创建者：huangyonghua
 * 日期：2019/3/7 8:50
 * 版权：Hymake Copyright(c) 2017
 * 说明：[类说明必填内容，请修改]
 */
public interface IClientStatus {
    void clientReady(String cid);
    void clientClose(String cid);
}
