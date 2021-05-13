package com.z1.runner.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 工作表
 * </p>
 *
 * @author DeanHuang(hudi-112@163.com)
 * @since 2021-05-14
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_job")
public class Job implements Serializable {

	private static final long serialVersionUID = 1L;
    
    /**
     * 并发默认值
     */
    public static final int CONCURRENT_NUM_DEFAULT = 1;
    /**
     * 并发数最小值
     */
    public static final int CONCURRENT_NUM_MIN = 1;
    /**
     * 并发数最大值
     */
    public static final int CONCURRENT_NUM_MAX = 9999;

    /**
     * 任务编号
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务类型
     */
    private String type;

    /**
     * 任务参数(JSON)
     */
    private String arguments;

    /**
     * 执行周期
     */
    private String cycle;

    /**
     * 并发数
     */
    private Integer concurrentNum;

    /**
     * 是否启用,0-否，1-是,默认为1
     */
    private Integer enabled;
    
    public static final String FIELD_NAME_NAME = "name";

}
