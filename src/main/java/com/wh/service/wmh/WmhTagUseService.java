package com.wh.service.wmh;

import java.util.List;

import com.wh.entity.WmhTagUse;

/** 
 * @param 
 * @author 王鹏翔 
 * @date 2017年3月8日 上午10:33:40 
 * @return 
 */
public interface WmhTagUseService {

	
	/**
	 * 添加标签
	 * @author 王鹏翔
	 * @Date 2017年3月8日  上午10:35:12
	 * @param record
	 * @return
	 */
	int insertSelective(WmhTagUse record) throws Exception;
	
	/**
     * 修改标签
     * @author 王鹏翔
     * @Date 2017年3月8日  下午1:23:29
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(WmhTagUse record) throws Exception;
	
	/**
     * 根据标签名称查询标签信息
     * @author 王鹏翔
     * @Date 2017年3月8日  上午11:39:41
     * @param tagName
     * @return
     */
    WmhTagUse selectTagByTagName(String tagName) throws Exception;
    
    /**
     * 查询常用标签
     * @author 王鹏翔
     * @Date 2017年3月13日  下午1:54:01
     * @return
     */
    List<WmhTagUse> selectCommonUseTagList() throws Exception;
}
