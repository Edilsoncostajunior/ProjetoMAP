package com.grupo4.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import com.grupo4.config.DatabaseStorage;
import com.grupo4.models.Review;

public class ReviewController {
    private static ArrayList<ReviewController> instance = null;

    private List<Review> reviews;
    private String store_id;

    private ReviewController(String store_id) {
        this.store_id = store_id;
        this.reviews = DatabaseStorage.creatingReviewFile(store_id);
    }

    public static synchronized ReviewController getInstance(String store_id) {
        if (instance == null) {
            instance = new ArrayList<>();
        }

        Optional<ReviewController> rOptional = instance.stream()
                .filter(value -> value.getId().equals(store_id))
                .findFirst();

        if (!rOptional.isPresent()) {
            ReviewController reviewController = new ReviewController(store_id);
            instance.add(reviewController);

            return reviewController;
        }

        return rOptional.get();
    }

    public List<Review> review_GET_ALL() {
        return reviews;
    }

    public Review review_GET_BY_ID(String id) {
        return reviews.stream().filter(value -> value.getId().equals(id)).findFirst().get();
    }

    public String review_PATCH(Map<String, String> changes) {
        int review_index = IntStream.range(0, reviews.size())
                .filter(i -> reviews.get(i).getId().equals(changes.get("id")))
                .findFirst()
                .orElse(-1);
        if (review_index != -1) {
            reviews.get(review_index).update(changes);
            DatabaseStorage.writtingReviewFile(reviews, store_id);
            return "Finalizado com sucesso as alterações do review!";
        }

        return "Falha na atualização do cliente";
    }

    public String review_POST(String product_name, String product_id, String client_name, String client_id,
            String message, int nota) {
        List<Review> reviews = new ArrayList<>(this.reviews);

        String id = reviews.size() == 0 ? "0"
                : "" + (Integer.parseInt(
                        reviews.stream().max((comp1, comp2) -> comp1.getId().compareTo(comp2.getId())).get().getId())
                        + 1);
        reviews.add(
                new Review(id, product_name, product_id, client_name, client_id, message, nota)
                );
        DatabaseStorage.writtingReviewFile(reviews, this.store_id);

        this.reviews = reviews;
        return "Finalizado com sucesso o novo cliente foi adicionado com sucesso!";
    }

    public String review_DELETE(String id) {
        this.review_GET_BY_ID(id);
        this.reviews = reviews.stream().filter(value -> !value.getId().equals(id)).toList();
        DatabaseStorage.writtingReviewFile(reviews, id);;

        return "Finalizado com sucesso o cliente foi deletado com sucesso!";
    }

    public String getId() {
        return store_id;
    }
}
