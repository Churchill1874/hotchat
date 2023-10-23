package com.ent.hotchat.pojo.manage.resp.administrator;

import com.ent.hotchat.common.constant.enums.AdminRoleEnum;
import com.ent.hotchat.entity.Administrator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginResp implements Serializable {
    private static final long serialVersionUID = -1325167787981976919L;

    @ApiModelProperty("角色")
    private AdminRoleEnum roleType;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("token令牌")
    private String tokenId;

    public static LoginResp CreateLoginResp(Administrator ob,String tokenId){
        LoginResp loginResp = new LoginResp();
        loginResp.setRoleType(ob.getRoleType());
        loginResp.setName(ob.getName());
        loginResp.setAccount(ob.getAccount());
        loginResp.setTokenId(tokenId);
        return loginResp;
    }

}
