package com.wh.dao.wchat;

import com.wh.dto.SourceDto;
import com.wh.dto.SourceInfoDto;
import com.wh.dto.UserRouteDto;
import com.wh.entity.TbUser;
import com.wh.entity.TbWchatuser;
import com.wh.exception.DAOException;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface TbWchatuserMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbWchatuser record);

    int saveTbWchatuser(TbWchatuser record);

    TbWchatuser selectByPrimaryKey(String id);
    /**
     * 根据openId查询
     * @param openId
     * @return
     */
    TbWchatuser findWchatuserByOpenId(String openId);

    int updateTbWchatuser(TbWchatuser record);

    int updateByPrimaryKey(TbWchatuser record);

    List<TbWchatuser> queryByUserId(String user_id);
    
    /**
     * 推广监控列表页面数据查询
     * @param sourceDto
     * @param rowBounds
     * @return
     * @author 王鹏翔
     * @date 2016年8月18日
     */
    List<Map<Object, Object>> selectTgjkListPage(SourceDto sourceDto,RowBounds rowBounds);
    
    /**
     * 推广监控页面关注数量查询
     */
    List<Map<Object, Object>> selectCount(SourceDto sourceDto);
    
    
    /**
     * 运营推广列表页面数据查询
     * @param sourceDto
     * @return
     * @author 王鹏翔
     * @date 2016年8月16日
     */
    List<Map<Object, Object>> selectYytgList(SourceDto sourceDto);
    
    /**
     * 表头统计总关注、注册、活跃人数
     * @author 王鹏翔
     * @Date 2016年9月30日  下午5:49:05
     * @param sourceDto
     * @return
     */
    List<Map<Object, Object>> selectYytgListPage(SourceDto sourceDto);

    /**
     * 活跃用户详情
     * @param sourceDto
     * @param rowBounds
     * @return
     * @author 王鹏翔
     * @date 2016年8月22日
     */
    List<Map<Object, Object>> selectActiveUserListPage(SourceDto sourceDto,RowBounds rowBounds);
    
    
    /**
     * 根据createtime查询每个二维码的 关注个数
     * @param sourceDto
     * @return
     * @author 王鹏翔
     * @date 2016年7月4日
     */
    List<Map<Object, Object>> selectSourceUserCountByCreateTimeListPage(SourceDto sourceDto,RowBounds rowBounds);
   
    
    /**
     * 分页查询所有关注的用户的注册来源信息
     * @author 何阳
     * @Date 2016年7月20日
     * @param sifd
     * @param rowBounds
     * @return
     */
    List<Map<Object, Object>> selectRegistFromListPage(SourceInfoDto sifd,RowBounds rowBounds);
    
    /**
     * 查询用户注册来源信息
     * @author 何阳
     * @Date 2016年7月20日
     * @param sifd
     * @return
     */
    List<Map<Object, Object>> selectRegistFromList(SourceInfoDto sifd);
    
//    //根据Id查用户的注册来源信息
//    List<Map<Object,Object>> selectRegistFromListPageById(SourceInfoDto sifd,RowBounds rowBounds);
    
    /**
     * 查询出所有用户的Id
     * @author 何阳
     * @Date 2016年7月20日
     * @param tu
     * @return
     */
    List<String> queryUserIdList(TbUser tu);
    
    /**
     * 分页查询所有用户行程信息
     * @author 何阳
     * @Date 2016年7月20日
     * @param urd
     * @param rowBounds
     * @return
     */
    List<Map<Object, Object>> selectUserRouteListPage(UserRouteDto urd,RowBounds rowBounds);
    
    /**
     * 运营推广查询数据详情
     * @author 何阳
     * @Date 2016年8月22日
     * @param sourceDto
     * @param rowBounds
     * @return
     */
    List<Map<Object, Object>> selectDetailsListPage(SourceDto sourceDto,RowBounds rowBounds);
    
    
    /**
     * 查询wchatUser中用户的地理位置信息
     * @return
     * @author 徐优优
     * @date 2016年8月22日
     */
    List<Map<String, Object>> selectWchatUserAddress();

    /**
     * 查询没有资料的用户
     * @return
     */
    List<TbWchatuser> queryNoMemberInfo();

    /**
     * 根据时间查询校联网服务号用户关注量和用户注册量
     * @param sourceInfoDto
     * @return
     */
    Map<String, Object> selectOpenIdAndUserIdCount(SourceDto SourceDto);

    /**
     * 根据openid 批量更新状态
     * @param tbWchatuser
     * @return
     */
    int updateStatusByOpenId(TbWchatuser tbWchatuser);

    /**
     * 根据id更新信息（只含有呢称，头像，和关注状态）
     * @param tbWchatuser
     * @return
     */
    int updateTbWchatuserById(TbWchatuser tbWchatuser);


    TbWchatuser searchByUserId(String userId);
    
    /**
     * 根据userid查询微门户用户关注状态
     * @author 王鹏翔
     * @Date 2017年3月21日  上午9:09:00
     * @param UserId
     * @return
     */
    Integer selectTbWchatsuerByUserId(String UserId);
    
    int deleteUserByUserId(String UserId);

	int deleteUserByOpenId(String openId);

	void updateOpenIdNull(String openId) throws DAOException;
}