package com.weitu.songda.entity.sys;import java.io.Serializable;import com.weitu.framework.core.annotation.RsEntity;import com.weitu.framework.component.orm.annotation.RdColumn;import com.weitu.framework.component.orm.annotation.RdTable;//@RsEntity@RdTable(name = "SYS_TEST")public class SysTest implements Serializable{    private static final long serialVersionUID = 1L;    //aaa (NULL)    public static final String T_NAME = "NAME";    @RdColumn(name = T_NAME)    private String name;    public String getName(){        return this.name;    }    public void setName(String name){        this.name = name;    }}