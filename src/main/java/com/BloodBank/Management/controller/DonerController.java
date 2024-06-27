package com.BloodBank.Management.controller;

import com.BloodBank.Management.entity.Doner;


import com.BloodBank.Management.exception.DonerNotFoundException;
import com.BloodBank.Management.repository.DonerRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")

public class DonerController {


    @Autowired
    private DonerRepository donerRepository;

    @PostMapping("/doner")
    Doner newDoner(@RequestBody Doner newDoner){
        return donerRepository.save(newDoner);
    }

    @GetMapping("/doners")
    List<Doner> getAllUsers(){
        return donerRepository.findAll();
    }

    @GetMapping("/doner/{id}")
      Doner getDonerbyId(@PathVariable Long id){
        return donerRepository.findById(id)
                .orElseThrow(()-> new DonerNotFoundException(id));
    }

    @PutMapping("/doner/{id}")
    Doner updateDoner(@RequestBody Doner newDoner,@PathVariable Long id )
    {

        return donerRepository.findById (id)
                .map(doner -> {
                    doner.setIdenty_number(newDoner.getIdenty_number());
                    doner.setDonate_count(newDoner.getDonate_count());
                    doner.setFirst_name(newDoner.getFirst_name());
                    doner.setLast_name(newDoner.getLast_name());
                    doner.setAge(newDoner.getAge());
                    doner.setBlood_group(newDoner.getBlood_group());
                    doner.setHome_no(newDoner.getHome_no());
                    doner.setStreet_name(newDoner.getStreet_name());
                    doner.setCity_name(newDoner.getCity_name());
                    doner.setDistrict_name(newDoner.getCity_name());
                    doner.setContact_number(newDoner.getContact_number());
                    return donerRepository.save(doner);
                }) .orElseThrow(()->new DonerNotFoundException(id));
    }

    @DeleteMapping("/doner/{id}")
    String  deleteDoner(@PathVariable Long id)
    {
        if (!donerRepository.existsById(id))
        {
            throw new DonerNotFoundException(id);
        }
        donerRepository.deleteById(id);
        return "Doner with identy_number"+id+"has been deleted success." ;




    }







}
