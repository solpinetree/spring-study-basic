package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // MemberServiceImpl은 추상화(MemberRepostiory) 에 의존하는 동시에, 구체화(MemoryMemberRepository) 에도 의존한다. -> DIP 를 위반하는 중
    private final MemberRepository memberRepository;

    @Autowired // ac.getBean(MemberRepository.class) -> 타입이 같은 빈을 찾아서 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        // MemberServiceImpl 입장에서는 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다.
        // 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정된다.
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
