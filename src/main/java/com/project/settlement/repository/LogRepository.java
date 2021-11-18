package com.project.settlement.repository;

import com.project.settlement.entities.ProcessingLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LogRepository extends ElasticsearchRepository<ProcessingLog, String> {



  /*  List<LogRepository> findByBrandAndColor(String brand, String color);
   Page<ProcessingLog> findByFileNameAndAffiliateCode(String fileName, boolean failed, String message, Pageable pageable);*/

}
