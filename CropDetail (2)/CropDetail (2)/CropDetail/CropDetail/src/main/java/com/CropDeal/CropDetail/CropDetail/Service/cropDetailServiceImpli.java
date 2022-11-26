package com.CropDeal.CropDetail.CropDetail.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CropDeal.CropDetail.CropDetail.Exception.CropDetailException;
import com.CropDeal.CropDetail.CropDetail.Repo.cropDetailRepository;
import com.CropDeal.CropDetail.CropDetail.model.cropDetail;

@Service
public class cropDetailServiceImpli implements CropDetailService {
	
	@Autowired
	private cropDetailRepository cdr;

	@Override
	public cropDetail createCropDetail(cropDetail cd) throws CropDetailException {
		// TODO Auto-generated method stub
		Optional<cropDetail> farmerOptional=cdr.findByCropName(cd.getType());
		if(farmerOptional.isPresent()) {
			throw new CropDetailException(CropDetailException.cropAlreadyExists());
		}
		else {
			return cdr.save(cd);
		}
		
	}

	@Override
	public List<cropDetail> getAllCropDetail() {
		// TODO Auto-generated method stub
		List<cropDetail> f=cdr.findAll();
		if(f.size()>0) {
			return f;
		}
		else {
			return new ArrayList<cropDetail>();
		}
	}

	@Override
	public cropDetail getSingleCropDetail(String id) throws CropDetailException {
		// TODO Auto-generated method stub
		Optional<cropDetail> optionalcrop=cdr.findById(id);
		if(!optionalcrop.isPresent()) {
			throw new CropDetailException(CropDetailException.NotFoundException(id));
		}
		else {
			return optionalcrop.get();
		}
	}

	@Override
	public void updateCropDetail(String id, cropDetail cd) throws CropDetailException {
		// TODO Auto-generated method stub
Optional<cropDetail> optionalcrop=cdr.findById(id);
		
		if( optionalcrop.isPresent()) {
			
			
			
			cropDetail cropToUpdate= optionalcrop.get();
			cropToUpdate.setType(cd.getType());
			cropToUpdate.setQuantity(cd.getQuantity());
			cropToUpdate.setAddress(cd.getAddress());
			cdr.save(cropToUpdate);
			
		}else {
			throw new CropDetailException(CropDetailException.NotFoundException(id));
		}
		
	}

	@Override
	public void deleteCropDetailById(String id) throws CropDetailException {
		// TODO Auto-generated method stub
		Optional<cropDetail> optionalcrop=cdr.findById(id);
		if(!optionalcrop.isPresent()) {
			throw new CropDetailException(CropDetailException.NotFoundException(id));
		}
		else {
			cdr.deleteById(id);
		}
	}

	
}
