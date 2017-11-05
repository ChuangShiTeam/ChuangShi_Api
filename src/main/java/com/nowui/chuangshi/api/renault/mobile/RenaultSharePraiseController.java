package com.nowui.chuangshi.api.renault.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.renault.model.RenaultShare;
import com.nowui.chuangshi.api.renault.model.RenaultSharePraise;
import com.nowui.chuangshi.api.renault.service.RenaultSharePraiseService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/renault/share/praise")
public class RenaultSharePraiseController extends Controller {

    @ActionKey("/mobile/renault/share/praise/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/praise/find")
    public void find() {
        
        validateRequest(RenaultShare.SHARE_ID);
        
        String request_user_id = getRequest_user_id();
        
        RenaultShare renault_share = getModel(RenaultShare.class);
        
        RenaultSharePraise renaultSharePraise = RenaultSharePraiseService.instance.userAndShareFind(request_user_id, renault_share.getShare_id());
        if (renaultSharePraise == null) {
            renderSuccessJson(false);
        } else {
            renderSuccessJson(true);
        }
    }

    @ActionKey("/mobile/renault/share/praise/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/praise/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/praise/delete")
    public void delete() {

        renderSuccessJson();
    }

}