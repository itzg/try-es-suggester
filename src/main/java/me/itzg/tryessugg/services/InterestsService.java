package me.itzg.tryessugg.services;

import me.itzg.tryessugg.model.Survey;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Geoff Bourne
 */
@Service
public class InterestsService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private Client esClient;

    public void save(List<String> interests) {
        final Survey survey = new Survey(new Date(), interests);

        surveyRepository.save(survey);
    }

    public List<String> suggestInterests(String given) {
        // NOTE: I couldn't seem to use ElasticsearchTemplate since it didn't have a way to set the results size to
        // zero, which is optimal when just getting aggregations.
        final SearchResponse searchResponse = esClient.prepareSearch()
                .setSize(0)
                .addAggregation(AggregationBuilders.terms("interests")
                        .field("interests")
                        .include(given + ".*")
                        .order(Terms.Order.term(true)))
                .get();

        final List<String> suggestions = new ArrayList<>();
        suggestions.add(given);

        final Terms interests = searchResponse.getAggregations().get("interests");
        interests.getBuckets().forEach(b -> {
            suggestions.add(b.getKey().toString());
        });

        return suggestions;
    }
}
