package com.playingjoy.fanrabbit.utils.cache;

import com.playingjoy.fanrabbit.domain.entity.DownLoadEntity;
import com.playingjoy.fanrabbit.gen.DownLoadEntityDao;
import com.playingjoy.fanrabbit.utils.db.DbHelper;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Author: Ly
 * Data：2018/4/12-11:40
 * Description:下载信息
 */
public class DownLoadInfoManager {

    /**
     * 插入数据
     *
     * @param entity
     */
    private static void saveOrUpdate(DownLoadEntity entity) {
        DownLoadEntity d = queryByDownloadId(entity.getDownloadId());
        if (d == null) {
            DbHelper.getDaoSession().getDownLoadEntityDao().save(entity);
        } else {
            entity.setId(d.getId());
            DbHelper.getDaoSession().getDownLoadEntityDao().update(entity);
        }
    }

    /**
     * 插入数据
     *
     * @param downLoadId
     * @param apkName
     * @param cacheName
     * @param progress
     * @param total
     */
    public static void saveOrUpdate(int downLoadId, String apkName, String cacheName, long progress, long total) {
        DownLoadEntity downLoadEntity = new DownLoadEntity();
        downLoadEntity.setDownloadId(downLoadId);
        downLoadEntity.setApkName(apkName);
        downLoadEntity.setCachePath(cacheName);
        downLoadEntity.setDownLoadProgress(progress);
        downLoadEntity.setDownloadTotalSize(total);
        saveOrUpdate(downLoadEntity);
    }

    /**
     * 更新进度
     *
     * @param downloadId
     * @param progress
     * @param total
     */
    public static void updateDownloadProgress(int downloadId, long progress, long total) {
        DownLoadEntity d = queryByDownloadId(downloadId);
        if (d != null) {
            d.setDownLoadProgress(progress);
            d.setDownloadTotalSize(total);
            DbHelper.getDaoSession().getDownLoadEntityDao().save(d);
        }
    }

    /**
     * 查询所有列表
     *
     * @return
     */
    public static List<DownLoadEntity> list() {
        return getQueryBuilder().list();
    }

    /**
     * 根据downloadId查询
     *
     * @param downloadId
     * @return
     */
    public static DownLoadEntity queryByDownloadId(int downloadId) {
        return getQueryBuilder().where(DownLoadEntityDao.Properties.DownloadId.eq(downloadId)).unique();
    }


    /**
     * 根据downloadID删除数据缓存
     *
     * @param downloadId
     * @return
     */
    public static boolean deleteByDownloadId(int downloadId) {
        DownLoadEntity downLoadEntity = queryByDownloadId(downloadId);
        if (downLoadEntity != null) {
            getEntityDao().delete(downLoadEntity);
        }
        DownLoadEntity result = queryByDownloadId(downloadId);
        return result == null;
    }

    private static QueryBuilder<DownLoadEntity> getQueryBuilder() {
        return getEntityDao().queryBuilder();
    }

    private static DownLoadEntityDao getEntityDao() {
        return DbHelper.getDaoSession().getDownLoadEntityDao();
    }
}
