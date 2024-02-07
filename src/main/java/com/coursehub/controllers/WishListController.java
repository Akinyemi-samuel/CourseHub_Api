package com.coursehub.controllers;


import com.coursehub.dto.request.WishListDto;
import com.coursehub.model.WishList;
import com.coursehub.service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin()
public class WishListController {

    private final WishlistService wishlistService;

    public WishListController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public String addProductToWishList(@RequestBody WishListDto wishListDto) throws Exception {
        wishlistService.addCourseToWishListByUser(wishListDto);
        return "Course Added to Wishlist successfully";
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("{userId}")
    public List<WishList> getAllProductByUserWishListId(@PathVariable(value = "userId") Long userId) {
        return wishlistService.getAllCourseByUserWishListId(userId);
    }
}