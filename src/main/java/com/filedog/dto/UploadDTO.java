package com.filedog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:CookieBoy
 * @Description
 * @create 2024/2/19
 * @version:1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadDTO {

    private String submiter;

    private List<String> folder = new ArrayList<>();

    private List<String> files = new ArrayList();

}
