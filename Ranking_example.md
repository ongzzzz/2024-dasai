# rankingTopFive -> userId : 회원 ID, quizResult: 퀴즈 맞은 수, nickName: 회원 닉네임, chracters: 가장 많이 맞은 캐릭터
# userRanking -> userRankingCount: 현재 회원 순위 
# ------------------------------------------------
# pageable: 
# pageNumber: 현재 페이지 번호 (처음 페이지는 0 부터 시작입니다.)
# pageSize: 페이지당 표시되는 댓글 수 (댓글 수는 5개만 받기로 정함)
# sort: 정렬 정보
# offset: 현재 페이지의 시작 인덱스
# paged, unpaged: 페이지네이션을 사용하는지 여부를 나타냅니다.
# last : 마지막 페이지 여부
# totalElements: 전체 댓글 수
# totalPages: 전체 페이지 수
# size: 페이지당 표시되는 댓글 수 (pageSize == size)
# number: 현재 페이지 번호 (pageNumber == number)
# first: 처음 페이지 여부
# numberOfElements: 현재 페이지에 포함된 댓글 수
# empty: 빈 값 여부
# ----------------------------------------------------------------------------------------------

# 이미 저장된 회원 + 랭킹 = 10개 
# 현재 회원 ID = 11
------------------------------------------------
# example: 
# Characters -> ["저팔계", "삼장법사", "손오공", "사오정", "저팔계", "손오공", "손오공", "저팔계", "삼장법사"]
# 맞은 문제 수 : 9개
# 가장 많이 맞은 캐릭터 : 저팔계, 손오공
# 순위 : 7위 
# 5위안에 ID 11이 포함되어 있기 때문에 userRanking은 null 값을 받습니다.
# comment 5개 반환


{
    "rankingTopFive": [
        {
            "userId": 10,
            "quizResult": 10,
            "nickName": "name10",
            "characters": [
                "손오공"
            ]
        },
        {
            "userId": 9,
            "quizResult": 9,
            "nickName": "name9",
            "characters": [
                "손오공"
            ]
        },
        {
            "userId": 11,
            "quizResult": 9,
            "nickName": "name11",
            "characters": [
                "저팔계",
                "손오공"
            ]
        },
        {
            "userId": 8,
            "quizResult": 8,
            "nickName": "name8",
            "characters": [
                "손오공"
            ]
        },
        {
            "userId": 7,
            "quizResult": 7,
            "nickName": "name7",
            "characters": [
                "손오공"
            ]
        }
    ],
    "userRanking": null,
    "comments": {
        "content": [
            {
                "nickName": "name10",
                "content": "content10"
            },
            {
                "nickName": "name9",
                "content": "content9"
            },
            {
                "nickName": "name8",
                "content": "content8"
            },
            {
                "nickName": "name7",
                "content": "content7"
            },
            {
                "nickName": "name6",
                "content": "content6"
            }
        ],
        "pageable": {
            "pageNumber": 0,
            "pageSize": 5,
            "sort": {
                "empty": false,
                "unsorted": false,
                "sorted": true
            },
            "offset": 0,
            "paged": true,
            "unpaged": false
        },
        "last": false,
        "totalPages": 2,
        "totalElements": 10,
        "size": 5,
        "number": 0,
        "sort": {
            "empty": false,
            "unsorted": false,
            "sorted": true
        },
        "first": true,
        "numberOfElements": 5,
        "empty": false
    }
}

--------------------------------------------------------------
# example 2 
# ["손오공", "저팔계", "손오공"]
# 맞은 문제 수: 3개
# 가장 많이 맞은 캐릭터 -> 손오공
# ID 11은 5위 밖에 있기 때문에 userRanking에 값이 포함됩니다.


{
    "rankingTopFive": [
        {
            "userId": 10,
            "quizResult": 10,
            "nickName": "name10",
            "characters": [
                "손오공"
            ]
        },
        {
            "userId": 9,
            "quizResult": 9,
            "nickName": "name9",
            "characters": [
                "손오공"
            ]
        },
        {
            "userId": 8,
            "quizResult": 8,
            "nickName": "name8",
            "characters": [
                "손오공"
            ]
        },
        {
            "userId": 7,
            "quizResult": 7,
            "nickName": "name7",
            "characters": [
                "손오공"
            ]
        },
        {
            "userId": 6,
            "quizResult": 6,
            "nickName": "name6",
            "characters": [
                "손오공"
            ]
        }
    ],
    "userRanking": {
        "userId": 11,
        "rankingCount": 8,
        "quizResult": 3,
        "nickName": "name11",
        "characters": [
            "손오공"
        ]
    },
    "comments": {
        "content": [
            {
                "nickName": "name10",
                "content": "content10"
            },
            {
                "nickName": "name9",
                "content": "content9"
            },
            {
                "nickName": "name8",
                "content": "content8"
            },
            {
                "nickName": "name7",
                "content": "content7"
            },
            {
                "nickName": "name6",
                "content": "content6"
            }
        ],
        "pageable": {
            "pageNumber": 0,
            "pageSize": 5,
            "sort": {
                "empty": false,
                "unsorted": false,
                "sorted": true
            },
            "offset": 0,
            "paged": true,
            "unpaged": false
        },
        "last": false,
        "totalElements": 10,
        "totalPages": 2,
        "size": 5,
        "number": 0,
        "sort": {
            "empty": false,
            "unsorted": false,
            "sorted": true
        },
        "first": true,
        "numberOfElements": 5,
        "empty": false
    }
}


