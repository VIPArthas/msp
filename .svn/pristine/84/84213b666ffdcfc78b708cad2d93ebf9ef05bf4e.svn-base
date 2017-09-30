package com.wh.dao.wmh;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.wh.entity.WmhNews;

public interface WmhNewsMapper {
    int deleteByPrimaryKey(String id);

    int insert(WmhNews record);

    int insertSelective(WmhNews record);

    WmhNews selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WmhNews record);

    int updateByPrimaryKeyWithBLOBs(WmhNews record);

    int updateByPrimaryKey(WmhNews record);
    
    List<WmhNews> selectAllNews();
    
    List<WmhNews> selectWmhNewsListPage(WmhNews record, RowBounds rowBounds);
    
    /**
     * 根据时间倒序查询10条数据
     * @author 王鹏翔
     * @Date 2017年3月15日  上午10:17:07
     * @return
     */
    List<Map<String, Object>> selectNewsByCrateTimeMobileListPage(WmhNews record, RowBounds rowBounds);
    
    /**
     * 根据条件查询新闻条数
     * @author 王鹏翔
     * @Date 2017年4月6日  下午5:11:31
     * @param news
     * @return
     */
    Long selectNewsCount(WmhNews news);
    
    /**
     * 根据sign查询新闻数量和上次同步时间
     * @param news
     * @return
     */
    Map<String,Object> selectSyncTimeAndNewsCount(WmhNews news);
}