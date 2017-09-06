package com.nowui.chuangshi.api.jiangling.mobile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.model.JianglingGame;
import com.nowui.chuangshi.api.jiangling.model.JianglingGameMember;
import com.nowui.chuangshi.api.jiangling.service.JianglingGameMemberService;
import com.nowui.chuangshi.api.jiangling.service.JianglingGameService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.Util;

import java.util.ArrayList;
import java.util.List;

@ControllerKey("/mobile/jiangling/game")
public class JianglingGameController extends Controller {

    @ActionKey("/mobile/jiangling/game/list")
    public void list() {
        String request_app_id = getRequest_app_id();

        List<JianglingGame> resultList = new ArrayList<JianglingGame>();
        List<JianglingGame> jianglingGameList = JianglingGameService.instance.appList(request_app_id);
        List<JianglingGameMember> jianglingGameMemberList = JianglingGameMemberService.instance.allList();

        for (JianglingGameMember jianglingGameMember : jianglingGameMemberList) {
            jianglingGameMember.keep(JianglingGameMember.GAME_ID, JianglingGameMember.GAME_MEMBER_NAME, JianglingGameMember.GAME_MEMBER_AVATAR, JianglingGameMember.GAME_MEMBER_SCORE, JianglingGameMember.GAME_MEMBER_RANK);
        }

        for (JianglingGame jianglingGame : jianglingGameList) {
            List<JianglingGameMember> memberList = new ArrayList<JianglingGameMember>();

            for (JianglingGameMember jianglingGameMember : jianglingGameMemberList) {
                if (jianglingGameMember.getGame_id().equals(jianglingGame.getGame_id())) {
                    memberList.add(jianglingGameMember);
                }
            }

            jianglingGame.put("member_list", memberList);

            resultList.add(jianglingGame);
        }

        validateResponse(JianglingGame.SYSTEM_CREATE_TIME, "member_list");

        renderSuccessJson(resultList);
    }

    @ActionKey("/mobile/jiangling/game/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/game/save")
    public void save() {
        validateRequest("member_list");

        JSONObject jsonObject = getParameterJSONObject();
        JSONArray jsonArray = jsonObject.getJSONArray("member_list");

        String game_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();

        JianglingGame jianglingGame = new JianglingGame();
        jianglingGame.setGame_id(game_id);
        jianglingGame.setApp_id(request_app_id);
        Boolean result = JianglingGameService.instance.save(jianglingGame, request_app_id);

        if (!result) {
            throw new RuntimeException("保存不成功");
        }

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject memberJsonObject = jsonArray.getJSONObject(i);

            String name = Util.getEmoji(memberJsonObject.getString("name"));

            JianglingGameMember jianglingGameMember = new JianglingGameMember();
            jianglingGameMember.setGame_id(game_id);
            jianglingGameMember.setGame_member_token(memberJsonObject.getString("token"));
            jianglingGameMember.setGame_member_name(name);
            jianglingGameMember.setGame_member_avatar(memberJsonObject.getString("avatar"));
            jianglingGameMember.setGame_member_score(memberJsonObject.getString("score"));
            jianglingGameMember.setGame_member_rank(memberJsonObject.getInteger("rank"));

            result = JianglingGameMemberService.instance.save(jianglingGameMember, request_app_id);

            if (!result) {
                throw new RuntimeException("保存不成功");
            }
        }

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/game/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/game/delete")
    public void delete() {

        renderSuccessJson();
    }

}