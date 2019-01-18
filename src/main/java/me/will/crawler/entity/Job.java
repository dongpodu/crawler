package me.will.crawler.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 站点
 * </p>
 *
 * @author will
 * @since 2019-01-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

        /**
     * 工作名
     */
         private String jobName;

        /**
     * 公司名
     */
         private String companyName;

        /**
     * 工作详情地址
     */
         private String detailUrl;

        /**
     * 职位描述
     */
         private String jobDescription;

        /**
     * 工资
     */
         private String salary;

        /**
     * 标签
     */
         private String tag;


}
