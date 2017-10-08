package com.nowui.chuangshi.api.minhang.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/minhang/member/record")
public class MinhangMemberRecordController extends Controller {

    @ActionKey("/mobile/minhang/member/record/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/record/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/record/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/record/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/member/record/delete")
    public void delete() {

        renderSuccessJson();
    }

}