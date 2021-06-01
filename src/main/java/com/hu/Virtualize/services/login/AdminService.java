package com.hu.Virtualize.services.login;

import com.hu.Virtualize.entities.AdminEntity;

public interface AdminService {
    AdminEntity getAdminById(Long id);
}
