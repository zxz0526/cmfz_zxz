package com.baizhi.service;

import com.baizhi.entity.Chapter;

public interface ChapterService {

    void addChapter(Chapter chapter);
    void delete(String id);
    void deleteAll(String c_id);
}
