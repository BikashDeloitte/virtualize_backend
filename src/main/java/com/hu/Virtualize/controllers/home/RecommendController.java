package com.hu.Virtualize.controllers.home;

import com.hu.Virtualize.entities.RecommendEntity;
import com.hu.Virtualize.services.home.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

@Slf4j
@RequestMapping("/recommend")
@RestController
public class RecommendController {

    private final RecommendService recommendService;

    public RecommendController(RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    /**
     * This function will insert image in recommend bar.
     * @param multipartFile image
     * @param date expire date (YYYY-MM-DD)
     * @return status
     */
    @PostMapping("/insert")
    public ResponseEntity<?> insertRecommend(@RequestParam("image") MultipartFile multipartFile, @RequestParam("date")Date date,
                                             @RequestParam("category")String category) {
        log.info("Insert offer to show on home recommend bar");
        String status = recommendService.insertRecommend(multipartFile, date, category);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    /**
     * This function will return all the show recommend IDs.
     * @return list of recommends
     */
    @GetMapping("/showRecommend")
    public ResponseEntity<?> findShowRecommendId() {
        log.info("Find all recommend bar");
        List<RecommendEntity> showRecommends = recommendService.findShowRecommendId();
        return new ResponseEntity<>(showRecommends, HttpStatus.OK);
    }

    /**
     * This function will show the image for recommend.
     * @param recommendId recommend id
     * @param response http servlet response.
     */
    @GetMapping("/bar/{recommendId}")
    public void renderImageFromDB(@PathVariable String recommendId, HttpServletResponse response) {
        log.info("show all recommend info on home page");
        RecommendEntity recommendEntity = recommendService.findById(Long.valueOf(recommendId));

        try {
            byte[] byteArray = new byte[recommendEntity.getRecommendImage().length];

            int i = 0;
            for (Byte wrappedByte : recommendEntity.getRecommendImage()) {
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        } catch (IOException e) {
            log.error("Image fetch error: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}