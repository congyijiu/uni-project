package com.uni.pj.dynamic.vos;

import com.uni.pj.dynamic.pojos.Dynamic;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author congyijiu
 * @create 2023-11-24-10:52
 */
@Data
public class DynamicDetailVo extends Dynamic {
    private List<String> imageUrlList;

    private Integer isLike;

    private Integer isFavorite;

    private Integer isFollow;

    public void setImageUrlList(String imageUrl) {
        imageUrlList = new ArrayList<>();
        String[] split = imageUrl.split(",");
        for (String s : split) {
            imageUrlList.add(s);
        }
    }
}
