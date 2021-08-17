package org.jayhenri.cloud9.interfaces.service.other;

import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.model.item.Review;

import java.util.Set;
import java.util.UUID;

/**
 * The interface Review service i.
 *
 * @param <T> the type parameter
 * @param <C> the type parameter
 * @param <U> the type parameter
 */
public interface ReviewServiceI {

    /**
     * Add.
     *
     * @param item   the item
     * @param review the review
     */
    void add(Item item, Review review);

    /**
     * Add.
     *
     * @param item   the item
     * @param review the review
     */
    void update(Item item, Review review);

    /**
     * Remove.
     *
     * @param item the item
     * @param uuid the uuid
     */
    void remove(Item item, UUID uuid);

    /**
     * Find all set.
     *
     * @param item the item
     * @return the set
     */
    Set<Review> findAll(Item item);

    /**
     * Exists by id boolean.
     *
     * @param item the item
     * @param uuid the uuid
     * @return the boolean
     */
    boolean existsById(Item item, UUID uuid);

    /**
     * Gets by id.
     *
     * @param item the item
     * @param uuid the uuid
     * @return the by id
     */
    Review getById(Item item, UUID uuid);
}