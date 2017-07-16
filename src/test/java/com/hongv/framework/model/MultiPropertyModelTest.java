package com.hongv.framework.model;

import com.hongv.framework.codec.JSONCodec;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by atom on 2017/7/16.
 */
public class MultiPropertyModelTest {

    @Test
    public void testModelConstruct() throws Exception {

        String oldData = "{}";

        String data = ModelPropertyBuilder.newBuilder()
                .load(oldData)
                .add(MultiPropertyModelFeedLike.KEY_ACTIVITY_TYPE, FeedActivityType.MolenDay)
                .add(MultiPropertyModelFeedLike.KEY_FEED_VIEW_COUNT, 100)
                .add(MultiPropertyModelFeedLike.KEY_LIKE_USERS, Arrays.asList(1l, 2l, 3l, 4l, 5l))
                .buildString();

        MultiPropertyModelFeedLike feedLikeObj = new MultiPropertyModelFeedLike(0l, 1l, data, new Date());

        System.out.println(JSONCodec.encode(feedLikeObj));

        FeedActivityType feedActivity = feedLikeObj.getFeedActivity();
        Assert.assertEquals(FeedActivityType.MolenDay, feedActivity);
        System.out.println("feedActivity: " + feedActivity);

        Integer feedViewCount = feedLikeObj.getFeedViewCount();
        Assert.assertEquals(100, feedViewCount.intValue());
        System.out.println("feedViewCount: " + feedViewCount);

        List<Long> likeUsers = feedLikeObj.getLikeUsers();
        Assert.assertEquals(Arrays.asList(1l, 2l, 3l, 4l, 5l), likeUsers);
        System.out.println("likeUsers: " + likeUsers);

    }

}