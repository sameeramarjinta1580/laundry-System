package com.laundry.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.laundry.dto.request.ServiceRequest;
import com.laundry.dto.response.ServiceResponse;
import com.laundry.service.ServiceService;

@RestController
@RequestMapping("/service")
@CrossOrigin
public class ServiceController {

    @Autowired
    private ServiceService service;

    // ✅ ADD SERVICE
    @PostMapping("/add")
    public ResponseEntity<ServiceResponse> addService(@ModelAttribute ServiceRequest req) throws IOException {
        return ResponseEntity.ok(service.addService(req));
    }

    // ✅ GET ALL SERVICES
    @GetMapping("/all")
    public ResponseEntity<List<ServiceResponse>> getAllServices() {
        return ResponseEntity.ok(service.getAllServices());
    }

    // ✅ GET SERVICE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getServiceById(id));
    }

    // ✅ GET SERVICES BY BRANCH
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<ServiceResponse>> getServicesByBranch(@PathVariable Long branchId) {
        return ResponseEntity.ok(service.getServicesByBranch(branchId));
    }

    // ✅ GET SERVICES BY CITY
    @GetMapping("/city/{city}")
    public ResponseEntity<List<ServiceResponse>> getServicesByCity(@PathVariable String city) {
        return ResponseEntity.ok(service.getServicesByCity(city));
    }

    // ⭐ OPTIONAL: Handle "no services found" case cleanly
    @GetMapping("/city/{city}/available")
    public ResponseEntity<?> getServicesIfAvailable(@PathVariable String city) {
        List<ServiceResponse> list = service.getServicesByCity(city);

        if (list.isEmpty()) {
            return ResponseEntity.ok("No services available in your city");
        }

        return ResponseEntity.ok(list);
    }

    // ✅ UPDATE SERVICE
    @PutMapping("/update/{id}")
    public ResponseEntity<ServiceResponse> updateService(@PathVariable Long id,
                                                         @ModelAttribute ServiceRequest req) throws IOException {
        return ResponseEntity.ok(service.updateService(id, req));
    }

    // ✅ DELETE SERVICE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteService(id));
    }
}