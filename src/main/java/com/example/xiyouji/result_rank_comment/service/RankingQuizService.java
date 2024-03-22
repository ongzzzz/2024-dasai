package com.example.xiyouji.result_rank_comment.service;

import com.example.xiyouji.login.entity.Member;
import com.example.xiyouji.login.repository.MemberRepository;
import com.example.xiyouji.result_rank_comment.constant.CustomException;
import com.example.xiyouji.result_rank_comment.constant.ErrorCode;
import com.example.xiyouji.result_rank_comment.dto.RankingTopTenResponse;
import com.example.xiyouji.result_rank_comment.dto.UserRankingResponse;
import com.example.xiyouji.result_rank_comment.repository.CommentRepository;
import com.example.xiyouji.type.Characters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RankingQuizService {


    private final MemberRepository memberRepository;
    private final RedisTemplate<String, RankingTopTenResponse> redisTemplate;
    private final static String RANKING = "ranking";


    @Transactional
    public UserRankingResponse saveUserRankingAndIsUserInTopTen(Long userId, List<String> characters){
        ZSetOperations<String, RankingTopTenResponse> zSetOperations = redisTemplate.opsForZSet();

        // Character 타입으로 변환
        List<String> charactersFormat = characters.stream()
                .map(character->Characters.fromString(character).toString()).toList();
        // 가장 많은 캐릭터 정답을 추출 - 중복일 경우 다수 추출
        List<Characters> maxCorrectCharacters = findMaxCorrectCharacters(charactersFormat);
        // 회원 정보 검색
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        RankingTopTenResponse rankingTopTenResponse = RankingTopTenResponse.of(userId,charactersFormat.size(), member.getNickName(),maxCorrectCharacters);

        // 랭킹 정보를 레디스에 저장
        saveRanking(zSetOperations, rankingTopTenResponse, charactersFormat.size());

        Long userRank = zSetOperations.reverseRank(RANKING, rankingTopTenResponse);
        if(userRank == null){
            throw new CustomException(ErrorCode.USER_RANKING_NOT_FOUND);
        }

        // 회원이 10위 안에 있다면 빈 값을 반환, 10위 밖에 있다면 회원 순위를 함께 dto에 담아서 반환
        return userRank < 10 ?
                UserRankingResponse.empty():
                UserRankingResponse.of(userId, userRank,charactersFormat.size(), member.getNickName(), maxCorrectCharacters);
    }
    public List<RankingTopTenResponse> getRankingTopTen(){
        ZSetOperations<String, RankingTopTenResponse> zSetOperations = redisTemplate.opsForZSet();
        // 가장 많은 답을 맞은 계정 부터 10위까지 추출
        Set<RankingTopTenResponse> rankingTopTenResponses = zSetOperations.reverseRange(RANKING, 0, 9);
        return new ArrayList<>(Objects.requireNonNull(rankingTopTenResponses));
    }


    private List<Characters> findMaxCorrectCharacters(List<String> charactersFormat) {

        // 맞은 답이 한개도 없을 경우 빈 List를 반환
        if(charactersFormat.isEmpty()){
            return new ArrayList<>();
        }

         /*캐릭터 별로 맞은 갯수 연산
         String: 캐릭터
         Integer: 맞은 갯수*/
        Map<String, Integer> map = charactersFormat.stream().collect(
                Collectors.toMap(Function.identity(), value->1, Integer::sum)
        );

        // 캐릭터 별로 맞은 갯수 중 가장 많이 맞은 갯수 추출
        Integer maxValue = Collections.max(map.values());

        // 가장 많이 맞은 캐릭터 추출 (가장 많이 맞은 캐릭터가 여러 개일 경우를 가정해 List로 값을 받는다.)
        List<Characters> maxCorrectCharacters = new ArrayList<>();
        for (String key : map.keySet()) {
            if(map.get(key) >= maxValue){
                maxCorrectCharacters.add(Characters.fromString(key));
            }
        }
        return maxCorrectCharacters;
    }


    private static void saveRanking(ZSetOperations<String, RankingTopTenResponse> zSetOperations, RankingTopTenResponse rankingTopTenResponse, Integer score) {
       zSetOperations.add(RANKING, rankingTopTenResponse, score);
    }
}
