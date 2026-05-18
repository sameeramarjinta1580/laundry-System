package com.laundry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.laundry.dto.request.BranchRequest;
import com.laundry.dto.response.BranchResponse;
import com.laundry.service.BranchService;

@RestController
@RequestMapping("/branch")
@CrossOrigin
public class BranchController {

    @Autowired
    private BranchService service;

    // ✅ ADD
    @PostMapping("/add")
    public BranchResponse add(@RequestBody BranchRequest req) {
        return service.addBranch(req);
    }

    // ✅ GET ALL
    @GetMapping("/all")
    public List<BranchResponse> all() {
        return service.getAllBranches();
    }

    // ✅ GET ACTIVE
    @GetMapping("/active")
    public List<BranchResponse> active() {
        return service.getActiveBranches();
    }

    // ✅ GET BY CITY
    @GetMapping("/city/{city}")
    public List<BranchResponse> byCity(@PathVariable String city) {
        return service.getByCity(city);
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public BranchResponse byId(@PathVariable Long id) {
        return service.getById(id);
    }

    // ✅ UPDATE
    @PutMapping("/update/{id}")
    public BranchResponse update(@PathVariable Long id,
                                 @RequestBody BranchRequest req) {
        return service.updateBranch(id, req);
    }

    // ✅ DELETE
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return service.deleteBranch(id);
    }
}