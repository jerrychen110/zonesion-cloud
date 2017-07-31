package com.zonesion.cloud.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.zonesion.cloud.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.Course.class.getName(), jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.Course.class.getName() + ".courseFavorites", jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.Course.class.getName() + ".chapters", jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.Course.class.getName() + ".courseReviews", jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.CourseFavorite.class.getName(), jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.CourseReview.class.getName(), jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.Chapter.class.getName(), jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.Chapter.class.getName() + ".courseLessons", jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.CourseLesson.class.getName(), jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.CourseLesson.class.getName() + ".courseLessonAttachments", jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.CourseLesson.class.getName() + ".courseLessonLearns", jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.CourseLesson.class.getName() + ".courseLessonNotes", jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.CourseLessonAttachment.class.getName(), jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.CourseLessonLearn.class.getName(), jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.CourseLessonNote.class.getName(), jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.CourseLessonNote.class.getName() + ".courseLessonNoteLikes", jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.CourseLessonNoteLike.class.getName(), jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.File.class.getName(), jcacheConfiguration);
            cm.createCache(com.zonesion.cloud.domain.Major.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
