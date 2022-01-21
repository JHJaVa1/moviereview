package kr.co.jhjsoft.moviereview.reposltory;

import kr.co.jhjsoft.moviereview.entity.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}
