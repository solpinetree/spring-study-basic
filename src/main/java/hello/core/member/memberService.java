package hello.core.member;

public interface memberService {

    void join(Member member);

    Member findMember(Long memberId);
}
