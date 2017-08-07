package com.nowui.chuangshi.api.file.dao;

import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.common.dao.Dao;

public class FileDao extends Dao {

    public FileDao() {
        setModel(new File());
    }

}