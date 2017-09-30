package com.wh.service.jzxx;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wh.entity.JzEvaluate;
import com.wh.entity.WhAttachements;


@Service
public interface WhAttachementsService {

    public void save(WhAttachements attchmentInfo);

    public void update(WhAttachements attchmentInfo);

    public void delete(WhAttachements attchmentInfo);

    public WhAttachements findById(String attchId);
    
    public void setAttachsEvalute(JzEvaluate jzEvaluate, String pathPix);

    /**
     * 根据管理id查询附件集合
     * 
     * @return
     */
    public List<WhAttachements> queryAttachsById(String linkId);
    
    List<Map<String, Object>> getFilePathByLinkId(String linkId);
//
//    /**
//     * 删除formLogId关联的附件
//     * 
//     * @param formLogId
//     * @return
//     */
//    public int clearAttachsByFormId(String formLogId) throws Exception;
//    
//    /**
//     * 更新当前linkId关联的附件信息
//     * @param linkId
//     * @param attachIds
//     * @throws Exception
//     */
//    public void updateAttachByLinkId(String linkId, List<String> attachIds) throws Exception;
//
//    /**
//     * 删除formLogId关联的附件
//     * 
//     * @param formLogId
//     * @return
//     */
//    public int clearAttachsByHashMap(@SuppressWarnings("rawtypes") Map map);
//
// 
//
//    // 删除附件通过ids
//    public void delbyattachids(String ids);

    // 根据关联ID删除附件
    public void delattachbylinkid(String linkid);

//    // ----------------------------------------新方法
//    /**
//     * 新增附件数据逻辑
//     */
//    public List<WhAttachements> insertWhAttachementsList(List<WhAttachements> WhAttachementsList);
}
