package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // MemberServiceImpl은 추상화(MemberRepostiory) 에 의존하는 동시에, 구체화(MemoryMemberRepository) 에도 의존한다. -> DIP 를 위반하는 중
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
