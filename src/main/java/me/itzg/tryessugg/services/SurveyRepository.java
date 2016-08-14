package me.itzg.tryessugg.services;

import me.itzg.tryessugg.model.Survey;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

/**
 * @author Geoff Bourne
 */
public interface SurveyRepository extends ElasticsearchCrudRepository<Survey, String> {
}
