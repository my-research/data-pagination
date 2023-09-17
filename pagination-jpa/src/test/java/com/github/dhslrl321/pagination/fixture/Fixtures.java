package com.github.dhslrl321.pagination.fixture;

import com.github.dhslrl321.pagination.model.Priority;
import com.github.dhslrl321.pagination.model.Todo;
import com.github.dhslrl321.pagination.model.TodoStatus;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.github.dhslrl321.pagination.persistence.IdGenerator.newId;

public class Fixtures {

    public static List<Todo> generateTodos(int size) {
        List<Todo> todos = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            String title = titles[random.nextInt(titles.length)];
            String content = contents[random.nextInt(contents.length)];
            TodoStatus status = TodoStatus.values()[random.nextInt(TodoStatus.values().length)];
            Priority priority = Priority.values()[random.nextInt(TodoStatus.values().length)];

            Long ownerId = 1004L + random.nextInt(6);

            LocalDateTime createdAt = generateRandomDateTime();
            LocalDateTime updatedAt = generateRandomDateTime();
            LocalDateTime deletedAt = random.nextDouble() < 1.0 / 8.0 ? generateRandomDateTime() : null;

            Todo todo = Todo.loadFromDb(newId(), title, content, status, priority, createdAt, updatedAt, deletedAt, ownerId);
            todos.add(todo);
        }

        return todos;
    }

    private static LocalDateTime generateRandomDateTime() {
        long minDay = LocalDateTime.of(2000, 1, 1, 0, 0).toEpochSecond(
                ZoneOffset.UTC);
        long maxDay = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        long randomDay = minDay + (long) (Math.random() * (maxDay - minDay));

        return LocalDateTime.ofEpochSecond(randomDay, 0, ZoneOffset.UTC);
    }

    static final String[] titles = {
            "공부하기", "일하기", "독서", "운동", "장보기",
            "청소", "요리", "전화", "글쓰기", "이메일",
            "계획 세우기", "산책", "달리기", "요가", "쇼핑",
            "리뷰", "코딩", "배우기", "만나기", "휴식",
            "운동하기", "코딩공부", "독서", "요리", "수영",
            "영화보기", "산책", "음악듣기", "휴식", "일하기",
            "밥먹기", "영화제작", "여행가기", "영어공부", "피아노연주",
            "프로그래밍", "게임하기", "만화보기", "놀러가기", "음식먹기",
            "등산", "자전거타기", "사진찍기", "공연보기", "쇼핑",
            "디자인", "친구만나기", "커피마시기", "헬스", "댄스",
            "운동장소", "미술", "학교가기", "좋은하루", "잠자기",
            "무도", "공부시간", "프로젝트", "파티", "운동복",
            "맛있는음식", "하늘보기", "낚시", "노래부르기", "마라톤",
            "행복", "맛있는음식", "공원", "바다", "해외여행"
    };

    static final String[] contents = {
            "오늘은 스스로에게 도전하는 날! 목표를 높이 세워보자.",
            "새로운 프로젝트를 시작하며 열심히 일해야 합니다.",
            "좋은 책을 읽으면서 지식을 쌓아보세요.",
            "운동은 건강의 기반이 됩니다. 꾸준히 운동합시다.",
            "식재료를 준비하고 요리 레시피를 찾아보세요.",
            "오늘은 집 청소를 철저히 하고 깔끔한 공간을 만들어봐요.",
            "요리 실력을 향상시키기 위해 새로운 레시피 도전!",
            "가족이나 친구에게 전화를 걸어 보세요.",
            "새로운 글을 쓰는 것은 창의적인 활동입니다.",
            "이메일을 확인하고 중요한 작업을 처리하세요.",
            "일정을 계획하고 목표를 달성해봅시다.",
            "산책을 하면서 신선한 공기를 마음껏 마시세요.",
            "달리기는 몸을 활발하게 만듭니다. 기분 좋게 뛰어보세요.",
            "요가를 통해 몸과 마음을 편안하게 유지하세요.",
            "온라인 쇼핑을 통해 원하는 물건을 찾아보세요.",
            "나의 활동을 돌아보며 리뷰를 남겨보세요.",
            "프로그래밍 능력을 향상시키는 시간을 가지세요.",
            "새로운 언어나 기술을 배우는 것은 늘 유용합니다.",
            "친구나 가족과 만나서 즐거운 시간을 보내세요.",
            "자유로운 시간을 활용하여 휴식을 취하세요."
    };

}

