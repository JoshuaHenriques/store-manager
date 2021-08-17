package org.jayhenri.cloud9.model.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

/**
 * The type Review.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review implements Serializable {

    private static final long serialVersionUID = -1286717403046249323L;

    @Id
    @Column(name = "review_id", unique = true, nullable = false)
    private UUID reviewUUID = UUID.randomUUID();

    @Column(name = "text")
    private String text;

    @Column(name = "rating", nullable = false)
    private int rating;

    /**
     * Instantiates a new Review.
     *
     * @param text   the text
     * @param rating the rating
     */
    public Review(String text, int rating) {
        this.text = text;
        this.rating = rating;
    }
}