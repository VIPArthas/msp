package com.wh.service.jzxx.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.dao.jzxx.WhAttachementsMapper;
import com.wh.entity.JzEvaluate;
import com.wh.entity.WhAttachements;
import com.wh.service.jzxx.WhAttachementsService;
import com.wh.util.DateUtil;

@Service
public class WhAttachementsServiceImpl implements WhAttachementsService {

    @Autowired
    private WhAttachementsMapper whAttachementsMapper;

    @Override
    public void save(WhAttachements attchmentInfo) {
        whAttachementsMapper.insertSelective(attchmentInfo);
    }

    @Override
    public void update(WhAttachements attchmentInfo) {
        whAttachementsMapper.updateByPrimaryKeySelective(attchmentInfo);
    }

    @Override
    public void delete(WhAttachements attchmentInfo) {
        whAttachementsMapper.deleteByPrimaryKey(attchmentInfo.getId());

    }

//    @Override
//    public WhAttachements findById(String attchId) {
//        return whAttachementsMapper.findeById(attchId);
//
//    }

//    @Override
//    public List<WhAttachements> queryAttachsById(String linkId) {
//        return whAttachementsMapper.queryAttachsById(linkId);
//    }
//
//    @Override
//    public int clearAttachsByFormId(String formLogId) throws Exception{
//        return whAttachementsMapper.clearAttachsByFormId(formLogId);
//    }
//    
//    @Transactional
//    @Override
//    public void updateAttachByLinkId(String linkId, List<String> attachIds) throws Exception{
//    	if(null != attachIds && attachIds.size() > 0){
//    		//查出当前的linkId现有的attachList
//        	List<WhAttachements> attachList = this.queryAttachsById(linkId);
//        	if(null != attachList && attachList.size() > 0){
//        		//将不存在在attachIds中的attach删掉
//            	//将新增的attachIds更新linkId
//        		List<WhAttachements> attachListDel = new ArrayList<WhAttachements>();
//        		for(int i=0;i<attachList.size();i++){
//        			WhAttachements temp = attachList.get(i);
//        			String attachId1 = temp.getAttachmentId();
//        			boolean flag = false;
//        			for(int j=0;j<attachIds.size();j++){
//        				String attachId2 = attachIds.get(j);
//        				if(!StringUtils.isEmpty(attachId2) && attachId1.equals(attachId2)){
//        					flag = true;
//        					break;
//        				}
//        			}
//        			if(!flag){
//        				attachListDel.add(temp);//需要删除的附件
//        			}
//        		}
//        		for(int i=0;i<attachIds.size();i++){
//        			String attachId = attachIds.get(i);
//        			WhAttachements tempAdd = new WhAttachements();
//        			tempAdd.setAttachmentId(attachId);
//        			tempAdd.setLinkId(linkId);
//        			this.update(tempAdd);
//        		}
//        		for(int i=0;i<attachListDel.size();i++){
//        			WhAttachements temp = attachListDel.get(i);
//        			WhAttachements tempDel = new WhAttachements();
//        			tempDel.setAttachmentId(temp.getAttachmentId());
//        			this.delete(tempDel);
//        		}
//        	}else{
//        		for(int j = 0;j<attachIds.size();j++){
//    				String attachId = attachIds.get(j);
//    				if(!StringUtils.isEmpty(attachId)){
//    					WhAttachements attach = new WhAttachements();
//            			attach.setAttachmentId(attachId);
//            			attach.setLinkId(linkId);
//            			attach.setLinkType("1");
//            			this.update(attach);
//               		 }
//    			}
//        	}
//    	}
//    }
//    
//    @Override
//    public int clearAttachsByHashMap(@SuppressWarnings("rawtypes") Map map) {
//        return whAttachementsMapper.clearAttachsByHashMap(map);
//    }
//
//   
//    @Override
//    public void delbyattachids(String ids) {
//        String[] idsarray = ids.split(",");
//        if (idsarray.length > 0) {
//            for (int i = 0; i < idsarray.length; i++) {
//                this.whAttachementsMapper.delattach(idsarray[i]);
//            }
//        } else {
//            this.whAttachementsMapper.delattach(ids);
//        }
//    }

    @Override
    public void delattachbylinkid(String linkid) {
        List<WhAttachements> list = this.whAttachementsMapper.queryAttachsById(linkid);
        for (WhAttachements a : list) {
            this.whAttachementsMapper.deleteByPrimaryKey(a.getId());
        }
    }

   /* @Transactional
    @Override
    public List<WhAttachements> insertWhAttachementsList(List<WhAttachements> WhAttachementsList) {
        whAttachementsMapper.insertWhAttachementsList(WhAttachementsList);
        return WhAttachementsList;
    }*/
    

    @Override
    public void setAttachsEvalute(JzEvaluate jzEvaluate, String pathPix) {
        try {
            List<WhAttachements> attachs = jzEvaluate.getWhAttachementsList();
            List<WhAttachements> newAttachs = new ArrayList<WhAttachements>();
            String path = "/resource/upload/avatarPc/" + DateUtil.formatDate(new Date(), "yyyyMM") + "/";
            File uploadDir = new File(pathPix + path);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            if (attachs != null && attachs.size() > 0) {
                for (WhAttachements WhAttachements : attachs) {
                    String filePath = WhAttachements.getFilePath();
                    String fileType = WhAttachements.getFilePath();
                    if (filePath != null && !filePath.equals("") && filePath.length() > 0 && fileType != null && !fileType.equals("")
                        && fileType.length() > 0) {
                        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1).toLowerCase();
                        String fileSmall = WhAttachements.getFileSize();
                        WhAttachements attach = new WhAttachements();
                        attach.setFileName(fileName);
                        if (filePath.indexOf("/upload/temporary/") != -1) {
                            File newFile = new File(pathPix + filePath);
                            if (newFile.exists()) {
                                FileUtils.copyFileToDirectory(newFile, uploadDir, false);
                                newFile.delete();
                            }
                            attach.setFilePath(filePath.replace("temporary", "avatarPc"));
                        } else {
                            attach.setFilePath(filePath);
                        }
                        if (fileSmall != null && !fileSmall.equals("") && fileSmall.length() > 0) {
                            if (fileSmall.indexOf("/upload/temporary/") != -1) {
                                File newFileSmall = new File(pathPix + fileSmall);
                                if (newFileSmall.exists()) {
                                    FileUtils.copyFileToDirectory(newFileSmall, uploadDir, false);
                                    newFileSmall.delete();
                                }
                                attach.setFilePath(fileSmall.replace("temporary", "avatarPc"));
                            } else {
                                attach.setFileSize(fileSmall);
                            }
                        }
                        attach.setFileType(fileType);
                        attach.setCreateTime(new Date());
                        newAttachs.add(attach);
                    }
                }
            }
            // 根据上传图片list设置attach字段值
            if (newAttachs != null && newAttachs.size() > 0) {
                jzEvaluate.setWhAttachementsList(newAttachs);
            }
         
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public WhAttachements findById(String attchId) {
		
		return whAttachementsMapper.selectByPrimaryKey(attchId);
	}

	@Override
	public List<WhAttachements> queryAttachsById(String linkId) {
		return whAttachementsMapper.queryAttachsById(linkId);
	}
	
	@Override
	public List<Map<String, Object>> getFilePathByLinkId(String linkId) {
		return whAttachementsMapper.getFilePathByLinkId(linkId);
	}
}
