package top.xuwuruoshui.springes.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Goods {
    private String title;
    private String img;
    private String price;
}
