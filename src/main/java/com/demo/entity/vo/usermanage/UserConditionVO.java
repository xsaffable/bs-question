package com.demo.entity.vo.usermanage;

import com.demo.entity.vo.BaseVO;
import lombok.Data;

/**
 * @author affable
 * @description 用户搜索条件 vo
 * @date 2020/7/10 16:09
 */
@Data
public class UserConditionVO extends BaseVO {

    private String username;

    private String nickname;

    private String email;

    private Integer sex;

}
