package kr.co.jhjsoft.moviereview.reposltory;

import kr.co.jhjsoft.moviereview.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
