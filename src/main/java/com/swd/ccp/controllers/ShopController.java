package com.swd.ccp.controllers;

import com.swd.ccp.DTO.request_models.CreateShopRequest;
import com.swd.ccp.DTO.request_models.UpdateShopRequest;
import com.swd.ccp.DTO.response_models.ResponseObject;
import com.swd.ccp.DTO.response_models.ShopResponse;
import com.swd.ccp.services.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @PostMapping()
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<ResponseObject> createShop(@RequestBody CreateShopRequest createShopRequest) {
        ResponseObject response = shopService.CreateShop(createShopRequest);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<ResponseObject> updateShop(@RequestBody UpdateShopRequest updateShopRequest) {
        ResponseObject response = shopService.updateShop(updateShopRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/activate/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<ResponseObject> activateShop(@PathVariable Integer id) {
        ResponseObject response = shopService.changeShopStatus(id, "ACTIVE");
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/deactivate/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<ResponseObject> deactivateShop(@PathVariable Integer id) {
        ResponseObject response = shopService.changeShopStatus(id, "INACTIVE");
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/shops")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<ShopResponse>> getAllShops(
            @RequestParam(defaultValue = "", name = "search") String search) {
        List<ShopResponse> shops = shopService.getAllShops(search);
        return ResponseEntity.ok(shops);
    }

    @GetMapping("/shops/active")
    @PreAuthorize("hasAuthority('customer:read')")
    public ResponseEntity<List<ShopResponse>> getAllActiveShops(
            @RequestParam(defaultValue = "", name = "search") String search) {
        List<ShopResponse> activeShops = shopService.getAllActiveShops(search);
        return ResponseEntity.ok(activeShops);
    }
}
