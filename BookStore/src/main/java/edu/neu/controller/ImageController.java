package edu.neu.controller;

import edu.neu.model.User;
import edu.neu.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ImageController {

//    @Autowired
//    ImageService imageService;
//
//    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
//    public @ResponseBody String uploadPhoto(@RequestPart(value = "file") MultipartFile multipartFile, @RequestParam(value = "bookId") String bookID, @RequestParam(value = "userId") String userId) {
//        String isPhotoUploaded = imageService.uploadFile(multipartFile, bookID, userId);
//        long end = System.currentTimeMillis();
//        long timeTaken = end - begin;
//        logger.info("TIme taken by uploadPhototoS3 API " + timeTaken + "ms");
//        stasDClient.recordExecutionTime("uploadPhototoS3Time", timeTaken);
//        return isPhotoUploaded;
//    }
}
