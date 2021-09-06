package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.JDBCMemberRepository;
import hello.hellospring.repository.JDBCTemplateMemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

/*
    private final MemoryMemberRepository memberRepository;

    @Autowired
    public MemberService(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
*/

   /* private final JDBCMemberRepository memberRepository;

    public MemberService(JDBCMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    private final JDBCTemplateMemberRepository memberRepository;

    public MemberService(JDBCTemplateMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /*
    * 회원 가입
    * */
    public Long join(Member member) {
        // 중복된 이름은 안 됨
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 회원 검증
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> { // result가 null 이 아니면 다음 코드 수행하라.
                throw new IllegalStateException("이미 존재하는 회원입니다.");
             });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
