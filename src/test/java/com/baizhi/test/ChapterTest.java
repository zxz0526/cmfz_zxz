package com.baizhi.test;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ChapterTest extends BaseTest {

    @Autowired
    private ChapterService chapterService;

    @Test
    public void add(){
        Chapter chapter = new Chapter();
        chapter.setTitle("a");
        chapter.setFileSize("1");
        chapter.setDuration("10");
        chapter.setDownPath("111");
        chapter.setC_id("1");
        chapterService.addChapter(chapter);
    }
    @Test
    public void delete(){
        chapterService.delete("c424a41c-f277-4a6c-88ea-049e183b1373");
    }

    @Test
    public void deleteAll(){
            chapterService.deleteAll("1");

    }
}
