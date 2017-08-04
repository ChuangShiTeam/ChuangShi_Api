package com.nowui.chuangshi.api.file.cache;

import com.nowui.chuangshi.api.file.dao.FileDao;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.common.cache.Cache;

public class FileCache extends Cache {

    public static final String FILE_ITEM_CACHE = "file_item_cache";

    public FileCache() {
        setDao(new FileDao());

        setItemCache(FILE_ITEM_CACHE, File.FILE_ID);
    }

}