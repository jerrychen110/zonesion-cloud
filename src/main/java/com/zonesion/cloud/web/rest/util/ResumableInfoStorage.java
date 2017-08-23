package com.zonesion.cloud.web.rest.util;

import java.util.HashMap;
import java.util.Map;

import com.zonesion.cloud.web.rest.dto.ResumableInfo;

/**
 * by fanxu
 */
public class ResumableInfoStorage {

    //Single instance
    private ResumableInfoStorage() {
    }
    private static ResumableInfoStorage sInstance;

    public static synchronized ResumableInfoStorage getInstance() {
        if (sInstance == null) {
            sInstance = new ResumableInfoStorage();
        }
        return sInstance;
    }

    //resumableIdentifier --  ResumableInfo
    private Map<String, ResumableInfo> mMap = new HashMap<>();

    /**
     * Get ResumableInfo from mMap or Create a new one.
     * @param resumableChunkSize
     * @param resumableTotalSize
     * @param resumableIdentifier
     * @param resumableFilename
     * @param resumableRelativePath
     * @param resumableFilePath
     * @return
     */
    public synchronized ResumableInfo get(int resumableChunkSize, long resumableTotalSize,
                             String resumableIdentifier, String resumableFilename,
                             String resumableRelativePath) {

        ResumableInfo info = mMap.get(resumableIdentifier);

        if (info == null) {
            info = new ResumableInfo();

    		info.setResumableChunkSize(resumableChunkSize);
    		info.setResumableTotalSize(resumableTotalSize);
    		info.setResumableIdentifier(resumableIdentifier);
    		info.setResumableFilename(resumableFilename);
    		info.setResumableRelativePath(resumableRelativePath);

            mMap.put(resumableIdentifier, info);
        }
        return info;
    }

    /**
     * 删除ResumableInfo
     * @param info
     */
    public void remove(String identifier) {
       mMap.remove(identifier);
    }
}
