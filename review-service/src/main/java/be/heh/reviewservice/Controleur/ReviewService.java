package be.heh.reviewservice.Controleur;

import be.heh.reviewservice.Modele.Review;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReviewService {

    /**
     * Implémentation de l'API: getReviews(int productId)
     * URL exemple: http://localhost:7004/review?productId=1
     */
    @GetMapping(value = "/review", produces = "application/json")
    public Flux<Review> getReviews(@RequestParam(value = "productId", required = true) int productId) {

        // Comme demandé: "Ne pas implémenter les bases de données !!!"
        // On retourne une liste simulée d'avis pour le produit demandé.

        List<Review> list = new ArrayList<>();

        // Simulation: on crée 3 avis fictifs pour l'ID produit reçu
        list.add(new Review(productId, 1, "Auteur 1", "Sujet 1", "Contenu très positif", "localhost:7004"));
        list.add(new Review(productId, 2, "Auteur 2", "Sujet 2", "Contenu assez neutre", "localhost:7004"));
        list.add(new Review(productId, 3, "Auteur 3", "Sujet 3", "Contenu négatif", "localhost:7004"));

        // En WebFlux, on encapsule la liste dans un Flux
        return Flux.fromIterable(list);
    }
}
