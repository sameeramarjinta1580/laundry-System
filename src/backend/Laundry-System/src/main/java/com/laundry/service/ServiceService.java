package com.laundry.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laundry.dto.request.ServiceRequest;
import com.laundry.dto.response.ServiceResponse;
import com.laundry.entity.Branch;
import com.laundry.entity.ServiceEntity;
import com.laundry.repository.BranchRepo;
import com.laundry.repository.ServiceRepository;

@Service
public class ServiceService {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private ServiceRepository repo;

    @Autowired
    private BranchRepo branchRepo; // ⭐ NEW

    // ✅ ADD SERVICE (Admin)
    public ServiceResponse addService(ServiceRequest req) throws IOException {

        if (req.getBranchId() == null) {
            throw new RuntimeException("Branch ID is required");
        }

        File folder = new File(UPLOAD_DIR);
        if (!folder.exists()) folder.mkdirs();

        String fileName = System.currentTimeMillis() + "_" +
                req.getImage().getOriginalFilename();

        Path path = Paths.get(UPLOAD_DIR + fileName);
        Files.write(path, req.getImage().getBytes());

        // ✅ SAFE NOW
        Branch branch = branchRepo.findById(req.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        ServiceEntity s = new ServiceEntity();
        s.setName(req.getName());
        s.setDescription(req.getDescription());
        s.setPricePerItem(req.getPricePerItem());
        s.setImageUrl(fileName);
        s.setBranch(branch);

        return mapToResponse(repo.save(s));
    }
    // ✅ GET ALL SERVICES
    public List<ServiceResponse> getAllServices() {

        return repo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ✅ UPDATE SERVICE
    public ServiceResponse updateService(Long id, ServiceRequest req) throws IOException {

        ServiceEntity service = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        service.setName(req.getName());
        service.setDescription(req.getDescription());
        service.setPricePerItem(req.getPricePerItem());

        // 🔥 Update branch if changed
        if (req.getBranchId() != null) {
            Branch branch = branchRepo.findById(req.getBranchId())
                    .orElseThrow(() -> new RuntimeException("Branch not found"));
            service.setBranch(branch);
        }

        // 👉 If new image uploaded
        if (req.getImage() != null && !req.getImage().isEmpty()) {

            String fileName = System.currentTimeMillis() + "_" +
                    req.getImage().getOriginalFilename();

            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.write(path, req.getImage().getBytes());

            service.setImageUrl(fileName);
        }

        ServiceEntity updated = repo.save(service);

        return mapToResponse(updated);
    }

    // ✅ DELETE
    public String deleteService(Long id) {

        ServiceEntity service = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        repo.delete(service);

        return "Service Deleted Successfully";
    }

    // ✅ GET BY ID
    public ServiceResponse getServiceById(Long id) {

        ServiceEntity s = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        return mapToResponse(s);
    }

    // ✅ GET SERVICES BY BRANCH
    public List<ServiceResponse> getServicesByBranch(Long branchId) {
        return repo.findByBranchId(branchId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ✅ GET SERVICES BY CITY
    public List<ServiceResponse> getServicesByCity(String city) {
        return repo.findByBranchCityAndBranchActiveTrue(city)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // 🔥 COMMON MAPPER (THIS WAS MISSING ❗)
    private ServiceResponse mapToResponse(ServiceEntity s) {

        ServiceResponse res = new ServiceResponse();

        res.setId(s.getId());
        res.setName(s.getName());
        res.setDescription(s.getDescription());
        res.setPricePerItem(s.getPricePerItem());
        res.setImageUrl("http://localhost:8080/" + s.getImageUrl());

        // ⭐ Branch details
        if (s.getBranch() != null) {
            res.setBranchId(s.getBranch().getId());
            res.setBranchName(s.getBranch().getName());
            res.setCity(s.getBranch().getCity());
        }

        return res;
    }
}