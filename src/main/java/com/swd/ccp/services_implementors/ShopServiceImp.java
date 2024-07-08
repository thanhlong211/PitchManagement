package com.swd.ccp.services_implementors;

import com.swd.ccp.DTO.request_models.CreateShopRequest;
import com.swd.ccp.DTO.request_models.UpdateShopRequest;
import com.swd.ccp.DTO.response_models.ResponseObject;
import com.swd.ccp.DTO.response_models.ShopResponse;
import com.swd.ccp.mapper.ShopMapper;
import com.swd.ccp.models.entity_models.Account;
import com.swd.ccp.models.entity_models.Manager;
import com.swd.ccp.models.entity_models.Shop;
import com.swd.ccp.repositories.AccountRepo;
import com.swd.ccp.repositories.ManagerRepo;
import com.swd.ccp.repositories.ShopRepo;
import com.swd.ccp.services.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopServiceImp implements ShopService {
    private final ShopRepo shopRepo;
    private final ManagerRepo managerRepo;
    private final AccountRepo accountRepo;

    @Override
    public ResponseObject CreateShop(CreateShopRequest createShopRequest) {
        // Validate shop name
        if (shopRepo.findAllByName(createShopRequest.getName()).isPresent()) {
            return ResponseObject.builder()
                    .message("Shop name already exists")
                    .statusCode(400)
                    .build();
        }

        // Validate shop name is not null
        if (createShopRequest.getName() == null) {
            return ResponseObject.builder()
                    .message("Shop name cannot be null")
                    .statusCode(400)
                    .build();
        }

        // Build and save the shop entity
        Shop shop = Shop.builder()
                .avatar(createShopRequest.getAvatar())
                .name(createShopRequest.getName())
                .phone(createShopRequest.getPhone())
                .address(createShopRequest.getAddress())
                .openTime(createShopRequest.getOpenTime())
                .closeTime(createShopRequest.getCloseTime())
                .status("ACTIVE")
                .build();
        Shop savedShop = shopRepo.save(shop);

        // Fetch the account of the owner
        Optional<Account> accountOptional = accountRepo.findById(createShopRequest.getOwner_id());
        if (!accountOptional.isPresent()) {
            return ResponseObject.builder()
                    .message("Owner account not found")
                    .statusCode(404)
                    .build();
        }
        Account ownerAccount = accountOptional.get();

        // Create and save the manager entity
        Manager manager = new Manager();
        manager.setShop(savedShop);
        manager.setAccount(ownerAccount);
        managerRepo.save(manager);

        return ResponseObject.builder()
                .message("Create Shop Success")
                .statusCode(200)
                .build();

    }

    @Override
    public ResponseObject updateShop(UpdateShopRequest updateShopRequest) {
        // Find the shop by ID
        Optional<Shop> existingShopOptional = shopRepo.findById(updateShopRequest.getId());
        if (!existingShopOptional.isPresent()) {
            return ResponseObject.builder()
                    .message("Shop not found")
                    .statusCode(404)
                    .build();
        }

        // Update existing shop entity
        Shop existingShop = existingShopOptional.get();
        existingShop.setAvatar(updateShopRequest.getAvatar());
        existingShop.setName(updateShopRequest.getName());
        existingShop.setPhone(updateShopRequest.getPhone());
        existingShop.setAddress(updateShopRequest.getAddress());
        existingShop.setOpenTime(updateShopRequest.getOpenTime());
        existingShop.setCloseTime(updateShopRequest.getCloseTime());

        // Save the updated shop entity
        shopRepo.save(existingShop);

        return ResponseObject.builder()
                .message("Update Shop Success")
                .statusCode(200)
                .build();
    }
    @Override
    public ResponseObject changeShopStatus(Integer id, String status) {
        // Find the shop by ID
        Optional<Shop> existingShopOptional = shopRepo.findById(id);
        if (!existingShopOptional.isPresent()) {
            return ResponseObject.builder()
                    .message("Shop not found")
                    .statusCode(404)
                    .build();
        }

        Shop existingShop = existingShopOptional.get();
        existingShop.setStatus(status);

        // Save the updated shop entity
        shopRepo.save(existingShop);

        return ResponseObject.builder()
                .message("Change Shop Status Success")
                .statusCode(200)
                .build();
    }
    @Override
    public List<ShopResponse> getAllShops(String search) {
        List<Shop> shops;

        if (search != null && !search.isBlank()) {
            shops = shopRepo.findAllByNameContainingIgnoreCase(search);
        } else {
            shops = shopRepo.findAll();
        }

        return mapShopsToDTOs(shops);
    }

    @Override
    public List<ShopResponse> getAllActiveShops(String search) {
        List<Shop> activeShops;

        if (search != null && !search.isBlank()) {
            activeShops = shopRepo.findAllByNameContainingIgnoreCaseAndStatus(search, "ACTIVE");
        } else {
            activeShops = shopRepo.findByStatus("ACTIVE");
        }

        return mapShopsToDTOs(activeShops);
    }

    private List<ShopResponse> mapShopsToDTOs(List<Shop> shops) {
        return shops.stream()
                .map(ShopMapper::shopToDTO)
                .collect(Collectors.toList());
    }
}

