package kr.co.jhjsoft.moviereview.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Log4j2
public class UploadController {
    //파일 업로드처리 메서드
    @PostMapping(value = "/upjoadajax")
    public void uploadFile(MultipartFile [] uploadFiles){
        for (MultipartFile uploadFile : uploadFiles){
            //업로드 되는 원본 파일 이름 출력
            String originalName = uploadFile.getOriginalFilename();
            //IE 나 Edge는 파일의 경로도 전달 되므로 파일의 경로를 제거
            String fileName = originalName.substring(originalName.lastIndexOf(("\\")+1));
            log.info("File Name : " + fileName);
        }
    }

}
