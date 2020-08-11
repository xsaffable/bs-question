package com.demo.entity.vo.viewindex;

import lombok.Data;

import java.util.List;

/**
 * @author affable
 * @description userQVO
 * @date 2020/8/9 20:11
 */
@Data
public class UserQVO {

    private String uid;
    private String qid;
    private String qtypeId;
    private String qtitle;
    private List<UserQTVO> ts;

}
