package com.wh.service.wmh.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.base.PageBounds;
import com.wh.dao.wmh.WmhFaultRepairMapper;
import com.wh.entity.WhAttachements;
import com.wh.entity.WmhFaultRepair;
import com.wh.entity.WmhUser;
import com.wh.service.jzxx.WhAttachementsService;
import com.wh.service.wmh.WmhFaultRepairService;
import com.wh.util.UUIDUtil;

@Service
public class WmhFaultRepairServiceImpl implements WmhFaultRepairService {

	@Autowired
	WmhFaultRepairMapper wmhFaultRepairMapper;
	
	@Autowired
	WhAttachementsService whAttachementsService;
	
	@Override
	public String faultRepair(WmhFaultRepair wmhFaultRepair,WmhUser user){
		
		String id = UUIDUtil.getUUID();
		wmhFaultRepair.setId(id);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String currentDate = sdf.format(new Date());
		wmhFaultRepair.setCreateTime(currentDate);
		if (user!=null) {
			
			wmhFaultRepair.setOpUserId(user.getId());
		}
		wmhFaultRepair.setStatus(0);
		
		int i = wmhFaultRepairMapper.insertSelective(wmhFaultRepair);
		if(i != 1){
			return "故障报修数据处理失败！";
		}
		
		saveImg(wmhFaultRepair,id);
		
		return "";
		
	}
	
	private void saveImg(WmhFaultRepair wmhFaultRepair,String id){
		String uploadUrl = null;
		List<String> fileList = wmhFaultRepair.getFileList();
		if (fileList != null && fileList.size() > 0) {
			for (String aFileList : fileList) {
				uploadUrl = aFileList;
				String newName = uploadUrl.substring(uploadUrl.lastIndexOf(".") + 1).toLowerCase();
				WhAttachements whAttachements = new WhAttachements();
				whAttachements.setId(UUIDUtil.getUUID());
				whAttachements.setLinkId(id);
				whAttachements.setFileName(newName);
				whAttachements.setFilePath(uploadUrl);
				whAttachements.setCreateTime(new Date());
				this.whAttachementsService.save(whAttachements);
			}
		}
	}

	@Override
	public List<Map<String, Object>> findList(Map<String, Object> map, PageBounds pageBounds) {
		
		return wmhFaultRepairMapper.findList(map,pageBounds);
	}

	@Override
	public int countList() {
	
		return wmhFaultRepairMapper.countList();
	}

	@Override
	public List<String> getPicPathByLinkId(String malId) {
	
		return wmhFaultRepairMapper.getPicPathByLinkId(malId);
	}


}
