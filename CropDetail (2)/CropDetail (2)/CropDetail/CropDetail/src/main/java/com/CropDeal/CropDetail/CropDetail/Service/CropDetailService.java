package com.CropDeal.CropDetail.CropDetail.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.CropDeal.CropDetail.CropDetail.Exception.CropDetailException;
import com.CropDeal.CropDetail.CropDetail.model.cropDetail;

@Service
public interface CropDetailService {
	public cropDetail createCropDetail(cropDetail cd) throws CropDetailException;
	public List<cropDetail> getAllCropDetail();
		
		public cropDetail getSingleCropDetail(String id) throws CropDetailException;
		
		public void updateCropDetail(String id,cropDetail cd) throws CropDetailException;
		
		public void deleteCropDetailById(String id) throws CropDetailException;

}
