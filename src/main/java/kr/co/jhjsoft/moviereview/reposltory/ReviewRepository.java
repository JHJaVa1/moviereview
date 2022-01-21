package kr.co.jhjsoft.moviereview.reposltory;

import kr.co.jhjsoft.moviereview.entity.Movie;
import kr.co.jhjsoft.moviereview.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    //영화 정보를 가지고 모든 영화의 모든 리뷰를 가저오는 메서드
    List<Review> findByMovie(Movie movie);

}
