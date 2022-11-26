package com.admin.details.admindetails.AdminService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Service;

import com.admin.details.admindetails.AdminException.AdminException;
import com.admin.details.admindetails.AdminRepository.AdminRepository;
import com.admin.details.admindetails.adminPackage.AdminDetails;

@Service
public class AdminServiceImpli implements AdminService {
	
	@Autowired
	private AdminRepository admr;


	@Override
	public List<AdminDetails> getAllAdmin() {
		// TODO Auto-generated method stub
		List<AdminDetails> adm=admr.findAll();
		if(adm.size()>0) {
			return adm;
		}
		else {
			return new ArrayList<AdminDetails>();
		}
	}

	@Override
	public AdminDetails getSingleAdmin(String id) throws AdminException {
		// TODO Auto-generated method stub
		Optional<AdminDetails> optionalAdmin=admr.findById(id);
		if(!optionalAdmin.isPresent()) {
			throw new AdminException(AdminException.NotFoundException(id));
		}
		else {
			return optionalAdmin.get();
		}
	}

	@Override
	public void updateAdmin(String id, AdminDetails e) throws AdminException {
Optional<AdminDetails> optionalAdmin=admr.findById(id);
		
		if(optionalAdmin.isPresent()) {
			
			
			
			AdminDetails AdminToUpdate=optionalAdmin.get();
			AdminToUpdate.setName(e.getName());
			AdminToUpdate.setEmail(e.getEmail());
			AdminToUpdate.setAddress(e.getAddress());
			AdminToUpdate.setMobileNo(e.getMobileNo());
			AdminToUpdate.setPassword(e.getPassword());
			admr.save(AdminToUpdate);
			
		}else {
			throw new AdminException(AdminException.NotFoundException(id));
		}
		
	}

	@Override
	public void deleteAdminById(String id) throws AdminException {
		// TODO Auto-generated method stub
		Optional<AdminDetails> optionalAdmin=admr.findById(id);
		if(!optionalAdmin.isPresent()) {
			throw new AdminException(AdminException.NotFoundException(id));
		}
		else {
			admr.deleteById(id);
		}
		
	}

	@Override
	public AdminDetails createAdminInfo(AdminDetails e) throws AdminException {
		// TODO Auto-generated method stub
		Optional<AdminDetails> AdminOptional=admr.findByAdminName(e.getName());
		if(AdminOptional.isPresent()) {
			throw new AdminException(AdminException.AdminAlreadyExists());
		}
		else {
			return admr.save(e);
		}
	}

	

	

}
