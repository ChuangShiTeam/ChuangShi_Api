package com.nowui.chuangshi.api.jiangling.mobile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.jiangling.model.JianglingNewGame;
import com.nowui.chuangshi.api.jiangling.model.JianglingNewGameMember;
import com.nowui.chuangshi.api.jiangling.service.JianglingNewGameMemberService;
import com.nowui.chuangshi.api.jiangling.service.JianglingNewGameService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.Util;

import java.util.ArrayList;
import java.util.List;

@ControllerKey("/mobile/jiangling/new/game")
public class JianglingNewGameController extends Controller {

    @ActionKey("/mobile/jiangling/new/game/list")
    public void list() {
        String request_app_id = getRequest_app_id();

        List<JianglingNewGame> resultList = new ArrayList<JianglingNewGame>();
        List<JianglingNewGame> jianglingNewGameList = JianglingNewGameService.instance.appList(request_app_id);
        List<JianglingNewGameMember> jianglingNewGameMemberList = JianglingNewGameMemberService.instance.allList();

        for (JianglingNewGameMember jianglingNewGameMember : jianglingNewGameMemberList) {
            jianglingNewGameMember.keep(JianglingNewGameMember.NEW_GAME_ID, JianglingNewGameMember.NEW_GAME_MEMBER_NAME, JianglingNewGameMember.NEW_GAME_MEMBER_AVATAR, JianglingNewGameMember.NEW_GAME_MEMBER_SCORE, JianglingNewGameMember.NEW_GAME_MEMBER_RANK);
        }

        for (JianglingNewGame jianglingNewGame : jianglingNewGameList) {
            List<JianglingNewGameMember> memberList = new ArrayList<JianglingNewGameMember>();

            for (JianglingNewGameMember jianglingNewGameMember : jianglingNewGameMemberList) {
                if (jianglingNewGameMember.getNew_game_id().equals(jianglingNewGame.getNew_game_id())) {
                    memberList.add(jianglingNewGameMember);
                }
            }

            jianglingNewGame.put("member_list", memberList);

            resultList.add(jianglingNewGame);
        }

        validateResponse(JianglingNewGame.SYSTEM_CREATE_TIME, "member_list");

        renderSuccessJson(resultList);
    }

    @ActionKey("/mobile/jiangling/new/game/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/new/game/save")
    public void save() {
        validateRequest("member_list");

        JSONObject jsonObject = getParameterJSONObject();
        JSONArray jsonArray = jsonObject.getJSONArray("member_list");

        String game_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();

        JianglingNewGame jianglingNewGame = new JianglingNewGame();
        jianglingNewGame.setNew_game_id(game_id);
        jianglingNewGame.setApp_id(request_app_id);
        Boolean result = JianglingNewGameService.instance.save(jianglingNewGame, request_app_id);

        if (!result) {
            throw new RuntimeException("保存不成功");
        }

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject memberJsonObject = jsonArray.getJSONObject(i);

            String name = Util.getEmoji(memberJsonObject.getString("name"));

            JianglingNewGameMember jianglingNewGameMember = new JianglingNewGameMember();
            jianglingNewGameMember.setNew_game_id(game_id);
            jianglingNewGameMember.setNew_game_member_token(memberJsonObject.getString("token"));
            jianglingNewGameMember.setNew_game_member_name(name);
            jianglingNewGameMember.setNew_game_member_avatar(memberJsonObject.getString("avatar"));
            jianglingNewGameMember.setNew_game_member_score(memberJsonObject.getString("score"));
            jianglingNewGameMember.setNew_game_member_rank(memberJsonObject.getInteger("rank"));

            result = JianglingNewGameMemberService.instance.save(jianglingNewGameMember, request_app_id);

            if (!result) {
                throw new RuntimeException("保存不成功");
            }
        }

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/new/game/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/jiangling/new/game/delete")
    public void delete() {

        renderSuccessJson();
    }

}