package com.grupo4.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.grupo4.models.Review;

public class ReviewControllerTest {

    private ReviewController reviewController;
    private List<Review> reviews;
    private String store_id;


    @BeforeEach
    void setUp() {

        store_id = "12";
        reviewController = ReviewController.getInstance(store_id);
        reviews = new ArrayList<>();
        reviews.add(new Review("3", "blusa sea", "2", "joyce", "25", "gostei muito!", 11));
        reviews.add(new Review("4", "blusa moon", "3", "joyce", "25", "bonita!", 12));
        reviewController.setReviews(reviews);
    }

    @Test
    void testReview_DELETE() {
    
        int size = reviewController.review_GET_ALL().size();
        String id = reviewController.review_GET_ALL().stream().findFirst().get().getId();

        reviewController.review_DELETE(id);
        List<Review> reviews = reviewController.review_GET_ALL();

        Assertions.assertTrue(reviews.size() < size);
        assertFalse(reviews.stream().anyMatch(review -> review.getId().equals(id)));
    }

    @Test
    void testReview_GET_ALL() {
        reviews = reviewController.review_GET_ALL();
        assertNotNull(reviews);
    }

    @Test
    void testReview_GET_BY_ID() {
        String id = reviewController.review_GET_ALL().stream().findFirst().get().getId();
        Review expectedProduct = reviewController.review_GET_BY_ID(id);
        assertEquals(id, expectedProduct.getId());
    }

    @Test
    void testReview_PATCH() {
  
        Map<String, String> changes = new HashMap<>();
        String id =reviewController.review_GET_ALL().stream().findFirst().get().getId();
        changes.put("id", id);
        changes.put("message", "paciencia!!!!!!!");

        reviewController.review_PATCH(changes);
        Review review = reviewController.review_GET_BY_ID(id);
        
        assertEquals("paciencia!!!!!!!", review.getMessage()); 
    }

    @Test
    void testReview_POST() {

        int initialSize = reviewController.review_GET_ALL().size();
        reviewController.review_POST("blusa star", "4", "joyce", "25", "gostei muitoo!", 9);
        List<Review> reviews = reviewController.review_GET_ALL();

        Assertions.assertEquals(initialSize + 1, reviews.size());
        Assertions.assertEquals(store_id, reviewController.getId());
    }

}
