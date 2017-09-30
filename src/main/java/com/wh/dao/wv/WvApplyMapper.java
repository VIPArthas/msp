package com.wh.dao.wv;

import com.wh.entity.WvApply;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface WvApplyMapper {
    int deleteByPrimaryKey(String id);

    int insert(WvApply record);

    int insertSelective(WvApply record);

    WvApply selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WvApply record);

    int updateByPrimaryKey(WvApply record);
    WvApply selectByKey(int key);
    WvApply selectByOpenId(String openId);
    List<WvApply> getPresenteeList(int key2);
    List<WvApply> searchAllListPage(WvApply wvApply,RowBounds rowBounds);
    List<WvApply> notApplyData(WvApply wvApply,RowBounds rowBounds);
    List<WvApply> notPay(WvApply wvApply,RowBounds rowBounds);
    List<WvApply> payEd(WvApply wvApply,RowBounds rowBounds);
    List<WvApply>  searchByCardId(WvApply wvApply,RowBounds rowBounds);
    List<WvApply>  searchBySchoolNumber(WvApply wvApply,RowBounds rowBounds);
    List<WvApply>  searchByName(WvApply wvApply,RowBounds rowBounds);
    List<WvApply>  searchByPhone(WvApply wvApply,RowBounds rowBounds);
}