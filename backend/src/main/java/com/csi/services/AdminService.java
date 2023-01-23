package com.csi.services;

import com.csi.utils.entities.Admin;

public interface AdminService {
	Admin validate(String userid, String pwd);
	void updateAdmin(Admin amin);
	long countAdmin();
}
