package com.laundry.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laundry.dto.request.BranchRequest;
import com.laundry.dto.response.BranchResponse;
import com.laundry.entity.Branch;
import com.laundry.repository.BranchRepo;

@Service
public class BranchService {

    @Autowired
    private BranchRepo repo;

    // ✅ ADD
    public BranchResponse addBranch(BranchRequest req) {

        Branch branch = new Branch();
        branch.setName(req.getName());
        branch.setCity(req.getCity());
        branch.setAddress(req.getAddress());
        branch.setOpeningTime(req.getOpeningTime());
        branch.setClosingTime(req.getClosingTime());

        // ✅ HANDLE NULL (IMPORTANT FIX)
        branch.setActive(req.getActive() != null ? req.getActive() : true);

        return mapToResponse(repo.save(branch));
    }

    // ✅ GET ALL
    public List<BranchResponse> getAllBranches() {
        return repo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ✅ GET ACTIVE ONLY
    public List<BranchResponse> getActiveBranches() {
        return repo.findByActiveTrue()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ✅ GET BY CITY
    public List<BranchResponse> getByCity(String city) {
        return repo.findByCityAndActiveTrue(city)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ✅ GET BY ID
    public BranchResponse getById(Long id) {
        Branch branch = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found"));
        return mapToResponse(branch);
    }

    // ✅ UPDATE
    public BranchResponse updateBranch(Long id, BranchRequest req) {

        Branch branch = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        branch.setName(req.getName());
        branch.setCity(req.getCity());
        branch.setAddress(req.getAddress());
        branch.setOpeningTime(req.getOpeningTime());
        branch.setClosingTime(req.getClosingTime());

        // ✅ KEEP OLD VALUE IF NULL
        branch.setActive(req.getActive() != null ? req.getActive() : branch.getActive());

        return mapToResponse(repo.save(branch));
    }

    // ✅ DELETE
    public String deleteBranch(Long id) {
        repo.deleteById(id);
        return "Branch deleted successfully";
    }

    // 🔁 MAPPER
    private BranchResponse mapToResponse(Branch b) {

        BranchResponse res = new BranchResponse();

        res.setId(b.getId());
        res.setName(b.getName());
        res.setCity(b.getCity());
        res.setAddress(b.getAddress());
        res.setOpeningTime(b.getOpeningTime());
        res.setClosingTime(b.getClosingTime());
        res.setActive(b.getActive());

        res.setTotalServices(
                b.getServices() != null ? b.getServices().size() : 0
        );

        return res;
    }
}