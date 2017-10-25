package com.nowui.chuangshi.type;

public enum MinhangMemberTaskType {
	
	POSTER_PICTURE("POSTER_PICTURE", "海报自拍"),
	PARTY_HISTORY_RECORD("PARTY_HISTORY_RECORD", "党史录音"),
	PARTY_SONG_RECORD("PARTY_SONG_RECORD", "党歌录音"),
	HAND_PRINT_PICTURE("HAND_PRINT_PICTURE", "手印图片"),
	LOCATION_QUESTION("LOCATION_QUESTION", "位置答题"),
	INFO_QUESTION("INFO_QUESTION", "信息答题"),
	TIMELINE_EVENT_QUESTION("TIMELINE_EVENT_QUESTION", "时间轴事件答题"),
	VIDEO_QUESTION("VIDEO_QUESTION", "视频答题");

    private String key;
    private String value;

    private MinhangMemberTaskType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
