package org.zerock.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
public class UploadResultDTO implements Serializable {
    private String fileName;
    private String uuid;
    private String folderPath;

    // 이미지 링크를 처리하기 위한 메서드
    public String getImageURL() {
        try {
            return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    // 섬네일 링크를 처리하기 위한 메서드
    // 업로드된 파일과 동일한 이름에 's_'가 붙은 형태이므로 구분이 어렵지 않음
    public String getThumbnailURL() {
        try {
            return URLEncoder.encode(folderPath+"/s_"+uuid+"_"+fileName, "UTF-8"); // "/s_" 추가
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
