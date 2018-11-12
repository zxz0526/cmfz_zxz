package com.baizhi.dao;

import com.baizhi.entity.Chapter;

public interface ChapterDAO extends BaseDAO<Chapter> {

    void deleteAll(String c_id);
}
