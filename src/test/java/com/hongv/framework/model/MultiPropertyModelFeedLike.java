package com.hongv.framework.model;

import com.google.common.reflect.TypeToken;

import java.util.Date;
import java.util.List;

/**
 * Created by atom on 2017/7/16.
 */
public class MultiPropertyModelFeedLike extends MultiPropertyModel {

    public static final ModelPropertyKey<List<Long>> KEY_LIKE_USERS = new ModelPropertyKey<>(MultiPropertyModelFeedLike.class,
            "likeUsers", new TypeToken<List<Long>>() {
    });

    public static final ModelPropertyKey<Integer> KEY_FEED_VIEW_COUNT = new ModelPropertyKey<>(MultiPropertyModelFeedLike.class,
            "feedViewCount", TypeToken.of(Integer.class));

    public static final ModelPropertyKey<FeedActivityType> KEY_ACTIVITY_TYPE = new ModelPropertyKey<FeedActivityType>(MultiPropertyModelFeedLike.class,
            "activityType", TypeToken.of(FeedActivityType.class), new PropertyTypeConverter<FeedActivityType>() {
        @Override
        public FeedActivityType decode(Object rawValue) {
            return FeedActivityType.fromValue((Integer) rawValue);
        }

        @Override
        public Object encode(FeedActivityType value) {
            return value.getValue();
        }
    }, FeedActivityType.Normal);

    private final long id;
    private final long postId;
    private final String data;
    private final Date createTime;

    public MultiPropertyModelFeedLike(long id, long postId, String data, Date createTime) {
        this.id = id;
        this.postId = postId;
        this.data = data;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public long getPostId() {
        return postId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    protected String getData() {
        return data;
    }

    public List<Long> getLikeUsers() {
        return getProperty(KEY_LIKE_USERS);
    }

    public Integer getFeedViewCount() {
        return getProperty(KEY_FEED_VIEW_COUNT);
    }

    public FeedActivityType getFeedActivity() {
        return getProperty(KEY_ACTIVITY_TYPE);
    }
}
