package com.admin.details.admindetails.AdminService;

import java.util.List;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Service;

import com.admin.details.admindetails.AdminException.AdminException;
import com.admin.details.admindetails.adminPackage.AdminDetails;

@Service
public interface AdminService {
	
	public AdminDetails createAdminInfo(AdminDetails e) throws AdminException;
	
	public List<AdminDetails> getAllAdmin();

	public AdminDetails getSingleAdmin(String id) throws AdminException;

	public void updateAdmin(String id, AdminDetails e) throws AdminException;

	public void deleteAdminById(String id) throws AdminException;
	

}
//


