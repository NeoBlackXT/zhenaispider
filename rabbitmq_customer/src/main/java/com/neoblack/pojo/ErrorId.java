package com.neoblack.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Neo on 2017/4/9.
 */
@Table(name = "tb_errorid")
public class ErrorId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long userid;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
