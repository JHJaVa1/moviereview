package kr.co.jhjsoft.moviereview.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"movie","member"})
public class Review extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewnum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    //Fetch를 설정하지 않으면 Review 정보를 가져올 때 join을 해서
    //데이터를 가져온다.
    //FetchType을 LAZY로 설정하면 처음에는 가져오지 않는다.
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private int grade;
    private String text;
}
