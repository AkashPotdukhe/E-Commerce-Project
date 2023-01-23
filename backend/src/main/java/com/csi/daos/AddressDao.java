package com.csi.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csi.utils.entities.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer> {

}
