package com.hu.Virtualize.services.home;

import com.hu.Virtualize.entities.RecommendEntity;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

public interface RecommendService {
    String insertRecommend(MultipartFile multipartFile, Date date, String category);
    RecommendEntity findById(Long recommendId);
    List<RecommendEntity> findShowRecommends();
}
