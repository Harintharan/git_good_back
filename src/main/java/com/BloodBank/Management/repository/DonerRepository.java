package com.BloodBank.Management.repository;

import com.BloodBank.Management.entity.Doner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonerRepository extends JpaRepository<Doner,Long> {


}
