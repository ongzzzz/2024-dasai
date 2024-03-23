package com.example.xiyouji.result_rank_comment.repository;

import com.example.xiyouji.login.entity.Member;
import com.example.xiyouji.login.repository.MemberRepository;
import com.example.xiyouji.result_rank_comment.config.JpaConfig;
import com.example.xiyouji.result_rank_comment.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@Import(JpaConfig.class)
@TestPropertySource(locations = "classpath:/application-test.yaml")
@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommentRepository commentRepository;


    @Test
    @DisplayName("댓글 페이징 테스트 (날짜 오름차순)")
    void commentPagingTest() throws Exception
    {
        //given
        saveComment();
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        PageRequest pageRequest = PageRequest.of(0, 5, sort);
        //when
        Page<Comment> page = commentRepository.pagingComments(pageRequest);
        //then
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(5);
        assertThat(page.getTotalElements()).isEqualTo(10);
        assertThat(page.isFirst()).isEqualTo(true);
        assertThat(page.isLast()).isEqualTo(false);

        int count = 0;
        for (int i = 5; i >=1 ; i--) {
            assertThat(page.getContent().get(count).getContent())
                    .isEqualTo("comment" + (i + 5));
            assertThat(page.getContent().get(count).getMember().getNickName())
                    .isEqualTo("nickName" + (i + 5));
            count ++;
        }
    }


    @Test
    @DisplayName("회원이 댓글을 작성 여부 테스트 - 작성된 회원 존재")
    void existsByMemberTest() throws Exception
    {

        // given
        Member member = Member.builder().nickName("name").build();
        Member savedMember = entityManager.persist(member);
        Comment comment = Comment.builder().content("content").member(member).build();
        entityManager.persist(comment);
        entityManager.flush();
        entityManager.clear();

        // when
        boolean isTrue = commentRepository.existsByMemberId(savedMember.getId());

        //then
        assertThat(isTrue).isTrue();
    }
    @Test
    @DisplayName("회원이 댓글을 작성 여부 테스트 - 작성된 회원 없음")
    void notExistsByMemberTest() throws Exception
    {

        // given
        Member member = Member.builder().nickName("name").build();
        Member savedMember = entityManager.persist(member);
        entityManager.flush();
        entityManager.clear();

        // when
        boolean isFalse = commentRepository.existsByMemberId(savedMember.getId());

        //then
        assertThat(isFalse).isFalse();
    }

    private void saveComment() throws InterruptedException {
        Comment comment;
        Member member;
        for (int i = 1; i <= 10; i++) {
            member = Member.builder().nickName("nickName"+i).build();
            entityManager.persist(member);
            comment = Comment.builder().content("comment" + i).member(member).build();
            entityManager.persist(comment);
            Thread.sleep(30);
        }
    }



}