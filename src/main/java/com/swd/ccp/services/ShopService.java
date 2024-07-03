package com.swd.ccp.services;

import com.swd.ccp.DTO.request_models.CreateShopRequest;
import com.swd.ccp.DTO.request_models.UpdateShopRequest;
import com.swd.ccp.DTO.response_models.ResponseObject;
import com.swd.ccp.DTO.response_models.ShopResponse;

import java.util.List;

public interface ShopService {
    ResponseObject CreateShop (CreateShopRequest createShopRequest);
    ResponseObject updateShop(UpdateShopRequest updateShopRequest);
    ResponseObject changeShopStatus(Integer id, String status);
    List<ShopResponse> getAllShops(String name);
    List<ShopResponse> getAllActiveShops(String name);
}
