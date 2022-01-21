package kr.co.jhjsoft.moviereview.reposltory;

import kr.co.jhjsoft.moviereview.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    //영화 목록 보기를 위한 메서드
    //Movie 와 Review를 join 하고 Movie로 그룹화 해서
    //Movie 정보와 grade의 평균과 Review의 개수를 구해주는 메서드
    @Query("select m, max(mi), avg(coalesce(r.grade,0)),count(distinct r) from Movie m left outer join MovieImage mi on mi.movie = m left outer join Review r on r.movie=m group by m")
    Page<Object[]> getListPage(Pageable pageable);

    //특정 영화에 대한 정보를 가져오는 메서드
    //영화 정보(Movie), 영화이미지정보(MovieImage), 리뷰정보(grade의 평균, 개수)
    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(r) from Movie m left outer join MovieImage mi on mi.movie = m left outer join Review r on r.movie = m where m.mno = :mno group by mi")
    List<Object[]> getMovieWithAll(Long mno);
}
