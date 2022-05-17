package com.example.baked.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

import com.example.baked.model.Tutor;
//import com.mongodb.client.result.UpdateResult;

@Component
public class CustomTutorRepoImp implements CustomTutorRepo{

    @Autowired
	MongoTemplate mongoTemplate;

    @Override
    public List<Tutor> getTutorOnFeatures(String major, String qualification, String subject) {
        // TODO Auto-generated method stub
        Query query = new Query();
        query.addCriteria(Criteria.where("major").is(major));
        query.addCriteria(Criteria.where("qualification").is(qualification));
        query.addCriteria(Criteria.where("subjects").in(subject));
        List<Tutor> tutor = mongoTemplate.find(query, Tutor.class, "Tutor");
        return tutor;
    }
    
}
