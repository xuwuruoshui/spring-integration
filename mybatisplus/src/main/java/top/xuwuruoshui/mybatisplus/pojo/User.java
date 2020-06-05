package top.xuwuruoshui.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 自动填充
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

  @TableId(type = IdType.INPUT)
  private long id;

  private String name;

  private long age;

  private String email;

  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  @Version
  private long version;

  @TableLogic
  private int deleted;


}
