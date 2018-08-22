package com.playingjoy.fanrabbit.domain.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author: Ly
 * Data：2018/4/12-10:34
 * Description:下载信息的数据类
 */
@Entity
public class DownLoadEntity {
    /**
     * 主键id
     */
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "downloadId")
    private int downloadId;
    /**
     * 下载的apk的名字
     */
    @Property(nameInDb = "apkName")
    private String apkName;
    /**
     * 下载的apk的存储路径
     */
    @Property(nameInDb = "cachePath")
    private String cachePath;
    /**
     * 目前的下载进度
     */
    @Property(nameInDb = "downLoadProgress")
    private Long downLoadProgress;
    /**
     * 总共的大小
     */
    @Property(nameInDb = "downloadTotalSize")
    private Long downloadTotalSize;


    @Generated(hash = 1274021415)
    public DownLoadEntity(Long id, int downloadId, String apkName, String cachePath,
            Long downLoadProgress, Long downloadTotalSize) {
        this.id = id;
        this.downloadId = downloadId;
        this.apkName = apkName;
        this.cachePath = cachePath;
        this.downLoadProgress = downLoadProgress;
        this.downloadTotalSize = downloadTotalSize;
    }


    @Generated(hash = 1351351138)
    public DownLoadEntity() {
    }

 
    @Override
    public String toString() {
        return "DownLoadEntity{" +
                "id=" + id +
                ", downloadId=" + downloadId +
                ", apkName='" + apkName + '\'' +
                ", cachePath='" + cachePath + '\'' +
                ", downLoadProgress=" + downLoadProgress +
                ", downloadTotalSize=" + downloadTotalSize +
                '}';
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public int getDownloadId() {
        return this.downloadId;
    }


    public void setDownloadId(int downloadId) {
        this.downloadId = downloadId;
    }


    public String getApkName() {
        return this.apkName;
    }


    public void setApkName(String apkName) {
        this.apkName = apkName;
    }


    public String getCachePath() {
        return this.cachePath;
    }


    public void setCachePath(String cachePath) {
        this.cachePath = cachePath;
    }


    public Long getDownLoadProgress() {
        return this.downLoadProgress;
    }


    public void setDownLoadProgress(Long downLoadProgress) {
        this.downLoadProgress = downLoadProgress;
    }


    public Long getDownloadTotalSize() {
        return this.downloadTotalSize;
    }


    public void setDownloadTotalSize(Long downloadTotalSize) {
        this.downloadTotalSize = downloadTotalSize;
    }
}
