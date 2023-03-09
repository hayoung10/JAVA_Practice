package Level4;

import java.util.*;

public class Hotel_Room_Assignment { // 호텔 방 배정
    HashMap<Long, Long> rooms = new HashMap<>(); // 배정할 수 있는 다음 방을 안내하기

    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        for(int i = 0; i < room_number.length; i++)
            answer[i] = findEmptyRoom(room_number[i]);

        return answer;
    }

    private long findEmptyRoom(long roomNum) { // 비어있는 방 찾기
        if(!rooms.containsKey(roomNum)) {
            rooms.put(roomNum, roomNum + 1); // roomNum의 다음 방으로 배정하기
            return roomNum;
        }

        long emptyRoom = findEmptyRoom(rooms.get(roomNum));
        rooms.put(roomNum, emptyRoom);
        return emptyRoom;
    }
}
