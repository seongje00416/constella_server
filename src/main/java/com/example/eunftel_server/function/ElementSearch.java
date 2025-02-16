package com.example.eunftel_server.function;

import java.util.*;

public class ElementSearch {
    public static Map<String, Boolean> containsAllElements( List<String> memberIdList, List<String> liveList ) {
        Set<String> set = new HashSet<>();
        for (String liveId : liveList) {
            set.add(liveId);
        }

        // 각 요소와 존재 여부를 Map으로 매핑
        Map<String, Boolean> result = new HashMap<>();
        for (String member : memberIdList) {
            result.put(member, set.contains(member));
        }
        return result;
    }
}
