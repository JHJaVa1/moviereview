package kr.co.jhjsoft.moviereview;

import kr.co.jhjsoft.moviereview.entity.Member;
import kr.co.jhjsoft.moviereview.entity.Movie;
import kr.co.jhjsoft.moviereview.entity.MovieImage;
import kr.co.jhjsoft.moviereview.entity.Review;
import kr.co.jhjsoft.moviereview.reposltory.MemberRepository;
import kr.co.jhjsoft.moviereview.reposltory.MovieImageRepository;
import kr.co.jhjsoft.moviereview.reposltory.MovieRepository;
import kr.co.jhjsoft.moviereview.reposltory.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class RepositoryTest {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MovieImageRepository movieImageRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    //영화 정보 100개를 삽입하는 메서드
    //@Test
    @Transactional
    @Commit
    public void insertMovies(){
        Random r = new Random();
        IntStream.rangeClosed(1,100).forEach(i ->{
            Movie movie = Movie.builder().title("Movie_" + i).build();
            movieRepository.save(movie);

            int count = r.nextInt(5);
            for(int j = 0; j<count; j++){
                MovieImage movieImage = MovieImage.builder().uuid(UUID.randomUUID().toString()).imgName("test"+j+".png").movie(movie).build();
                movieImageRepository.save(movieImage);
            }
        });
    }
    //Member 100개 삽입하는 테스트
    //@Test
    public void insertMember(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            Member member = Member.builder().email("user_" + i + "@gmail.com").pw("1234").nickname("효재_"+i).build();

            memberRepository.save(member);
        });
    }
    //Review 데이터를 200개 삽입하는 메서드
    //@Test
    public void reviewInsert(){
        Random r = new Random();
        for(int i = 1; i<=300 ; i++){
            //Review는 Member와 Movie에 존재하는 데이터를 기반으로 생성
            Long mid = (long)(r.nextInt(100)+1);
            Long mno = (long)(r.nextInt(100)+1);

            Member member = Member.builder().mid(mid).build();
            Movie movie = Movie.builder().mno(mno).build();

            Review review = Review.builder().member(member).movie(movie).grade(r.nextInt(5)+1).text("리뷰_"+i).build();
            reviewRepository.save(review);
        }
    }
    //영화 목록을 가져오는 메서드
    //@Test
    public void testListPage(){
        PageRequest pageRequest = PageRequest.of(0,10, Sort.by("mno").descending());
        Page<Object[]> result = movieRepository.getListPage(pageRequest);
        for(Object[] objects : result.getContent()){
            System.out.println(Arrays.toString(objects));
        }
    }
    //특정 영화에 대한 정보를 가져오는 메서드
    //@Test
    public void testGetMovie(){
        List<Object[]> result = movieRepository.getMovieWithAll(12L);
        for(Object[] r : result){
            System.out.println(Arrays.toString(r));
        }
    }

    @Test
    public void testGetReviews(){
        List<Review> list = reviewRepository.findByMovie(Movie.builder().mno(71L).build());
        for(Review r : list){
            //회원의 이메일을 출력
            //에러 난다.
            System.out.println(r.getMember().getEmail());

        }

        System.out.println(list);
    }
}
