package com.hu.Virtualize.services;

import com.hu.Virtualize.entities.RecommendEntity;
import com.hu.Virtualize.repositories.RecommendRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RecommendServiceImpl implements RecommendService {
    private final RecommendRepository recommendRepository;

    public RecommendServiceImpl(RecommendRepository recommendRepository) {
        this.recommendRepository = recommendRepository;
    }

    /**
     * This function will add the image in recommend bar.
     * @param multipartFile image
     * @param date expire date
     * @return status
     */
    public String insertRecommend(MultipartFile multipartFile, Date date, String category) {

        // convert MultipartFile into byte array and store in product entity
        Byte[] byteObjects;
        try{
            byteObjects = new Byte[multipartFile.getBytes().length];

            // copy the file data into byte array
            int i = 0;
            for (byte b : multipartFile.getBytes()){
                byteObjects[i++] = b;
            }
        } catch (Exception e) {
            log.error("Exception: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.HTTP_VERSION_NOT_SUPPORTED, e.getMessage());
        }

        RecommendEntity recommendEntity = new RecommendEntity();
        // set the profile image
        recommendEntity.setRecommendImage(byteObjects);
        recommendEntity.setEndDate(date);
        recommendEntity.setCategoryType(category);

        recommendEntity = recommendRepository.save(recommendEntity);
        log.info("Recommendation add successfully.");

        return "Recommendation add successfully.";
    }

    /**
     * This will return all recommend object.
     * @param recommendId recommend id.
     * @return  recommend object
     */
    public RecommendEntity findById(Long recommendId) {
        Optional<RecommendEntity> recommendEntity = recommendRepository.findById(recommendId);

        // if productId isn't valid
        if(recommendEntity.isEmpty()) {
            log.error("Invalid recommend bar");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid recommend");
        }
        return recommendEntity.get();
    }

    /**
     * This function will return all show recommend object id.
     * It also delete the expire recommend bar.
     * @return list of recommend id.
     */
    public List<RecommendEntity> findShowRecommendId() {
        List<RecommendEntity> recommendEntities = recommendRepository.findAll();

        long millis = System.currentTimeMillis();
        java.sql.Date todayDate = new java.sql.Date(millis);

        // delete the expire recommend.
        for(RecommendEntity recommend : recommendEntities) {
            // if recommend image is expired, then that will be deleted.
            if(recommend.getEndDate().compareTo(todayDate) < 0) {
                recommendRepository.deleteById(recommend.getRecommendId());
            }
        }

        // again find all remain recommend items.
        recommendEntities = recommendRepository.findAll();

        // sorting according to the date
        recommendEntities.sort(Comparator.comparing(RecommendEntity::getEndDate));

        List<Long> recommendIds = new ArrayList<>();

        return recommendEntities;
    }
}
