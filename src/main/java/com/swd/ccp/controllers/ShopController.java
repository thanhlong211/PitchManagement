package com.swd.ccp.controllers;

import com.swd.ccp.DTO.request_models.CreateShopRequest;
import com.swd.ccp.DTO.request_models.SearchRequest;
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

    @PostMapping("/owner/create_shop")
    public ResponseEntity<ResponseObject> createShop(@RequestBody CreateShopRequest createShopRequest) {
        ResponseObject response = shopService.CreateShop(createShopRequest);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    @PutMapping("/owner/update_shop")
    public ResponseEntity<ResponseObject> updateShop(@RequestBody UpdateShopRequest updateShopRequest) {
        ResponseObject response = shopService.updateShop(updateShopRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/owner/activate/{id}")
    public ResponseEntity<ResponseObject> activateShop(@PathVariable Integer id) {
        ResponseObject response = shopService.changeShopStatus(id, "ACTIVE");
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/owner/deactivate/{id}")
    public ResponseEntity<ResponseObject> deactivateShop(@PathVariable Integer id) {
        ResponseObject response = shopService.changeShopStatus(id, "INACTIVE");
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/owner/all")
    public ResponseEntity<List<ShopResponse>> getAllShops(@RequestBody SearchRequest searchRequest) {
        List<ShopResponse> shops = shopService.getAllShops(searchRequest.getSearch());
        return ResponseEntity.ok(shops);
    }
    @GetMapping("/customer/shops/active")
    public ResponseEntity<List<ShopResponse>> getAllActiveShops(
            @RequestParam(defaultValue = "", name = "search") String search) {
        List<ShopResponse> activeShops = shopService.getAllActiveShops(search);
        return ResponseEntity.ok(activeShops);
    }
}
