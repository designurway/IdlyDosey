package com.designurway.idlidosa.a.model;

import java.util.List;

public class GetNotificationResponse {
    private final long multicast_id;
    private final int success;
    private final int failure;
    private final int canonical_ids;
    private final List<GetNotification> results;

    public GetNotificationResponse(long multicast_id, int success, int failure, int canonical_ids, List<GetNotification> results) {
        this.multicast_id = multicast_id;
        this.success = success;
        this.failure = failure;
        this.canonical_ids = canonical_ids;
        this.results = results;
    }

    public long getMulticast_id() {
        return multicast_id;
    }

    public int getSuccess() {
        return success;
    }

    public int getFailure() {
        return failure;
    }

    public int getCanonical_ids() {
        return canonical_ids;
    }

    public List<GetNotification> getResults() {
        return results;
    }
}
